package jwd.wafepa;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.Record.Intesity;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@Autowired
	private RecordService recordService;

	@PostConstruct
	public void init2() {
		Activity activity1 = activityService.save(new Activity("Swimming"));
		Activity activity2 = activityService.save(new Activity("Running"));

		User user1 = new User();
		user1.setFirstName("First name " + 1);
		user1.setLastName("Last name " + 1);
		user1.setEmail("email" + 1 + "@example.com");
		user1.setPassword("123");
		user1 = userService.save(user1);

		User user2 = new User();
		user2.setFirstName("First name " + 2);
		user2.setLastName("Last name " + 2);
		user2.setEmail("email" + 2 + "@example.com");
		user2.setPassword("123");
		user2 = userService.save(user2);

		Record record1 = new Record();
		record1.setTime(new Timestamp(new Date("20/11/2018 08:00").getTime()));
		record1.setDuration(60);
		record1.setIntesity(Intesity.LOW);
		record1.setUser(user1);
		record1.setActivity(activity1);
		recordService.save(record1);

		Record record2 = new Record();
		record2.setTime(new Timestamp(new Date("21/11/2018 08:00").getTime()));
		record2.setDuration(60);
		record2.setIntesity(Intesity.MODERATE);
		record2.setUser(user2);
		record2.setActivity(activity2);
		recordService.save(record2);
	}

//	@PostConstruct
//	public void init() {
//		activityService.save(new Activity("Swimming"));
//		activityService.save(new Activity("Running"));
//		activityService.save(new Activity("Walking"));
//
//		// pravimo 5 korisnika
//		for (int i = 1; i <= 2; i++) {
//			User user = new User();
//			user.setFirstName("First name " + i);
//			user.setLastName("Last name " + i);
//			user.setEmail("email" + i + "@example.com");
//			user.setPassword("123");
//			userService.save(user);
//
//			// za svakog korisnika pravimo po 3 adrese
//			for (int j = 1; j <= 3; j++) {
//				Address address = new Address();
//				address.setNumber(Integer.toString(j));
//				address.setStreat("Laze nancica");
//
//				addressService.save(address);
//
//				user.addAddress(address);
//				userService.save(user);
//				addressService.save(address);
//
//			}
//		}
//
//	}
}
