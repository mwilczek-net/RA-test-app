package com.wilczek.ra.task1.mapperRestApi.mappings;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilczek.ra.task1.mapperEngine.utils.ResourceHelper;

@RestController
@RequestMapping("/api")
public class MappingsValues {

	@RequestMapping("/hello")
	public String helloWorld() throws IOException {
		return ResourceHelper.readResourceToString("html/welcome.html");
	}


}
