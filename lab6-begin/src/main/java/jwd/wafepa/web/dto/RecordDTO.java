package jwd.wafepa.web.dto;

import java.sql.Timestamp;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Record.Intesity;
import jwd.wafepa.model.User;

public class RecordDTO {
	private Long id;
	private Timestamp time;
	private int duration;
	private Intesity intesity;
	private UserDTO userDto;
	private ActivityDTO activityDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Intesity getIntesity() {
		return intesity;
	}

	public void setIntesity(Intesity intesity) {
		this.intesity = intesity;
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public ActivityDTO getActivityDto() {
		return activityDto;
	}

	public void setActivityDto(ActivityDTO activityDto) {
		this.activityDto = activityDto;
	}

	

}
