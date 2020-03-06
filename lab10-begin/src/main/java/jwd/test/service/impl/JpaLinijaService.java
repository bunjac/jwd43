package jwd.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.test.model.Linija;
import jwd.test.model.Rezervacija;
import jwd.test.repository.LinijaRepository;
import jwd.test.repository.RezervacijaRepository;
import jwd.test.service.LinijaService;

@Service
@Transactional
public class JpaLinijaService implements LinijaService {

	@Autowired
	private LinijaRepository linijaRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Override
	public Linija findOne(Long id) {
		return linijaRepository.findOne(id);
	}

	@Override
	public Page<Linija> findAll(int pageNum, int rowPerPage) {
		return linijaRepository.findAll(new PageRequest(pageNum, rowPerPage));
	}

	@Override
	public Page<Linija> search(String destinacija, Long prevoznikId, Double maksCena, int pageNum, int rowPerPage) {

		if (destinacija != null) {
			destinacija = '%' + destinacija + '%';
		}

		return linijaRepository.search(destinacija, prevoznikId, maksCena, new PageRequest(pageNum, rowPerPage));
	}

	@Override
	public Linija save(Linija linija) {
		return linijaRepository.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Linija linija = linijaRepository.findOne(id);
		if (linija != null) {
			linijaRepository.delete(linija);
		}

		return linija;
	}

	@Override
	public Rezervacija reserve(Long id) {
		Linija linija = findOne(id);

		if (linija != null) {
			Rezervacija rezervacija = null;
			if (true) {

			}

			return rezervacija;
		} else {
			throw new IllegalArgumentException("Tried to reserve a ticket for non-existant line");
		}
	}
}