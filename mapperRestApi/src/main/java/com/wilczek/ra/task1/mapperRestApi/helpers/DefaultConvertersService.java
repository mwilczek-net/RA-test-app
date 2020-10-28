package com.wilczek.ra.task1.mapperRestApi.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wilczek.ra.task1.mapperRestApi.data.MappedValuesData;

@Service("defaultConvertersService")
@Primary
public class DefaultConvertersService implements ConvertersService {

	@Override
	public List<MappedValuesData> convertMappedResults(final List<Integer> numbers, final List<String> values) {
		final int numbersSize = numbers.size();
		final int valuesSize = values.size();
		final int maxSize = Math.max(numbersSize, valuesSize);

		final ArrayList<MappedValuesData> result = new ArrayList<>();

		for (int i = 0; i < maxSize; i++) {
			final MappedValuesData data = new MappedValuesData();
			data.setId(getItemOrNull(numbers, i));
			data.setValue(getItemOrNull(values, i));
			result.add(data);
		}

		return result;
	}

	protected <T> T getItemOrNull(final List<T> list, final int index) {
		try {
			return list.get(index);
		} catch (final IndexOutOfBoundsException e) {
			// Make it explicit (python -> import this)
			return null;
		}
	}
}
