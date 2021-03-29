package com.boot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.boot.rest.dao.BookRepository;
import com.boot.rest.entities.Book;

@Component
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	/*
	 * private static List<Book> bookList=new ArrayList<Book>(); static {
	 * 
	 * bookList.add(new Book(101,"Head First Java","XYZ",459.00f)); bookList.add(new
	 * Book(102,"Spring Pro","ABC",988.7f)); bookList.add(new
	 * Book(103,"Design Pattern Java","XYZ",999.00f));
	 * 
	 * }
	 */
	
	public List<Book> getAllBooks(){
		List<Book> bookList=(List<Book>)this.bookRepository.findAll();
		return bookList;
	}
	
	public Book getBookById(int id) {
		
		Book book=null;
//		book= bookList.stream().filter(e-> e.getId()==id).findFirst().get();
		book=this.bookRepository.findById(id);
		return book;		
	}
	
	public Book addBook(Book b) {
		
//		 bookList.add(b);
		Book result= this.bookRepository.save(b);
		 return result;
	}
	
	public void deleteBook(int id) {
		
//		bookList=bookList.stream().filter(book -> book.getId()!=id).collect(Collectors.toList());
		this.bookRepository.deleteById(id);
	}
	
	//update book by id and set book title and author 
	public void updateBook(Book book, int id) {
		
		/*
		 * bookList=bookList.stream().map(b-> {
		 * 
		 * if(b.getId()==id) {
		 * 
		 * b.setTitle(book.getTitle()); b.setAuthorName(book.getAuthorName());
		 * b.setPrice(book.getPrice()); } return b; }).collect(Collectors.toList());
		 */
		book.setId(id);
		this.bookRepository.save(book);
	}

}
