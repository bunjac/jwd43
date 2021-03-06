package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Rezervacija;
import jwd.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO> {

	@Override
	public RezervacijaDTO convert(Rezervacija rezervacija) {
		if (rezervacija == null) {
			return null;
		}

		RezervacijaDTO dto = new RezervacijaDTO();

		dto.setId(rezervacija.getId());
		dto.setLinijaId(rezervacija.getLinija().getId());

		return dto;
	}

	public List<RezervacijaDTO> convert(List<Rezervacija> rezervacijaList) {
		List<RezervacijaDTO> dtoList = new ArrayList<>();

		for (Rezervacija rezervacija : rezervacijaList) {
			dtoList.add(convert(rezervacija));
		}

		return dtoList;
	}

}