package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.entity.Book;
import id.co.indivara.jdt12.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@Controller
@RequestMapping("/library")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    List<Book> getAllBook (@RequestParam(required = false) String title){
        return bookService.getAllBook(title).getBody();
    }
    @PostMapping("/book")
    Book createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook).getBody();
    }

    @GetMapping("/book/{bookId}")
    Book findBookById(@PathVariable("bookId")Long id){
        return bookService.findBookById(id).getBody();
    }

    @DeleteMapping("/book/{bookId}")
    HttpStatus deleteBook(@PathVariable("bookId") Long id){
        return bookService.deleteBook(id).getBody();
    }
    @PutMapping("/book/{id}")
    Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(id, book).getBody();
    }
}
