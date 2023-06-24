package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.entity.Reader;
import id.co.indivara.jdt12.library.repo.ReaderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReaderServiceTest {
    @InjectMocks
    private ReaderService readerService;
    @Mock
    private ReaderRepository readerRepository;

    @Test
    public void createReaderServiceTest() throws ParseException {
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setReaderName("Amir Jaya");
        reader.setJoinDate(date1);

        when(readerRepository.save(any(Reader.class))).thenReturn(reader);

        Reader newReader = readerService.createReader(reader).getBody();

        Assert.assertNotNull(newReader);
        Assertions.assertThat(newReader.getReaderName()).isEqualTo("Amir Jaya");
    }
    @Test
    public void findReaderServiceTest()throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setReaderName("Amir Jaya");
        reader.setJoinDate(date1);

        when(readerRepository.findById(anyLong())).thenReturn(Optional.of(reader));

        Reader existingReader = readerService.findReaderById(1L).getBody();

        Assert.assertNotNull(existingReader);
        Assertions.assertThat(existingReader.getReaderId()).isEqualTo(1L);
    }
    @Test
    public void findReaderServiceExceptionTest()throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setReaderName("Amir Jaya");
        reader.setJoinDate(date1);

        when(readerRepository.findById(1L)).thenReturn(Optional.of(reader));

        Assert.assertThrows(RuntimeException.class, ()-> {
           readerService.findReaderById(2L);
        });
    }
    @Test
    public void getAllReaderServiceTest()throws ParseException{
        List<Reader> readers = readerRepository.findAll();
        Assertions.assertThat(readers.size()).isEqualTo(0);
    }
    @Test
    public void updateReaderServiceTest()throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setReaderName("Amir Jaya");
        reader.setJoinDate(date1);

        when(readerRepository.findById(anyLong())).thenReturn(Optional.of(reader));
        when(readerRepository.save(any(Reader.class))).thenReturn(reader);
        reader.setReaderName("Budi Santoso");

        Reader updatedReader = readerService.updateReader(1L, reader).getBody();

        Assert.assertNotNull(updatedReader);
        Assert.assertEquals("Budi Santoso", updatedReader.getReaderName());
    }
    @Test
    public void deleteReaderServiceTest()throws ParseException{
        String date = "07-07-2020";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setReaderName("Amir Jaya");
        reader.setJoinDate(date1);

        when(readerRepository.findById(anyLong())).thenReturn(Optional.of(reader));
        doNothing().when(readerRepository).delete(reader);

        readerService.deleteReaderById(1L);

        verify(readerRepository, times(1)).delete(reader);
    }
}
