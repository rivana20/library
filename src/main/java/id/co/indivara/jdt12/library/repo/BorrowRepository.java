package id.co.indivara.jdt12.library.repo;

import id.co.indivara.jdt12.library.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
