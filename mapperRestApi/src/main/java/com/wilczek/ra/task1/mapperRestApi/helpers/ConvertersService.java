package com.wilczek.ra.task1.mapperRestApi.helpers;

import java.util.List;

import com.wilczek.ra.task1.mapperRestApi.data.MappedValuesData;

public interface ConvertersService {

	List<MappedValuesData> convertMappedResults(List<Integer> numbers, List<String> results);
}
