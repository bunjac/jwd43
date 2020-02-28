package jwd.wafepa.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Record {
	public enum Intesity {
		LOW, MODERATE, HIGH, EXTREME
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Timestamp time;

	@Column(nullable = false)
	private int duration;

	@Column
	@Enumerated
	private Intesity intesity;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Activity activity;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
