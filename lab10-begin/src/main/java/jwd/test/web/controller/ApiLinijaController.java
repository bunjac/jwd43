package jwd.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import jwd.test.model.Linija;
import jwd.test.model.Rezervacija;
import jwd.test.service.LinijaService;
import jwd.test.support.LinijaDTOToLinija;
import jwd.test.support.LinijaToLinijaDTO;
import jwd.test.support.RezervacijaToRezervacijaDTO;
import jwd.test.web.dto.LinijaDTO;
import jwd.test.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/linije")
public class ApiLinijaController {
	@Autowired
	private LinijaService linijaService;

	@Autowired
	private LinijaToLinijaDTO toDTO;

	@Autowired
	private RezervacijaToRezervacijaDTO toLinijaDTO;

	@Autowired
	private LinijaDTOToLinija toLinija;

	@Autowired
	private RezervacijaToRezervacijaDTO toRezDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<LinijaDTO>> get(@RequestParam(required = false) String destinacija,
			@RequestParam(required = false) Long prevoznikId, @RequestParam(required = false) Double maksCena,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "rowsPerPage", defaultValue = "10") int rowsPerPage) {

		Page<Linija> linijePage = null;

		if (destinacija != null || prevoznikId != null || maksCena != null) {
			linijePage = linijaService.search(destinacija, prevoznikId, maksCena, pageNum, rowsPerPage);
		} else {
			linijePage = linijaService.findAll(pageNum, rowsPerPage);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(linijePage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(linijePage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<LinijaDTO> get(@PathVariable Long id) {
		Linija linija = linijaService.findOne(id);
		if (linija == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(linija), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<LinijaDTO> delete(@PathVariable Long id) {
		Linija deleted = linijaService.delete(id);
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<LinijaDTO> add(@Validated @RequestBody LinijaDTO newLinijaDTO) {
		Linija savedLinija = linijaService.save(toLinija.convert(newLinijaDTO));
		return new ResponseEntity<>(toDTO.convert(savedLinija), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<LinijaDTO> edit(@Validated @RequestBody LinijaDTO linijaDTO, @PathVariable Long id) {
		if (!id.equals(linijaDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Linija persisted = linijaService.save(toLinija.convert(linijaDTO));
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<RezervacijaDTO> rezervisi(@PathVariable(value = "id") Long linijaId) {
		Rezervacija rez = linijaService.reserve(linijaId);

		if (rez != null) {
			return new ResponseEntity<RezervacijaDTO>(toRezDTO.convert(rez), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}