package jwd.wafepa.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {
	private ActivityService activityService;

	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();

		Activity a1 = new Activity("Swimming");
		Activity a2 = new Activity("Running");

		activityService.save(a1);
		activityService.save(a2);
	}

	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(2L);
		Assert.assertEquals("Running", a.getName());
	}

	@Test
	public void testFindAll() {
		List<Activity> result = activityService.findAll();
		Assert.assertEquals(2, result.size());
	}

	@Test
	public void testfindByName() {
		// TODO
		List<Activity> activities = activityService.findByName("swimming");
		Assert.assertEquals(0, activities.size());
		activities = activityService.findByName("Swimming");
		Assert.assertEquals(1, activities.size());
	}

	@Test
	public void testSave() {
		Activity a3 = new Activity("Relaxing");
		Activity a4 = new Activity("Walking");
		List<Activity> activities = Arrays.asList(a3, a4);
		activityService.save(activities);
		Assert.assertEquals(4, activityService.findAll().size());
	}

	@Test
	public void testRemove() {
		List<Long> activitiesToRemove = Arrays.asList(3L, 4L);
		activityService.remove(activitiesToRemove);
		Assert.assertEquals(2, activityService.findAll().size());
		activitiesToRemove = Arrays.asList(1L, 4L);
		activityService.remove(activitiesToRemove);
		Assert.assertEquals(1, activityService.findAll().size());
	}

}

//List findByName(String name) - treba da pronađe Activity objekat sa zadatim imenom