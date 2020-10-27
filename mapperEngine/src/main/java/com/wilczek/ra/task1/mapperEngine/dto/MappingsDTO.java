package com.wilczek.ra.task1.mapperEngine.dto;

import java.util.ArrayList;
import java.util.List;

public interface MappingsDTO {

	List<String> getMappings() throws Exception;

	int getMappingSize(int mappingId) throws Exception;

	String map(int mappingId, int number) throws Exception;

	default List<String> map(final int mappingId, final List<Integer> numbers) throws Exception {
		final ArrayList<String> resultMapping = new ArrayList<>();

		for(final Integer number: numbers) {
			final String mapping = map(mappingId, number.intValue());
			resultMapping.add(mapping);
		}

		return resultMapping;
	}

	int mappingIdForName(String mappingName);

}
