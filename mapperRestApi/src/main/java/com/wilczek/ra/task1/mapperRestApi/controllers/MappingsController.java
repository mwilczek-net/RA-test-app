package com.wilczek.ra.task1.mapperRestApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilczek.ra.task1.mapperEngine.service.MappingService;

@RestController
@RequestMapping("/api/mappings")
public class MappingsController {

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
}
