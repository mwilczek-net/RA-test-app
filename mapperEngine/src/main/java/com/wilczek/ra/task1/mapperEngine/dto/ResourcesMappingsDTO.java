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
	public List<String> getMappings() throws Exception {
		try {
			return CollectionUtils.emptyIfNull(getMappingsData().getMappings())
					.stream()
					.map(MappingData::getName)
					.collect(Collectors.toList());
		} catch (final IOException e) {
			LOG.error(e);
			throw e;
		}
	}

	@Override
	public int getMappingSize(final int mappingId) throws Exception {
		try {
			return getMappingsData()
					.getMappings()
					.get(mappingId)
					.getValues()
					.size();
		} catch (final IOException e) {
			LOG.error(e);
			throw e;
		}
	}

	@Override
	public String map(final int mappingId, final int number) throws Exception {
		try {
			return getMappingsData()
					.getMappings()
					.get(mappingId)
					.getValues()
					.get(number);
		} catch (final Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	@Override
	public int mappingIdForName(final String mappingName) throws Exception {
		try {
			return getMappings().indexOf(mappingName);
		} catch (final Exception e) {
			LOG.error(e);
			throw e;
		}
	}

	public MappingsData getMappingsData() throws IOException {
		if (mappingsData == null) {
			mappingsData = ResourceHelper.readResoureceToObject(mappingsFileUrl, MappingsData.class);
		}

		return mappingsData;
	}

}
