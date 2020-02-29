package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Record;
import jwd.wafepa.web.dto.RecordDTO;

@Component
public class RecordDTOToRecord implements Converter<RecordDTO, Record> {

	@Autowired
	private UserDTOToUser toUser;

	@Autowired
	private ActivityDTOToActivity toActivity;

	@Override
	public Record convert(RecordDTO dto) {
		if (dto == null) {
			return null;
		}

		Record record = new Record();

		record.setId(dto.getId());
		record.setTime(dto.getTime());
		record.setDuration(dto.getDuration());
		record.setIntesity(dto.getIntesity());
		record.setUser(toUser.convert(dto.getUserDto()));
		record.setActivity(toActivity.convert(dto.getActivityDto()));

		return record;
	}

	public List<Record> convert(List<RecordDTO> dtos) {
		List<Record> records = new ArrayList<Record>();

		for (RecordDTO dto : dtos) {
			records.add(convert(dto));
		}

		return records;
	}

}
