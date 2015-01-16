package com.utc.api01.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private final static String REDIRECT_DETAILBOOK = "detailBook";
    private final static String REDIRECT_EDITBOOK = "editBook";
    private final static String REDIRECT_LISTING = "listing";
    private final static String JSP_BOOK = "book";
    
    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setUserService(GeneriqueService<Book> us) {
        this.bookService = us;
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
        model.addAttribute("url", "add");
        return REDIRECT_EDITBOOK;
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book b) {
        this.bookService.add(b);
        return REDIRECT_LISTING;
    }

    @RequestMapping("/book/edit/{idBook}")
    public String editBook(@PathVariable("idBook") int idBook, Model model) {
        model.addAttribute("book", this.bookService.getById(idBook));
        model.addAttribute("url", "update");
        return REDIRECT_EDITBOOK;
    }

    @RequestMapping(value = "/book/edit/update", method = RequestMethod.POST)
    public String editAndSaveBook(@ModelAttribute("book") Book u) {
        this.bookService.update(u);
        return REDIRECT_LISTING;
    }

    @RequestMapping("/admin/book/remove/{idBook}")
    public String removeBook(@PathVariable("idBook") int id) {
        this.bookService.remove(id);
        return REDIRECT_LISTING;
    }
    
    @RequestMapping("/book/match")
    public String matchBook(Model model) {
        ArrayList<Book> bookList = new ArrayList<Book>();
        ArrayList<Question> questionList = new ArrayList<Question>();
        ArrayList<Notes> noteList = new ArrayList<Notes>();
        ArrayList<Evaluation> evaluationList = new ArrayList<Evaluation>();
        
        bookList.add(new Book(10,"test4","autor","type","description"));
        bookList.add(new Book(11,"test","autor2","type2","description"));
        bookList.add(new Book(12,"test8","autor","type","description"));
        
        questionList.add(new Question(01,"question1",5,1));
        questionList.add(new Question(02,"question2",5,1));
        questionList.add(new Question(03,"question3",5,1));
        questionList.add(new Question(04,"question4",5,1));
        
        evaluationList.add(new Evaluation(01,0,bookList.get(0),new User()));
        evaluationList.add(new Evaluation(02,0,bookList.get(0),new User()));
        
        noteList.add(new Notes(01,5,evaluationList.get(0),questionList.get(0)));
        
        MatchFounder matchfounder = new MatchFounder(bookList, questionList, noteList, evaluationList);
        Book conseille = matchfounder.matchFounding();
        model.addAttribute("book",conseille);

        return "bookProposition";
    }
}
