package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Record;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.support.RecordDTOToRecord;
import jwd.wafepa.support.RecordToRecordDTO;
import jwd.wafepa.web.dto.RecordDTO;

@RestController
@RequestMapping()
public class ApiRecordController {

	@Autowired
	private RecordService recordService;

	@Autowired
	private RecordToRecordDTO toDto;

	@Autowired
	private RecordDTOToRecord toRecord;

	@RequestMapping(value = "/api/records", method = RequestMethod.GET)
	ResponseEntity<List<RecordDTO>> findAll(@RequestParam(defaultValue = "0") int page) {

		Page<Record> pages = recordService.findAll(page);
		List<Record> records = pages.getContent();

		if (records == null || records.isEmpty()) {
			return new ResponseEntity<List<RecordDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<RecordDTO>>(toDto.convert(records), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/records/{id}", method = RequestMethod.GET)
	ResponseEntity<RecordDTO> findOne(@PathVariable Long id) {

		Record record = recordService.findOne(id);
		if (record == null) {
			return new ResponseEntity<RecordDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RecordDTO>(toDto.convert(record), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/records", method = RequestMethod.POST, consumes = "application/json")
	ResponseEntity<RecordDTO> add(@Validated @RequestBody RecordDTO record) {

		Record newRecord = toRecord.convert(record);
		recordService.save(newRecord);
		return new ResponseEntity<RecordDTO>(toDto.convert(newRecord), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/api/records/{id}", method = RequestMethod.DELETE)
	ResponseEntity<RecordDTO> delete(@PathVariable Long id) {

		Record deleted = recordService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<RecordDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RecordDTO>(toDto.convert(deleted), HttpStatus.OK);

	}

	@RequestMapping(value = "/api/records/{id}", method = RequestMethod.PUT, consumes = "application/json")
	ResponseEntity<RecordDTO> edit(@PathVariable Long id, @Validated @RequestBody RecordDTO record) {

		if (!id.equals(record.getId())) {
			return new ResponseEntity<RecordDTO>(HttpStatus.BAD_REQUEST);
		}

		Record edited = recordService.save(toRecord.convert(record));

		return new ResponseEntity<RecordDTO>(toDto.convert(edited), HttpStatus.OK);

	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
