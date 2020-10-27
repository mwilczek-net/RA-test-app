package com.wilczek.ra.task1.mapperEngine.dto;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { ResourcesMappingsDTO.class })
class ResourcesMappingsDTOTest {

	public static final List<String> MAPPINGS_LIST = Arrays.asList("Animals", "Furnitures");

	@Autowired
	@Qualifier("resourcesMappingsDTO")
	private ResourcesMappingsDTO mappingsDTO;

	@Test
	void getMappings_test() {
		final List<String> mappings = mappingsDTO.getMappings();
		assertLinesMatch(MAPPINGS_LIST, mappings);
	}

}
