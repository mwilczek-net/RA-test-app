package com.wilczek.ra.task1.mapperEngine.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public interface MappingsDTO {

	List<String> getMappings() throws Exception;

	int getMappingSize(int mappingId) throws Exception;

	String map(int mappingId, int number);

	default List<String> map(final int mappingId, final List<Integer> numbers) {
		return CollectionUtils.emptyIfNull(numbers)
				.stream()
				.mapToInt(Integer::intValue)
				.mapToObj(number -> map(mappingId, number))
				.collect(Collectors.toList());
	}

}
