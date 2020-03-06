package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Linija;
import jwd.test.model.Prevoznik;
import jwd.test.service.LinijaService;
import jwd.test.service.PrevoznikService;
import jwd.test.web.dto.LinijaDTO;

@Component
public class LinijaDTOToLinija implements Converter<LinijaDTO, Linija> {

	@Autowired
	private LinijaService linijaService;

	@Autowired
	private PrevoznikService prevoznikService;

	@Override
	public Linija convert(LinijaDTO linijaDTO) {
		Prevoznik prevoznik = prevoznikService.findOne(linijaDTO.getPrevoznikId());
		if (prevoznik != null) {
			Linija linija = null;
			if (linijaDTO.getId() != null) {
				linija = linijaService.findOne(linijaDTO.getId());
			} else {
				linija = new Linija();
			}

			linija.setId(linijaDTO.getId());
			linija.setBrojMesta(linijaDTO.getBrojMesta());
			linija.setCenaKarte(linijaDTO.getCenaKarte());
			linija.setVremePolaska(linijaDTO.getVremePolaska());
			linija.setDestinacija(linijaDTO.getDestinacija());
			linija.setPrevoznik(prevoznik);

			return linija;
		}

		return null;
	}

	public List<Linija> convert(List<LinijaDTO> linijaDTOs) {
		List<Linija> ret = new ArrayList<>();

		for (LinijaDTO linijaDTO : linijaDTOs) {
			ret.add(convert(linijaDTO));
		}

		return ret;
	}
}