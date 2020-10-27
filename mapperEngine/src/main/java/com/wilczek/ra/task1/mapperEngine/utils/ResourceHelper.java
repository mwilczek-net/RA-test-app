package com.wilczek.ra.task1.mapperEngine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResourceHelper {

	public static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

	public static final String READ_RESOURCE_ERROR_MESSAGE = "Can't read file: \"%s\"";
	public static final String READ_RESOURCE_TO_OBJECT_ERROR_MESSAGE = "Problem with parsing file: \"%s\" to Object: \"%s\"";
	public static final String MAPPING_STRING_TO_OBJECT_ERROR_MESSAGE = "Problem with parsing JSON String to Object: \"%s\"";

	private static final Logger LOG = LogManager.getLogger(ResourceHelper.class);

	public static String readResourceToString(final String resourcePath) throws IOException {
		try (final InputStream resourceStream = new ClassPathResource(resourcePath).getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream))) {
			final String fileContent = reader.lines().collect(Collectors.joining("\n"));
			return fileContent;
		} catch (final IOException e) {
			LOG.error(String.format(READ_RESOURCE_ERROR_MESSAGE, resourcePath));
			throw e;
		}
	}

	public static <T> T readResoureceToObject(final String resourcePath, final Class<T> type) throws IOException {
		try {
			final String fileContent = readResourceToString(resourcePath);
			return mapStringToObject(fileContent, type);
		} catch (final IOException e) {
			LOG.error(String.format(READ_RESOURCE_TO_OBJECT_ERROR_MESSAGE, resourcePath, type.getName()));
			throw e;
		}
	}

	public static <T> T mapStringToObject(final String content, final Class<T> type) throws JsonProcessingException {
		try {
			return DEFAULT_OBJECT_MAPPER.readValue(content, type);
		} catch (final JsonProcessingException e) {
			LOG.error(String.format(MAPPING_STRING_TO_OBJECT_ERROR_MESSAGE, type.getName()));
			throw e;
		}
	}
}
