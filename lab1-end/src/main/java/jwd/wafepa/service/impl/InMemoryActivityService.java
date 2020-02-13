package jwd.wafepa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.ActivityService;

public class InMemoryActivityService implements ActivityService {

	private Map<Long, Activity> activities = new HashMap<>();
	private long nextId = 1L;

	@Override
	public List<Activity> findAll() {
		return new ArrayList<>(activities.values());
	}

	@Override
	public Activity findOne(Long id) {
		return activities.get(id);
	}

	@Override
	public Activity save(Activity activity) {
		if (activity.getId() == null) {
			activity.setId(nextId);
			nextId += 1l;
		}
		activities.put(activity.getId(), activity);
		return activity;
	}

	@Override
	public Activity delete(Long id) {
		return activities.remove(id);
	}

	@Override
	public List<Activity> findByName(String name) {
		List<Activity> activitiesByName = new ArrayList<>();
		for (Activity a : activities.values()) {
			if (a.getName().equals(name)) {
				activitiesByName.add(a);
			}
		}
		return activitiesByName;
	}

	@Override
	public void save(List<Activity> activities) {
		for (Activity a : activities) {
			save(a);
		}
	}

	@Override
	public void remove(List<Long> activityIds) {
		for (Long id : activityIds) {
			delete(id);
		}
	}

}
