package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityToActivityDTO implements Converter<Activity, ActivityDTO> {

	@Override
	public ActivityDTO convert(Activity source) {
		ActivityDTO dto = new ActivityDTO();
		dto.setId(source.getId());
		dto.setName(source.getName());
		return dto;
	}

	public List<ActivityDTO> convert(List<Activity> sourceList) {
		List<ActivityDTO> dtoList = new ArrayList<>();
		if (sourceList != null) {
			for (Activity source : sourceList) {
				ActivityDTO dto = convert(source);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}

}
