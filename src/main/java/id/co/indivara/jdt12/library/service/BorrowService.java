package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Book;
import id.co.indivara.jdt12.library.entity.Borrow;
import id.co.indivara.jdt12.library.entity.Reader;
import id.co.indivara.jdt12.library.entity.Wishlist;
import id.co.indivara.jdt12.library.repo.BookRepository;
import id.co.indivara.jdt12.library.repo.BorrowRepository;
import id.co.indivara.jdt12.library.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private WishlistService wishlistService;

    public String addBorrow(Borrow borrow){
        Reader reader = readerRepository.findById(borrow.getBorrowerId()).get();
        Book book = bookRepository.findById(borrow.getBookId()).get();

        if (book.getStock()<1){
            Wishlist wishlist = new Wishlist();
            wishlist.setBookId(borrow.getBookId());
            wishlist.setReaderId(borrow.getBorrowerId());
            wishlistService.saveWishlist(wishlist);
            return "Your requested book \"" + book.getBookName() + "\" is out of stock!\n" + "Wishlist added!";
        }
        book.readingTimes();
        book.borrowedOne();
        bookRepository.save(book);
        Date currentDate = new Date();
        borrow.setCheckOut(currentDate);
        borrowRepository.save(borrow);
        return reader.getReaderName() + " has borrowed one copy of \"" + book.getBookName() + "\"!";
    }

    public Borrow returnBook(Borrow borrowBody){
        Long borrowId = borrowBody.getBorrowId();
        Borrow borrow = borrowRepository.findById(borrowId).get();
        Book book = bookRepository.findById(borrow.getBookId()).get();

        book.returnedOne();
        bookRepository.save(book);
        Date currentDate = new Date();
        borrow.setCheckIn(currentDate);
        return borrowRepository.save(borrow);
    }
}
