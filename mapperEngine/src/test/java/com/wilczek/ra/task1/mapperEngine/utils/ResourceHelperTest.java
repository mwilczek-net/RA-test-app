package com.wilczek.ra.task1.mapperEngine.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class ResourceHelperTest {

	private static final Logger LOG = LogManager.getLogger(ResourceHelperTest.class);

	public static final String NON_EXISTING_FILE_PATH = "non-existing-file-path";
	public static final String EXISTING_FILE_PATH = "ResourceHelper/existingFile.txt";
	public static final String EXISTING_FILE_CONTENT = "Test File\nWith multi lines";

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

}
