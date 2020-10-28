package com.wilczek.ra.task1.mapperRestApi.helpers;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wilczek.ra.task1.mapperRestApi.data.MappedValuesData;


@Service("defaultConvertersService")
@Primary
public class DefaultConvertersService implements ConvertersService{

	@Override
	public List<MappedValuesData> convertMappedResults(final List<Integer> numbers, final List<String> results) {
		// TODO Auto-generated method stub
		return null;
	}

}
