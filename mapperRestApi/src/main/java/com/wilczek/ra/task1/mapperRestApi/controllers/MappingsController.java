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
import com.wilczek.ra.task1.mapperRestApi.data.MappedValuesData;
import com.wilczek.ra.task1.mapperRestApi.helpers.ConvertersService;

@RestController
@RequestMapping("/api/mappings")
public class MappingsController {

	public static final String DEFAULT_SEPARATOR = ",";

	@Autowired
	@Qualifier("defaultMappingService")
	private MappingService mappingService;

	@Autowired
	@Qualifier("defaultConvertersService")
	protected ConvertersService convertersService;

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
	public List<MappedValuesData> map(@PathVariable("mappingName") final String mappingName,
			@RequestParam("numbers") final String numbers,
			@RequestParam(name = "separator", required = false) final String separator
			) throws Exception {

		final List<Integer> numbersList = convertStringOfIntsToList(numbers);
		final String selectedSeparator = separator != null ? separator : DEFAULT_SEPARATOR;

		final List<String> mappedValues = mappingService.mapAndJoinPrevious(mappingName, numbersList, selectedSeparator);
		return convertersService.convertMappedResults(numbersList, mappedValues);
	}

	@RequestMapping("/map/id/{mappingId}")
	public List<MappedValuesData> map(@PathVariable("mappingId") final int mappingId,
			@RequestParam("numbers") final String numbers,
			@RequestParam(name = "separator", required = false) final String separator
			) throws Exception {

		final List<Integer> numbersList = convertStringOfIntsToList(numbers);
		final String selectedSeparator = separator != null ? separator : DEFAULT_SEPARATOR;

		final List<String> mappedValues = mappingService.mapAndJoinPrevious(mappingId, numbersList, selectedSeparator);
		return convertersService.convertMappedResults(numbersList, mappedValues);
	}

	public List<Integer> convertStringOfIntsToList(final String input) {
		return Arrays.asList(input.split(","))
				.stream()
				.map(number -> Integer.valueOf(number))
				.collect(Collectors.toList());
	}
}
