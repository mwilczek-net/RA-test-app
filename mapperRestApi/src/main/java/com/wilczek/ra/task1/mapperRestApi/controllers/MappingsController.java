package com.wilczek.ra.task1.mapperRestApi.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wilczek.ra.task1.mapperEngine.service.MappingService;

@RestController
@RequestMapping("/api/mappings")
public class MappingsController {

	public static final String DEFAULT_SEPARATOR = ",";

	@Autowired
	@Qualifier("defaultMappingService")
	private MappingService mappingService;

	@RequestMapping("/list")
	public List<String> mappingsList() throws Exception {
		return mappingService.getMappings();
	}

	@RequestMapping("/size/name/{mappingName}")
	public int mappingsSize(@PathVariable("mappingName") final String mappingName) throws Exception {
		return mappingService.getMappingSize(mappingName);
	}

	@RequestMapping("/size/id/{mappingId}")
	public int mappingsSize(@PathVariable("mappingId") final int mappingId) throws Exception {
		return mappingService.getMappingSize(mappingId);
	}

	@RequestMapping("/map/name/{mappingName}")
	public List<String> map(@PathVariable("mappingName") final String mappingName,
			@RequestParam("numbers") final String numbers,
			@RequestParam(name = "separator", required = false) final String separator
			) throws Exception {

		final List<Integer> numbersList = Arrays.asList(numbers.split(","))
				.stream()
				.map(number -> Integer.valueOf(number))
				.collect(Collectors.toList());

		final String selectedSeparator = separator != null ? separator : DEFAULT_SEPARATOR;

		return mappingService.mapAndJoinPrevious(mappingName, numbersList, selectedSeparator);
	}
}
