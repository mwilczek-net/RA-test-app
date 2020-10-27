package com.wilczek.ra.task1.mapperEngine.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ResourcesMappingsDTO.class })
class ResourcesMappingsDTOTest {

	public static final List<String> MAPPINGS_LIST = Arrays.asList("Animals", "Furnitures", "Test Group");
	public static final int[] MAPPINGS_SIZES_LIST = new int[] {20, 20, 11};

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
		for (int i = 0; i< MAPPINGS_LIST.size(); i++) {
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

}
