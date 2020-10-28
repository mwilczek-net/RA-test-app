package com.wilczek.ra.task1.mapperEngine.service;

import java.util.List;

public interface MappingService {

	List<String> getMappings() throws Exception;

	String map(int mappingId, int number) throws Exception;

	String map(String mappingName, int number) throws Exception;

	List<String> map(int mappingId, List<Integer> numbers) throws Exception;

	List<String> map(String mappingName, List<Integer> numbers) throws Exception;

	List<String> mapAndJoinPrevious(int mappingId, List<Integer> numbers, String separator) throws Exception;

	List<String> mapAndJoinPrevious(String mappingName, List<Integer> numbers, String separator) throws Exception;

	int getMappingSize(int mappingId) throws Exception;

	int getMappingSize(String mappingName) throws Exception;

	int mappingIdForName(String mappingName) throws Exception;

}
