package com.utc.api01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.utc.api01.model.Book;
import com.utc.api01.service.GeneriqueService;

@Controller
public class BookController {
	
private GeneriqueService<Book> bookService;
	
	@Autowired(required=true)
	@Qualifier(value="bookService")
	public void setUserService(GeneriqueService<Book> us){
		this.bookService = us;
	}
	
	@RequestMapping(value = "/book/listing", method = RequestMethod.GET)
	public String listBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("listBooks", this.bookService.list());
		return "book";
	}
	
	@RequestMapping(value= "book/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") Book b){
		this.bookService.add(b);
		return "redirect:/book/listing";
	}
	
	@RequestMapping("/book/edit/{idBook}")
    public String editBook(@PathVariable("idBook") int idBook, Model model){
		model.addAttribute("book", this.bookService.getById(idBook));
        return "editBook";
    }
	
	@RequestMapping(value= "/book/update", method = RequestMethod.POST)
    public String editAndSaveBook(@ModelAttribute("book") Book u){
    	this.bookService.update(u);
        return "redirect:/book/listing";
    }
}
