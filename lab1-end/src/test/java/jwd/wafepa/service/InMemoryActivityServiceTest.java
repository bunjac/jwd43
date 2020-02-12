package jwd.wafepa.service;

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
}
