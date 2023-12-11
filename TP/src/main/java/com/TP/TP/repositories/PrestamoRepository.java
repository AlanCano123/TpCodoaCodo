package com.TP.TP.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.TP.TP.models.Prestamo;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
