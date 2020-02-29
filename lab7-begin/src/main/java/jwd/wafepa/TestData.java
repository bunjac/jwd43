package jwd.wafepa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Address;
import jwd.wafepa.model.Record;
import jwd.wafepa.model.Record.Intesity;
import jwd.wafepa.model.User;
import jwd.wafepa.service.ActivityService;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.service.RecordService;
import jwd.wafepa.service.UserService;

@Component
public class TestData {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private RecordService recordService;

	@PostConstruct
	public void init() {
		Random random = new Random();

		Activity[] activities = { activityService.save(new Activity("Swimming")),
				activityService.save(new Activity("Running")) };

		// pravimo 5 korisnika
		for (int i = 1; i <= 25; i++) {
			User user = new User();
			user.setFirstName("First name " + i);
			user.setLastName("Last name " + i);
			user.setEmail("email" + i + "@example.com");
			user.setPassword("123");
			userService.save(user);

			// za svakog korisnika pravimo po 3 adrese
			for (int j = 1; j <= 3; j++) {
				Address address = new Address();
				address.setNumber(Integer.toString(j));
				address.setStreat("Laze nancica");

				addressService.save(address);

				user.addAddress(address);
				userService.save(user);
				addressService.save(address);

			}

			// za svakog korisnika pravimo po 2 recorda
			for (int k = 1; k <= 2; k++) {
				Record record = new Record();
				record.setTime(new Timestamp(new Date("20/11/2018 08:00").getTime()));
				record.setDuration(random.nextInt(500) + 1);
				record.setIntesity(Intesity.values()[random.nextInt(4)]);
				record.setUser(user);
				Activity activity = activities[random.nextInt(2)];
				record.setActivity(activity);
				recordService.save(record);
				userService.save(user);
				activityService.save(activity);
			}
		}

	}
}
