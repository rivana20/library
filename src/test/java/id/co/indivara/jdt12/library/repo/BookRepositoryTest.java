package id.co.indivara.jdt12.library.repo;

import id.co.indivara.jdt12.library.entity.Book;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.assertj.core.api.Assertions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test //Create book
    @Order(1)
    public void createBookTest(){
        Book book = Book.builder()
                .bookName("Twilight")
                .author("Stephanie Meyer")
                .publisher("Gramedia")
                .stock(3)
                .build();
        bookRepository.save(book);
        Assertions.assertThat(book.getBookId()).isGreaterThan(0);
    }

    @Test //Find book by id
    @Order(2)
    public void findBookByIdTest(){
        Book book = bookRepository.findById(1L).get();
        Assertions.assertThat(book.getBookId()).isEqualTo(1L);
    }

    @Test //Find all listed books
    @Order(3)
    public void getAllBookTest(){
        List<Book> books = bookRepository.findAll();
        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

    @Test //Update existing book
    @Order(4)
    public void updateBookTest(){
        Book book = bookRepository.findById(1L).get();
        book.setBookName("Lord of the Rings");
        book.setAuthor("John Ronald");
        book.setStock(5);

        Book updatedBook = bookRepository.save(book);
        Assertions.assertThat(updatedBook.getBookName()).isEqualTo("Lord of the Rings");
        Assertions.assertThat(updatedBook.getAuthor()).isEqualTo("John Ronald");
        Assertions.assertThat(updatedBook.getStock()).isEqualTo(5);
    }

    @Test //Delete existing book
    @Order(5)
    public void deleteBookTest(){
        Book book = bookRepository.findById(1L).get();
        bookRepository.delete(book);
        Book book1 = null;
        Optional<Book> optionalBook = bookRepository.findByBookName("Lord of the Rings");
        if (optionalBook.isPresent()){
            book1 = optionalBook.get();
        }
        Assertions.assertThat(book1).isNull();
    }
}