package jwd.test.service;

import java.util.List;

import jwd.test.model.Prevoznik;

public interface PrevoznikService {
	Prevoznik findOne(Long id);

	List<Prevoznik> findAll();

	Prevoznik save(Prevoznik prevoznik);

}