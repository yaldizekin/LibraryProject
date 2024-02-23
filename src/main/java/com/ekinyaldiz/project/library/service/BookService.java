package com.ekinyaldiz.project.library.service;



import com.ekinyaldiz.project.library.repo.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void createBook(Book book);

    void deleteBookByBookId(Integer bookId);

    List<Book> searchBooks(String searchTerm);

    Optional<Book> getById(Integer bookId);

    void updateBook(Book book);
}

