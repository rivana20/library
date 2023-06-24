package id.co.indivara.jdt12.library.repo;

import id.co.indivara.jdt12.library.entity.Reader;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReaderRepositoryTest {
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void createReaderTest() throws ParseException {
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = Reader.builder()
                .readerName("Ferdy Sambo")
                .joinDate(date1)
                .build();
        readerRepository.save(reader);
        Assertions.assertThat(reader.getReaderId()).isGreaterThan(0);
    }
    @Test
    public void findReaderByIdTest()throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderName("Budi Santoso");
        reader.setJoinDate(date1);
        readerRepository.save(reader);

        Reader existingReader = readerRepository.findById(reader.getReaderId()).get();

        Assert.assertNotNull(existingReader);
        Assert.assertEquals("Budi Santoso", existingReader.getReaderName());
        String beforeDate = "07-08-2020";
        Date existingDate = dateFormat.parse(beforeDate);
        Assertions.assertThat(reader.getJoinDate()).isBefore(existingDate);
    }
    @Test
    public void getAllReaderTest(){
        List<Reader> readers = readerRepository.findAll();
        Assertions.assertThat(readers.size()).isGreaterThan(0);
    }
    @Test
    public void updateReaderTest() throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderName("Budi Santoso");
        reader.setJoinDate(date1);
        readerRepository.save(reader);

        Reader existingReader = readerRepository.findById(reader.getReaderId()).get();
        existingReader.setReaderName("Amir Jaya");
        Reader newReader = readerRepository.save(existingReader);

        Assert.assertEquals("Amir Jaya", newReader.getReaderName());

    }
    @Test
    public void deleteReaderTest() throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader budi = new Reader();
        budi.setReaderName("Budi Santoso");
        budi.setJoinDate(date1);
        readerRepository.save(budi);
        Long id = budi.getReaderId();

        readerRepository.delete(budi);
        Optional<Reader> existingReader = readerRepository.findById(id);

        Assertions.assertThat(existingReader).isEmpty();
    }
}
