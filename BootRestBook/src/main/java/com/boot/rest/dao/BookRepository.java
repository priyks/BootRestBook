package com.boot.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.rest.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public Book findById(int id);

}
