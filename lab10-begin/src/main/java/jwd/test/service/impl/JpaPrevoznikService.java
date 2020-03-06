package jwd.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.test.model.Prevoznik;
import jwd.test.repository.PrevoznikRepository;
import jwd.test.service.PrevoznikService;

@Service
public class JpaPrevoznikService implements PrevoznikService {

	@Autowired
	private PrevoznikRepository prevoznikRepository;

	@Override
	public Prevoznik findOne(Long id) {
		return prevoznikRepository.findOne(id);
	}

	@Override
	public List<Prevoznik> findAll() {
		return prevoznikRepository.findAll();
	}

	@Override
	public Prevoznik save(Prevoznik prevoznik) {
		return prevoznikRepository.save(prevoznik);
	}

}