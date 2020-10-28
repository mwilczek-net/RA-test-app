package com.wilczek.ra.task1.mapperRestApi.mappings;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilczek.ra.task1.mapperEngine.service.MappingService;
import com.wilczek.ra.task1.mapperEngine.utils.ResourceHelper;

@RestController
@RequestMapping("/api")
public class MappingsValuesController {

	@Autowired
	@Qualifier("defaultMappingService")
	private MappingService mappingService;

	@RequestMapping("/hello")
	public String helloWorld() throws IOException {
		return ResourceHelper.readResourceToString("html/welcome.html");
	}

	@RequestMapping("/mappingsList")
	public List<String> mappingsList() throws Exception {
		return mappingService.getMappings();
	}


}
