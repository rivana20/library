package id.co.indivara.jdt12.library.controller;

import id.co.indivara.jdt12.library.entity.Reader;
import id.co.indivara.jdt12.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@Controller
@RequestMapping("/library")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/reader")
    List<Reader> getAllReader (@RequestParam(required = false)String name){
        return readerService.getAllReader(name).getBody();
    }
    @PostMapping("/reader")
    Reader createReader(@RequestBody Reader newReader){
        return readerService.createReader(newReader).getBody();
    }
    @GetMapping("/reader/{readerId}")
    Reader findReaderById(@PathVariable("readerId")Long id){
        return readerService.findReaderById(id).getBody();
    }
    @DeleteMapping("/reader/{readerId}")
    HttpStatus deleteReaderById (@PathVariable("readerId")Long id){
        return readerService.deleteReaderById(id).getBody();
    }

    @PutMapping("/reader/{id}")
    Reader updateReader (@PathVariable Long id, @RequestBody Reader reader){
        return readerService.updateReader(id, reader).getBody();
    }
}
