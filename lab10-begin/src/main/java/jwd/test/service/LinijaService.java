package jwd.test.service;

import org.springframework.data.domain.Page;

import jwd.test.model.Linija;
import jwd.test.model.Rezervacija;

public interface LinijaService {
	Linija findOne(Long id);

	Page<Linija> findAll(int pageNum, int rowsPerPage);

	Page<Linija> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum, int rowsPerPage);

	Linija save(Linija linija);

	Linija delete(Long id);

	Rezervacija reserve(Long id);
}