package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityDTOToActivity implements Converter<ActivityDTO, Activity> {

	@Autowired
	private ActivityService activityService;

	@Override
	public Activity convert(ActivityDTO source) {
		Activity activity;
		if (source.getId() == null) {
			activity = new Activity();
		} else {
			activity = activityService.findOne(source.getId());
		}
		activity.setId(source.getId());
		activity.setName(source.getName());

		return activity;
	}

	public List<Activity> convert(List<ActivityDTO> sourceList) {
		List<Activity> actList = new ArrayList<>();
		if (sourceList != null) {
			for (ActivityDTO source : sourceList) {
				Activity activity = convert(source);
				actList.add(activity);
			}
			return actList;
		}
		return null;
	}
}
