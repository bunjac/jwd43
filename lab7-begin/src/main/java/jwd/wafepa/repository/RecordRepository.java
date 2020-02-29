package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

	List<Record> findByUserId(Long id);

}
