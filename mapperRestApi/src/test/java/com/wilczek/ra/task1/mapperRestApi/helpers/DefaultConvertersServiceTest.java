package com.wilczek.ra.task1.mapperRestApi.helpers;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wilczek.ra.task1.mapperRestApi.data.MappedValuesData;

@SpringBootTest(classes = { DefaultConvertersService.class })
class DefaultConvertersServiceTest {

	@Autowired
	protected ConvertersService convertersService;

	@Test
	void convertMappedResults_test() {
		final List<String> inputStringsShort = Arrays.asList("a", "b", "c", "dd", "ee");
		final List<Integer> inputIntShort = Arrays.stream(new int[] {1, 2, 3, 4, 5}).boxed().collect(Collectors.toList());

		final List<String> inputStringsLong = Arrays.asList("c", "a", "z", "g", "j", "w", "ala", "je", "koty");
		final List<Integer> inputIntLong = Arrays.stream(new int[] {5, 11, 20, 0, 2, 6, 1, 100, 2}).boxed().collect(Collectors.toList());

		final List<MappedValuesData> shortStringsShortInt = convertersService.convertMappedResults(inputIntShort, inputStringsShort);
		final List<MappedValuesData> longStringsLongInt = convertersService.convertMappedResults(inputIntLong, inputStringsLong);
		final List<MappedValuesData> shortStringsLongInt = convertersService.convertMappedResults(inputIntLong, inputStringsShort);
		final List<MappedValuesData> longStringsShortInt = convertersService.convertMappedResults(inputIntShort, inputStringsLong);

		assertLinesMatch(inputStringsShort, shortStringsShortInt.stream().map(MappedValuesData::getValue).collect(Collectors.toList()));
		assertLinesMatch(inputStringsLong, longStringsLongInt.stream().map(MappedValuesData::getValue).collect(Collectors.toList()));
		assertLinesMatch(inputStringsShort, shortStringsLongInt.stream().map(MappedValuesData::getValue).collect(Collectors.toList()));
		assertLinesMatch(inputStringsLong, longStringsShortInt.stream().map(MappedValuesData::getValue).collect(Collectors.toList()));

		assertIterableEquals(inputIntShort, shortStringsShortInt.stream().map(MappedValuesData::getId).collect(Collectors.toList()));
		assertIterableEquals(inputIntLong, longStringsLongInt.stream().map(MappedValuesData::getId).collect(Collectors.toList()));
		assertIterableEquals(inputIntLong, shortStringsLongInt.stream().map(MappedValuesData::getId).collect(Collectors.toList()));
		assertIterableEquals(inputIntShort, longStringsLongInt.stream().map(MappedValuesData::getId).collect(Collectors.toList()));
	}

}
