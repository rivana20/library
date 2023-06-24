package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Reader;
import id.co.indivara.jdt12.library.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public ResponseEntity<List<Reader>> getAllReader(String name){
        List<Reader> readers = new ArrayList<>();
        if (name == null){
            readers.addAll(readerRepository.findAll());
        } else if (readers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(readers,HttpStatus.OK);
    }

    public ResponseEntity<Reader> createReader(Reader newReader){
        Reader reader = readerRepository.save(new Reader(newReader.getReaderName(),newReader.getJoinDate()));
        return new ResponseEntity<>(reader,HttpStatus.OK);
    }

    public ResponseEntity<Reader> findReaderById(Long id){
        Reader reader = readerRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(reader, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteReaderById(Long id){
        readerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Reader> updateReader(Long id, Reader reader){
        Reader reader1 = readerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        reader1.setReaderName(reader.getReaderName());
        reader1.setJoinDate(reader.getJoinDate());
        return new ResponseEntity<>(readerRepository.save(reader1), HttpStatus.OK);
    }
}
