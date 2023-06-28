package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Book;
import id.co.indivara.jdt12.library.repo.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @Test
    public void createBookServiceTest(){
        Book book = new Book();
        book.setBookName("Twilight");
        book.setAuthor("Stephanie");
        book.setPublisher("Gramedia");
        book.setStock(5);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book newBook = bookService.createBook(book).getBody();

        Assert.assertNotNull(newBook);
        Assertions.assertThat(newBook.getBookName()).isEqualTo("Twilight");
    }
    @Test
    public void findBookServiceTest(){
        Book book = new Book();
        book.setBookId(1L);
        book.setBookName("Twilight");
        book.setAuthor("Stephanie");
        book.setPublisher("Gramedia");
        book.setStock(5);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Book existingBook = bookService.findBookById(1L).getBody();

        Assert.assertNotNull(existingBook);
        Assertions.assertThat(existingBook.getBookId()).isEqualTo(1L);
    }
    @Test
    public void findBookServiceExceptionTest(){
        Book book = new Book();
        book.setBookId(1L);
        book.setBookName("Twilight");
        book.setAuthor("Stephanie");
        book.setPublisher("Gramedia");
        book.setStock(5);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Assert.assertThrows(RuntimeException.class, ()-> {
            bookService.findBookById(2L);
        });
    }
    @Test
    public void getAllBookServiceTest(){
        List<Book> books = bookRepository.findAll();
        Assertions.assertThat(books.size()).isEqualTo(0);
    }
    @Test
    public void updateBookServiceTest(){
        Book book = new Book();
        book.setBookId(1L);
        book.setBookName("Twilight");
        book.setAuthor("Stephanie");
        book.setPublisher("Gramedia");
        book.setStock(5);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        book.setAuthor("Meyer");

        Book updatedBook = bookService.updateBook(1L, book).getBody();

        Assert.assertNotNull(updatedBook);
        Assert.assertEquals("Meyer", updatedBook.getAuthor());
    }
        @Test
    public void deleteBookServiceTest(){
        Book book = new Book();
        book.setBookId(1L);
        book.setBookName("Twilight");
        book.setAuthor("Stephanie");
        book.setPublisher("Gramedia");
        book.setStock(5);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).deleteById(anyLong());

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(anyLong());
    }
}
