package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Prevoznik;
import jwd.test.service.PrevoznikService;
import jwd.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDTOToPrevoznik implements Converter<PrevoznikDTO, Prevoznik> {

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Prevoznik convert(PrevoznikDTO prevoznikDTO) {
		Prevoznik prev = null;
		if (prevoznikDTO.getId() != null) {
			prev = prevoznikService.findOne(prevoznikDTO.getId());
			if (prev == null) {
				return null;
			}
		} else {
			prev = new Prevoznik();
		}

		prev.setNaziv(prevoznikDTO.getNaziv());
		prev.setAdresa(prevoznikDTO.getAdresa());
		prev.setPib(prevoznikDTO.getPib());

		return prev;
	}

	public List<Prevoznik> convert(List<PrevoznikDTO> prevoznikDTOs) {
		List<Prevoznik> ret = new ArrayList<>();

		for (PrevoznikDTO prevoznikDTO : prevoznikDTOs) {
			ret.add(convert(prevoznikDTO));
		}

		return ret;
	}
}