package com.wilczek.ra.task1.mapperEngine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.Arrays;
import java.util.List;

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

}
