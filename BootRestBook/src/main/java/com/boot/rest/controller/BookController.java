package com.boot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.rest.entities.Book;
import com.boot.rest.service.BookService;

@RestController
public class BookController {
    
	@Autowired
	BookService bookService;
	
	
	
	@RequestMapping(value="/book",method=RequestMethod.GET)
	@ResponseBody
	public String getBook() {
		
		return "This is for testing purspose";
	}
	
	// this method returns specific book provided in url
	@GetMapping("/books/{id}")
	public ResponseEntity<Book>  getBookById(@PathVariable("id") int id) {
		
		Book book= this.bookService.getBookById(id);
		if(book==null) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	// this returns all books in service class
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> booklist=this.bookService.getAllBooks();
		if(booklist.size()<=0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(booklist));
	}
	
	// add new book
    @PostMapping("/books")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        try {
    	Book newBook= this.bookService.addBook(book);
    	System.out.println(newBook);
    	return ResponseEntity.of(Optional.of(newBook));
        }
        catch (Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
        
    }
    
    //delete book by id
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void>  deleteBookById(@PathVariable("id") int id) {
    	
    	try{
    		bookService.deleteBook(id);
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	catch(Exception e) {
    		
    		e.printStackTrace();
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    	
    }
    
    // update book by id
    
    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBookById(@RequestBody Book book, @PathVariable("id") int id) {
    	try {
    		bookService.updateBook(book, id);
    		return  ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    	
    
    }
}
