package com.wilczek.ra.task1.mapperEngine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.wilczek.ra.task1.mapperEngine.dto.ResourcesMappingsDTO;

@SpringBootTest(classes = { DefaultMappingService.class, ResourcesMappingsDTO.class })
class DefaultMappingServiceTest {

	public static final List<String> MAPPINGS_LIST = Arrays.asList("Animals", "Furnitures", "Test Group");
	public static final int[] MAPPINGS_SIZES_LIST = new int[] { 20, 20, 11 };

	public static final List<String> MAPPINGS_ANIMALS = Arrays.asList("Mouse", "Cat", "Dog", "Elephant",
			"Djungarian Hamster", "Pigeon", "Penguin", "Snkae", "Panda", "Bear", "Koala", "Hawk", "Eagle", "Rat",
			"Parrot", "Pig", "Guinea Pig", "Vietnamese Walkingstick", "Snail", "Unicorn");

	public static final List<String> MAPPINGS_TEST_GROUP = Arrays.asList("Test value 1", "Test value 2", "Test value 3",
			"Test value 4", "Test value 5", "Test value 6", "Test value 7", "Test value 8", "Test value 9",
			"Test value 10", "Test value 11");

	public static final List<String> SEPARATORS = Arrays.asList(", ", ":");

	@Autowired
	@Qualifier("defaultMappingService")
	private MappingService mappingService;

	@Test
	void getMappings_test() throws Exception {
		final List<String> mappings = mappingService.getMappings();
		assertLinesMatch(MAPPINGS_LIST, mappings);
	}

	@Test
	void mappingIdForName_test() throws Exception {
		for (int i = 0; i < MAPPINGS_LIST.size(); i++) {
			final String mappingName = MAPPINGS_LIST.get(i);
			final int mappingId = mappingService.mappingIdForName(mappingName);
			assertEquals(i, mappingId, "Mapping ID for name: " + mappingName);
		}
	}

	@Test
	void map_id_test() throws Exception {
		final int animalsId = 0;
		final int testGroupId = 2;

		for (int i = 0; i < MAPPINGS_ANIMALS.size(); i++) {
			final String mappedValue = mappingService.map(animalsId, i);
			assertEquals(MAPPINGS_ANIMALS.get(i), mappedValue);
		}

		for (int i = 0; i < MAPPINGS_TEST_GROUP.size(); i++) {
			final String mappedValue = mappingService.map(testGroupId, i);
			assertEquals(MAPPINGS_TEST_GROUP.get(i), mappedValue);
		}
	}

	@Test
	void map_name_test() throws Exception {
		final String animalsName = MAPPINGS_LIST.get(0);
		final String testGroupName = MAPPINGS_LIST.get(2);

		for (int i = 0; i < MAPPINGS_ANIMALS.size(); i++) {
			final String mappedValue = mappingService.map(animalsName, i);
			assertEquals(MAPPINGS_ANIMALS.get(i), mappedValue);
		}

		for (int i = 0; i < MAPPINGS_TEST_GROUP.size(); i++) {
			final String mappedValue = mappingService.map(testGroupName, i);
			assertEquals(MAPPINGS_TEST_GROUP.get(i), mappedValue);
		}
	}

	@Test
	void map_id_list_test() throws Exception {
		final int animalsId = 0;
		final int testGroupId = 2;

		final List<Integer> size20 = IntStream.range(0, 20).boxed().collect(Collectors.toList());
		final List<Integer> size11 = IntStream.range(0, 11).boxed().collect(Collectors.toList());

		final List<String> mappedAnimals = mappingService.map(animalsId, size20);
		final List<String> mappedTestGroup = mappingService.map(testGroupId, size11);

		assertLinesMatch(MAPPINGS_ANIMALS, mappedAnimals);
		assertLinesMatch(MAPPINGS_TEST_GROUP, mappedTestGroup);
	}

	@Test
	void map_name_list_test() throws Exception {
		final String animalsName = MAPPINGS_LIST.get(0);
		final String testGroupName = MAPPINGS_LIST.get(2);

		final List<Integer> size20 = IntStream.range(0, 20).boxed().collect(Collectors.toList());
		final List<Integer> size11 = IntStream.range(0, 11).boxed().collect(Collectors.toList());

		final List<String> mappedAnimals = mappingService.map(animalsName, size20);
		final List<String> mappedTestGroup = mappingService.map(testGroupName, size11);

		assertLinesMatch(MAPPINGS_ANIMALS, mappedAnimals);
		assertLinesMatch(MAPPINGS_TEST_GROUP, mappedTestGroup);
	}

