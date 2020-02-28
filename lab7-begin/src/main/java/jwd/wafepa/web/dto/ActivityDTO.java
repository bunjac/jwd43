package jwd.wafepa.web.dto;

import org.hibernate.validator.constraints.NotBlank;

public class ActivityDTO {
	private Long id;
	@NotBlank
	private String name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
