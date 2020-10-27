package com.wilczek.ra.task1.mapperEngine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wilczek.ra.task1.mapperEngine.dto.MappingsDTO;

@Service("defaultMappingService")
@Primary
public class DefaultMappingService implements MappingService {

	@Autowired
	@Qualifier("resourcesMappingsDTO")
	private MappingsDTO mappingsDTO;

	@Override
	public List<String> getMappings() throws Exception {
		return mappingsDTO.getMappings();
	}

	@Override
	public String map(final int mappingId, final int number) throws Exception {
		return mappingsDTO.map(mappingId, number);
	}

	@Override
	public String map(final String mappingName, final int number) throws Exception {
		final int mappingId = mappingIdForName(mappingName);
		return map(mappingId, number);
	}

	@Override
	public List<String> map(final int mappingId, final List<Integer> numbers) throws Exception {
		return mappingsDTO.map(mappingId, numbers);
	}

	@Override
	public List<String> map(final String mappingName, final List<Integer> numbers) throws Exception {
		final int mappingId = mappingIdForName(mappingName);
		return map(mappingId, numbers);
	}
	@Override
	public List<String> mapAndJoinPrevious(final int mappingId, final List<Integer> numbers, final String separator) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> mapAndJoinPrevious(final String mappingName, final List<Integer> numbers, final String separator) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMappingSize(final int mappingId) throws Exception {
		return mappingsDTO.getMappingSize(mappingId);
	}

	@Override
	public int mappingIdForName(final String mappingName) throws Exception {
		return mappingsDTO.mappingIdForName(mappingName);
	}



}
