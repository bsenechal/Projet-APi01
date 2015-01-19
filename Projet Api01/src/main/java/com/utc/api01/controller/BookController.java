package com.utc.api01.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.matching.MatchFounder;
import com.utc.api01.model.Book;
import com.utc.api01.model.Evaluation;
import com.utc.api01.model.Notes;
import com.utc.api01.model.Question;
import com.utc.api01.model.User;
import com.utc.api01.service.GeneriqueService;

@Controller
public class BookController {
    private GeneriqueService<Evaluation> evalService;
    private GeneriqueService<Book> bookService;
    private GeneriqueService<Question> questionService;
    private GeneriqueService<User> userService;
    private GeneriqueService<Notes> noteService;
    
    private static final String REDIRECT_DETAILBOOK = "detailBook";
    private static final String REDIRECT_MYBOOK = "myBook";
    private static final String REDIRECT_EDITBOOK = "editBook";
    private static final String REDIRECT_INDEX = "index";
    private static final String MSG_ADD_SUCCESS = "Le livre a correctement �t� ajout�.";
    private static final String MSG_EDIT_SUCCESS = "Le livre a correctement �t� modifi�.";
    private static final String MSG_SUPPR_SUCCESS = "Le livre a correctement �t� supprim�.";
    private static final String REDIRECT_LOGIN = "login";
    private static final String JSP_BOOK = "book";
    
    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(GeneriqueService<Book> us) {
        this.bookService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "questionService")
    public void setQuestionService(GeneriqueService<Question> us) {
        this.questionService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(GeneriqueService<User> us) {
        this.userService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "noteService")
    public void setNoteService(GeneriqueService<Notes> us) {
        this.noteService = us;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "evalService")
    public void setEvaluationService(GeneriqueService<Evaluation> e) {
        this.evalService = e;
    }

    @RequestMapping(value = "/book/detail/{idBook}", method = RequestMethod.GET)
    public String detailBook(@PathVariable("idBook") int idBook, Model model) {
        model.addAttribute("book", this.bookService.getById(idBook));
        if(!this.noteService.list().isEmpty()) model.addAttribute("notes", getNoteByBook((ArrayList<Notes>) this.noteService.list(), idBook));
        return REDIRECT_DETAILBOOK;
    }

    @RequestMapping(value = "/imageDisplay/{idBook}", method = RequestMethod.GET)
    public void showImage(@PathVariable("idBook") Integer idBook,
            HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(
                this.bookService.getById(idBook).getImage());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/book/listing", method = RequestMethod.GET)
    public String listBook(Model model) {
        model.addAttribute("listBooks", this.bookService.list());
        return JSP_BOOK;
    }
    
    @RequestMapping(value = "/book/found", method = RequestMethod.GET)
    public String foundBook(@RequestParam("title") String title,Model model) {
        Book b = this.bookService.getByCriteria("title", title);
        if(b != null){
            return "redirect:/book/detail/"+b.getIdBook();
        }else{ 
            model.addAttribute("listBooks",this.bookService.list());
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/book/new", method = RequestMethod.GET)
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return REDIRECT_EDITBOOK;
    }

    @RequestMapping("/book/edit/{idBook}")
    public String editBook(@PathVariable("idBook") int idBook, Model model) {
        model.addAttribute("book", this.bookService.getById(idBook));
        return REDIRECT_EDITBOOK;
    }

    @RequestMapping("/admin/book/remove/{idBook}")
    public ModelAndView removeBook(@PathVariable("idBook") int idBook) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        model.setViewName(JSP_BOOK);
        
        for (Evaluation e : this.evalService.list()){
            if (e.getBook().getIdBook() == idBook){
                for (Notes n : this.noteService.list()){
                    if (n.getEvaluation().getIdEval() == e.getIdEval()){
                        this.noteService.remove(n.getIdNotes());
                    }
                }
                this.evalService.remove(e.getIdEval());
            }
        }
        
        this.bookService.remove(idBook);
        model.addObject("listBooks", this.bookService.list());
        return model;
    }
    
    @RequestMapping(value = "/book/myBook", method = RequestMethod.GET)
    public String myBook(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        
        if (principal instanceof org.springframework.security.core.userdetails.User){
            userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            User user = this.userService.getByCriteria("email", userName);
            
            ArrayList<Notes> noteList = getNoteByUser(user, (ArrayList<Notes>)this.noteService.list());
            model.addAttribute("notes",noteList);
            
            return REDIRECT_MYBOOK;
        }
       
        return REDIRECT_LOGIN;
    }
    
    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("book") Book b,
            @RequestParam("file") MultipartFile file,
            BindingResult result) throws IOException {
        ModelAndView model = new ModelAndView();
       
        if (result.hasErrors()) {
            if (b.getIdBook() != 0){
                model.addObject("book", b);
            }
            model.setViewName(REDIRECT_EDITBOOK);
        } else {
            model.setViewName(JSP_BOOK);
            
            if (b.getIdBook() != 0) {
                model.addObject("msg", MSG_EDIT_SUCCESS);
                this.bookService.update(b);
            } else {
                model.addObject("msg", MSG_ADD_SUCCESS);

                b.setImage(file.getBytes());

                this.bookService.add(b);
            }
            model.addObject("listBooks", this.bookService.list());
        }
            return model;
    }
    
    @RequestMapping("/book/match")
    public String matchBook(Model model) {
        ArrayList<Book> bookList = (ArrayList<Book>) this.bookService.list();
        ArrayList<Question> questionList = (ArrayList<Question>) this.questionService.list();
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        
        if (principal instanceof org.springframework.security.core.userdetails.User){
            userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            User user = this.userService.getByCriteria("email", userName);
            
            ArrayList<Notes> noteList = getNoteByUser(user, (ArrayList<Notes>)this.noteService.list());
            ArrayList<Evaluation> evaluationList = getEvaluationByNote(noteList);
            
            MatchFounder matchfounder = new MatchFounder(bookList, questionList, noteList, evaluationList);
            Book conseille = matchfounder.matchFounding();
            model.addAttribute("book",conseille);

            return "bookProposition";
            
        }else return REDIRECT_LOGIN;  
    }
    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws ServletException {
            binder.registerCustomEditor(byte[].class,
                new ByteArrayMultipartFileEditor());
    }
    
    public ArrayList<Notes> getNoteByUser(User user, ArrayList<Notes> notes){
        ArrayList<Notes> res = new ArrayList<Notes>();
        for(Notes n : notes) if(n.getEvaluation().getUser().getIdUser() == user.getIdUser()) res.add(n);     
        return res;
    }
    
    public ArrayList<Evaluation> getEvaluationByNote(ArrayList<Notes> notes){
        ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
        for(Notes n : notes) evaluations.add(n.getEvaluation());
        return evaluations;
    }
    
    public ArrayList<Evaluation> getEvaluationByBook(ArrayList<Evaluation> eval, int idBook){
        ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
        for(Evaluation e : eval) if(e.getBook().getIdBook() == idBook) evaluations.add(e);
        return evaluations;
    }
    
    public ArrayList<Notes> getNoteByBook(ArrayList<Notes> notes, int idBook){
        ArrayList<Notes> res = new ArrayList<Notes>();
        for(Notes n : notes) if(n.getEvaluation().getBook().getIdBook() == idBook) res.add(n);     
        return res;
    }
}
