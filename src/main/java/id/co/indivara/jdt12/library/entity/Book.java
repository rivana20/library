package id.co.indivara.jdt12.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotNull
    private String bookName;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private Integer stock;
    @Column(columnDefinition = "integer default 0")
    private Integer numberOfReading;

    public Book(String bookName, String author, String publisher, Integer stock) {
        this.bookName=bookName;
        this.author=author;
        this.publisher=publisher;
        this.stock=stock;
    }
    public void readingTimes(){
        this.numberOfReading++;
    }
    public void borrowedOne(){
        this.stock--;
    }
    public void returnedOne(){
        this.stock++;
    }
}