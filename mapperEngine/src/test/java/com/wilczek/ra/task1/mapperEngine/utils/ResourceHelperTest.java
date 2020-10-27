package com.wilczek.ra.task1.mapperEngine.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.wilczek.ra.task1.mapperEngine.data.MappingData;
import com.wilczek.ra.task1.mapperEngine.data.MappingsData;

class ResourceHelperTest {

	private static final Logger LOG = LogManager.getLogger(ResourceHelperTest.class);

	public static final String NON_EXISTING_FILE_PATH = "non-existing-file-path";
	public static final String EXISTING_FILE_PATH = "ResourceHelper/existingFile.txt";
	public static final String EXISTING_FILE_CONTENT = "Test File\nWith multi lines";

	public static final String JSON_FILE_PATH = "mappings/mappings.json";

	public static final List<String> JSON_MAPPINGS_LIST = Arrays.asList("Animals", "Furnitures", "Test Group");

	@Test
	void readResourceToString_test() throws IOException {
		try {
			ResourceHelper.readResourceToString(NON_EXISTING_FILE_PATH);
			fail("Didn't failed on non existing path");
		} catch (final IOException e) {
			// success
		}

		try {
			final String existingFileContent = ResourceHelper.readResourceToString(EXISTING_FILE_PATH);
			assertEquals(EXISTING_FILE_CONTENT, existingFileContent);
		} catch (final IOException e) {
			// Make tests more verbose
			LOG.error("Didn't find file", e);
			throw e;
		}
	}

	@Test
	void readResoureceToObject_test() throws IOException {
		MappingsData mappingsDataResult;
		try {
			mappingsDataResult = ResourceHelper.readResoureceToObject(JSON_FILE_PATH, MappingsData.class);
		} catch (final IOException e) {
			// Make tests more verbose
			LOG.error("Didn't find file", e);
			throw e;
		}

		assertNotNull(mappingsDataResult);
		assertNotNull(mappingsDataResult.getMappings());
		final List<MappingData> mappingsListResult = mappingsDataResult.getMappings();
		assertEquals(3, mappingsListResult.size());

		final MappingData mappingData0 = mappingsListResult.get(0);
		final MappingData mappingData1 = mappingsListResult.get(1);
		final MappingData mappingData2 = mappingsListResult.get(2);

		assertEquals(JSON_MAPPINGS_LIST.get(0), mappingData0.getName());
		assertEquals(JSON_MAPPINGS_LIST.get(1), mappingData1.getName());
		assertEquals(JSON_MAPPINGS_LIST.get(2), mappingData2.getName());

		assertNotNull(mappingData0.getValues());
		assertNotNull(mappingData1.getValues());
		assertNotNull(mappingData2.getValues());

		assertEquals(20, mappingData0.getValues().size());
		assertEquals(20, mappingData1.getValues().size());
		assertEquals(11, mappingData2.getValues().size());
	}

}
