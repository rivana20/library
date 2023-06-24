package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Book;
import id.co.indivara.jdt12.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<List<Book>> getAllBook(String title){
        List<Book> books = new ArrayList<>();
        if (title == null){
            books.addAll(bookRepository.findAll());
        } else if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> createBook(Book newBook){
        Book book = bookRepository.save(new Book(newBook.getBookName(),newBook.getAuthor(), newBook.getPublisher(), newBook.getStock()));
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    public ResponseEntity<Book> findBookById(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteBook (Long bookId){
        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<Book> updateBook(Long id, Book book){
        Book book1 = bookRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        book1.setBookName(book.getBookName());
        book1.setAuthor(book.getAuthor());
        book1.setPublisher(book.getPublisher());
        book1.setStock(book.getStock());
        return new ResponseEntity<>(bookRepository.save(book1),
                HttpStatus.OK);
    }
}
