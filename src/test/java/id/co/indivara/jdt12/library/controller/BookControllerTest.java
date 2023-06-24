//package id.co.indivara.jdt12.library.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import id.co.indivara.jdt12.library.entity.Book;
//import id.co.indivara.jdt12.library.service.BookService;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import java.awt.*;
//
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class BookControllerTest {
//    @InjectMocks
//    private BookService bookService;
//    @Mock
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void createBookControllerTest() throws Exception {
//        Book book = new Book();
//        book.setBookId(1L);
//        book.setBookName("Twilight");
//        book.setAuthor("Stephanie");
//        book.setPublisher("Gramedia");
//        book.setStock(5);
//
//        when(bookService.createBook(any(Book.class)));
//
//        this.mockMvc.perform(post("/library/book").contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(book)))
//                .andExpect(status().isCreated())
//                .andExpect((ResultMatcher) jsonPath("$.name", is(book.getBookName())))
//                .andExpect((ResultMatcher) jsonPath("$.author", is(book.getAuthor())))
//                .andExpect((ResultMatcher) jsonPath("$.pusblisher", is(book.getPublisher())))
//                .andExpect((ResultMatcher) jsonPath("$.stock", is(book.getStock())));
//    }
//}
