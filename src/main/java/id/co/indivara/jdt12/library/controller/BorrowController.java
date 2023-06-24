package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.entity.Borrow;
import id.co.indivara.jdt12.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8080")
@RestController
@Controller
@RequestMapping("/library")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @PostMapping("/borrow")
    String addBorrow(@RequestBody Borrow borrow){
        return borrowService.addBorrow(borrow);
    }
    @PutMapping("/return")
    Borrow returnBook(@RequestBody Borrow borrowBody){
        return borrowService.returnBook(borrowBody);
    }
}
