package jwd.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.test.model.Rezervacija;
import jwd.test.web.dto.Model3DTO;

@Component
public class Model3ToModel3DTO implements Converter<Rezervacija, Model3DTO> {

	@Override
	public Model3DTO convert(Rezervacija rezervacija) {

		return null;
	}

	public List<Model3DTO> convert(List<Rezervacija> model3List) {
		List<Model3DTO> dtoList = new ArrayList<>();

		for (Rezervacija rezervacija : model3List) {
			dtoList.add(convert(rezervacija));
		}

		return dtoList;
	}

}