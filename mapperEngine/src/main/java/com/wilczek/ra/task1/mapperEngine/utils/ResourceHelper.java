package com.wilczek.ra.task1.mapperEngine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class ResourceHelper {

	public static final String READ_RESOURCE_EXCEPTION_MESSAGE = "Can't read file: \"%s\"";

	private static final Logger LOG = LogManager.getLogger(ResourceHelper.class);

	public static String readResourceToString(final String resourcePath) throws IOException {
		try (final InputStream resourceStream = new ClassPathResource(resourcePath).getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
			final String fileContent = reader.lines().collect(Collectors.joining("\n"));
			return fileContent;
		} catch (final IOException e) {
			LOG.error(String.format(READ_RESOURCE_EXCEPTION_MESSAGE, resourcePath));
			throw e;
		}
	}
}
