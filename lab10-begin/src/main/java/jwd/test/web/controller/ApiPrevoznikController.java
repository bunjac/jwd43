package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Prevoznik;
import jwd.test.service.PrevoznikService;
import jwd.test.support.PrevoznikToPrevoznikDTO;
import jwd.test.web.dto.PrevoznikDTO;

@RestController
@RequestMapping(value = "/api/prevoznici")
public class ApiPrevoznikController {

	@Autowired
	private PrevoznikService prevoznikService;

	@Autowired
	private PrevoznikToPrevoznikDTO toDto;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<PrevoznikDTO>> getAll() {
		List<Prevoznik> prevoznici = prevoznikService.findAll();
		return new ResponseEntity<List<PrevoznikDTO>>(toDto.convert(prevoznici), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	ResponseEntity<PrevoznikDTO> getOne(@PathVariable Long id) {
		Prevoznik prevoznik = prevoznikService.findOne(id);
		if (prevoznik == null) {
			return new ResponseEntity<PrevoznikDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PrevoznikDTO>(toDto.convert(prevoznik), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}