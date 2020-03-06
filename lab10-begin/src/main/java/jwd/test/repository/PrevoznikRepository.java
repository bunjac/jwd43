package jwd.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.test.model.Prevoznik;

@Repository
public interface PrevoznikRepository extends JpaRepository<Prevoznik, Long> {
		
}