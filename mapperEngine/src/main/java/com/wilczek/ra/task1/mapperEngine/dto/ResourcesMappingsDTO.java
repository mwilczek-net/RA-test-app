package com.wilczek.ra.task1.mapperEngine.dto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilczek.ra.task1.mapperEngine.data.MappingData;
import com.wilczek.ra.task1.mapperEngine.data.MappingsData;
import com.wilczek.ra.task1.mapperEngine.utils.ResourceHelper;

@Service("resourcesMappingsDTO")
@Primary
public class ResourcesMappingsDTO implements MappingsDTO {

	private static final Logger LOG = LogManager.getLogger(ResourcesMappingsDTO.class);

	@Value("${resources.mappings.file:none}")
	private String mappingsFileUrl;

	private MappingsData mappingsData = null;

	@Override
	public List<String> getMappings() {
		try {
			return CollectionUtils.emptyIfNull(getMappingsData().getMappings())
					.stream()
					.map(MappingData::getName)
					.collect(Collectors.toList());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String map(final int mappingId, final int number) {
		// TODO Auto-generated method stub
		return null;
	}

	public MappingsData getMappingsData() throws IOException {
		if (mappingsData == null) {
			final String mappingsJson = ResourceHelper.readResourceToString(mappingsFileUrl);
			final ObjectMapper objectMapper = new ObjectMapper();
			mappingsData = objectMapper.readValue(mappingsJson, MappingsData.class);
		}

		return mappingsData;
	}

}
