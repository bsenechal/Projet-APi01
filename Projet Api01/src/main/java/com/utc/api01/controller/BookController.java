package com.utc.api01.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private GeneriqueService<Book> bookService;
    private GeneriqueService<Question> questionService;
    private GeneriqueService<User> userService;
    private GeneriqueService<Notes> noteService;
    
    private static final String REDIRECT_DETAILBOOK = "detailBook";
    private static final String REDIRECT_EDITBOOK = "editBook";
    private static final String REDIRECT_LISTING = "listing";
    private static final String MSG_ADD_SUCCESS = "Le livre a correctement été ajouté.";
    private static final String MSG_EDIT_SUCCESS = "Le livre a correctement été modifié.";
    private static final String MSG_SUPPR_SUCCESS = "Le livre a correctement été supprimé.";
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

    @RequestMapping(value = "/book/detail/{idBook}", method = RequestMethod.GET)
    public String detailBook(@PathVariable("idBook") int idBook, Model model) {
        model.addAttribute("book", this.bookService.getById(idBook));
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
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.list());
        return JSP_BOOK;
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
    public String removeBook(@PathVariable("idBook") int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", MSG_SUPPR_SUCCESS);
        model.setViewName(JSP_BOOK);
        this.bookService.remove(id);
        model.addObject("listBooks", this.bookService.list());
        return REDIRECT_LISTING;
    }
    
    @RequestMapping(value = "/admin/book/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("book") Book b, BindingResult result) {
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
            
//            bookList.add(new Book(10,"test4","autor","type","description"));
//            bookList.add(new Book(11,"test","autor2","type2","description"));
//            bookList.add(new Book(12,"test8","autor","type","description"));
//            
//            questionList.add(new Question(01,"question1",5,1));
//            questionList.add(new Question(02,"question2",5,1));
//            questionList.add(new Question(03,"question3",5,1));
//            questionList.add(new Question(04,"question4",5,1));
//            
//            evaluationList.add(new Evaluation(01,0,bookList.get(0),new User()));
//            evaluationList.add(new Evaluation(02,0,bookList.get(0),new User()));
//            
//            noteList.add(new Notes(01,5,evaluationList.get(0),questionList.get(0)));
            
            MatchFounder matchfounder = new MatchFounder(bookList, questionList, noteList, evaluationList);
            Book conseille = matchfounder.matchFounding();
            model.addAttribute("book",conseille);

            return "bookProposition";
            
        }else return REDIRECT_LOGIN;  
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
}
