package com.wilczek.ra.task1.mapperEngine.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ResourcesMappingsDTO.class })
class ResourcesMappingsDTOTest {

	public static final List<String> MAPPINGS_LIST = Arrays.asList("Animals", "Furnitures", "Test Group");
	public static final int[] MAPPINGS_SIZES_LIST = new int[] { 20, 20, 11 };

	public static final List<String> MAPPINGS_ANIMALS = Arrays.asList("Mouse", "Cat", "Dog", "Elephant",
			"Djungarian Hamster", "Pigeon", "Penguin", "Snkae", "Panda", "Bear", "Koala", "Hawk", "Eagle", "Rat",
			"Parrot", "Pig", "Guinea Pig", "Vietnamese Walkingstick", "Snail", "Unicorn");

	public static final List<String> MAPPINGS_TEST_GROUP = Arrays.asList("Test value 1", "Test value 2", "Test value 3",
			"Test value 4", "Test value 5", "Test value 6", "Test value 7", "Test value 8", "Test value 9",
			"Test value 10", "Test value 11");

	@Autowired
	@Qualifier("resourcesMappingsDTO")
	private ResourcesMappingsDTO mappingsDTO;

	@Test
	void getMappings_test() throws Exception {
		final List<String> mappings = mappingsDTO.getMappings();
		assertLinesMatch(MAPPINGS_LIST, mappings);
	}

	@Test
	void getMappingSize_test() throws Exception {
		for (int i = 0; i < MAPPINGS_LIST.size(); i++) {
			final int mappingSize = mappingsDTO.getMappingSize(i);
			assertEquals(MAPPINGS_SIZES_LIST[i], mappingSize, "Size for mapping: " + MAPPINGS_LIST.get(i));
		}

		try {
			final int notExistingMappingId = MAPPINGS_LIST.size();
			mappingsDTO.getMappingSize(notExistingMappingId);
			fail("No exception thrown for size of non existing mappingID: " + notExistingMappingId);
		} catch (final IndexOutOfBoundsException e) {
			// success
		}
	}

	@Test
	void map_test() throws Exception {
		final int animalsId = 0;
		final int testGroupId = 2;

		for (int i = 0; i < MAPPINGS_ANIMALS.size(); i++) {
			final String mappedValue = mappingsDTO.map(animalsId, i);
			assertEquals(MAPPINGS_ANIMALS.get(i), mappedValue);
		}

		for (int i = 0; i < MAPPINGS_TEST_GROUP.size(); i++) {
			final String mappedValue = mappingsDTO.map(testGroupId, i);
			assertEquals(MAPPINGS_TEST_GROUP.get(i), mappedValue);
		}
	}

	@Test
	void map_list_test() throws Exception {
		final int animalsId = 0;
		final int testGroupId = 2;

		final List<Integer> size20 = IntStream.range(0, 20).boxed().collect(Collectors.toList());
		final List<Integer> size11 = IntStream.range(0, 11).boxed().collect(Collectors.toList());

		final List<String> mappedAnimals = mappingsDTO.map(animalsId, size20);
		final List<String> mappedTestGroup = mappingsDTO.map(testGroupId, size11);

		assertLinesMatch(MAPPINGS_ANIMALS, mappedAnimals);
		assertLinesMatch(MAPPINGS_TEST_GROUP, mappedTestGroup);
	}

	@Test
	void mappingIdForName_test() {

	}

}
