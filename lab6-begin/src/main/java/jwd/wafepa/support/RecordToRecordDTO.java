package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordToRecordDTO implements Converter<Record, RecordDTO> {

	@Autowired
	private UserToUserDTO toUserDto;

	@Autowired
	private ActivityToActivityDTO toActivityDto;

	@Override
	public RecordDTO convert(Record record) {
		if (record == null) {
			return null;
		}

		RecordDTO dto = new RecordDTO();

		dto.setId(record.getId());
		dto.setTime(record.getTime());
		dto.setDuration(record.getDuration());
		dto.setIntesity(record.getIntesity());
		dto.setUserDto(toUserDto.convert(record.getUser()));
		dto.setActivityDto(toActivityDto.convert(record.getActivity()));

		return dto;
	}

	public List<RecordDTO> convert(List<Record> records) {
		List<RecordDTO> dtos = new ArrayList<RecordDTO>();

		for (Record r : records) {
			dtos.add(convert(r));
		}

		return dtos;
	}

}
