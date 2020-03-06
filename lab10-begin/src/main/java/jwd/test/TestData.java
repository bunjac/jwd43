package jwd.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.test.model.Prevoznik;
import jwd.test.service.PrevoznikService;

@Component
public class TestData {

	@Autowired
	private PrevoznikService prevoznikService;

	@PostConstruct
	public void init() {

		Prevoznik p1 = new Prevoznik();
		p1.setNaziv("Prevoznik 1");
		p1.setAdresa("Adresa 1");
		p1.setPib("PIB 1");
		prevoznikService.save(p1);

		Prevoznik p2 = new Prevoznik();
		p2.setNaziv("Prevoznik 2");
		p2.setAdresa("Adresa 2");
		p2.setPib("PIB 2");
		prevoznikService.save(p2);

	}

}
