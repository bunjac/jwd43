package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Record;

public interface RecordService {

	Record findOne(Long id);

	List<Record> findAll();

	Record save(Record record);

	List<Record> save(List<Record> records);

	Record delete(Long id);

	void delete(List<Long> ids);

	Page<Record> findAll(int page);
}