	@Test
	void getMappingSize_test() throws Exception {
		for (int i = 0; i < MAPPINGS_LIST.size(); i++) {
			final int mappingSize = mappingService.getMappingSize(i);
			assertEquals(MAPPINGS_SIZES_LIST[i], mappingSize, "Size for mapping: " + MAPPINGS_LIST.get(i));
		}

		try {
			final int notExistingMappingId = MAPPINGS_LIST.size();
			mappingService.getMappingSize(notExistingMappingId);
			fail("No exception thrown for size of non existing mappingID: " + notExistingMappingId);
		} catch (final IndexOutOfBoundsException e) {
			// success
		}
	}

	@Test
	void mapAndJoinPrevious_id_test() throws Exception {
		final int animalsId = 0;

		final int[] numbers1 = new int[] { 0, 5, 3, 8 };
		final int[] numbers2 = new int[] { 1, 3, 5, 7, 11 };

		final List<Integer> numbersList1 = Arrays.stream(numbers1).boxed().collect(Collectors.toList());
		final List<Integer> numbersList2 = Arrays.stream(numbers2).boxed().collect(Collectors.toList());

		final List<int[]> subarays1 = generateSubArrays(numbers1);
		final List<int[]> subarays2 = generateSubArrays(numbers2);

		final List<List<String>> stringLists1 = subarays1
				.stream()
				.map(indexes -> elementsToJoin(indexes, MAPPINGS_ANIMALS))
				.collect(Collectors.toList());

		final List<List<String>> stringLists2 = subarays2
				.stream()
				.map(indexes -> elementsToJoin(indexes, MAPPINGS_ANIMALS))
				.collect(Collectors.toList());

		for (final String separator: SEPARATORS) {
			final List<String> joinedLists1 = stringLists1
					.stream()
					.map(l -> String.join(separator, l))
					.collect(Collectors.toList());
			final List<String> joinedLists2 = stringLists2
					.stream()
					.map(l -> String.join(separator, l))
					.collect(Collectors.toList());

			final List<String> result1 = mappingService.mapAndJoinPrevious(animalsId, numbersList1, separator);
			final List<String> result2 = mappingService.mapAndJoinPrevious(animalsId, numbersList2, separator);

			assertLinesMatch(joinedLists1, result1);
			assertLinesMatch(joinedLists2, result2);
		}
	}

	@Test
	void mapAndJoinPrevious_name_test() throws Exception {
		final String animalsName = MAPPINGS_LIST.get(0);

		final int[] numbers1 = new int[] { 0, 5, 3, 8 };
		final int[] numbers2 = new int[] { 1, 3, 5, 7, 11 };

		final List<Integer> numbersList1 = Arrays.stream(numbers1).boxed().collect(Collectors.toList());
		final List<Integer> numbersList2 = Arrays.stream(numbers2).boxed().collect(Collectors.toList());

		final List<int[]> subarays1 = generateSubArrays(numbers1);
		final List<int[]> subarays2 = generateSubArrays(numbers2);

		final List<List<String>> stringLists1 = subarays1
				.stream()
				.map(indexes -> elementsToJoin(indexes, MAPPINGS_ANIMALS))
				.collect(Collectors.toList());

		final List<List<String>> stringLists2 = subarays2
				.stream()
				.map(indexes -> elementsToJoin(indexes, MAPPINGS_ANIMALS))
				.collect(Collectors.toList());

		for (final String separator: SEPARATORS) {
			final List<String> joinedLists1 = stringLists1
					.stream()
					.map(l -> String.join(separator, l))
					.collect(Collectors.toList());
			final List<String> joinedLists2 = stringLists2
					.stream()
					.map(l -> String.join(separator, l))
					.collect(Collectors.toList());

			final List<String> result1 = mappingService.mapAndJoinPrevious(animalsName, numbersList1, separator);
			final List<String> result2 = mappingService.mapAndJoinPrevious(animalsName, numbersList2, separator);

			assertLinesMatch(joinedLists1, result1);
			assertLinesMatch(joinedLists2, result2);
		}
	}

	private List<int[]> generateSubArrays(final int[] in) {
		final ArrayList<int[]> result = new ArrayList<>();

		for (int i = 0; i < in.length; i++) {
			final int[] subArray = Arrays.copyOfRange(in, 0, i + 1);
			result.add(subArray);
		}

		return result;
	}

	private List<String> elementsToJoin(final int[] indexes, final List<String> elements) {
		final List<String> result = new ArrayList<>();

		for (final int index : indexes) {
			result.add(elements.get(index));
		}

		return result;
	}

}
