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
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;
    @NotNull
    private Long readerId;
    @NotNull
    private Long bookId;

    public Wishlist(Long bookId, Long readerId) {
        this.bookId=bookId;
        this.readerId=readerId;
    }
}
