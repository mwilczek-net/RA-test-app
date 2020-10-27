package com.wilczek.ra.task1.mapperEngine.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("resourcesMappingsDTO")
@Primary
public class ResourcesMappingsDTO implements MappingsDTO {

	@Value("${resources.mappings.file}")
	private String mappingsFileUrl;

	@Override
	public List<String> getMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String map(final int mappingId, final int number) {
		// TODO Auto-generated method stub
		return null;
	}

}
