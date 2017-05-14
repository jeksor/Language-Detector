package com.esorokin.lantector.model.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class MapperUtils {
	private MapperUtils() {}

	/**
	 * Convert collection of dto objects to app model collection.
	 *
	 * @param fromCollection Dto collection.
	 * @param itemMapper     Mapper for single object.
	 * @param <From>         Type of Dto model.
	 * @param <To>           Type of App model.
	 * @return Collection of app model.
	 */
	@SuppressWarnings("TypeParameterHidesVisibleType")
	public static <From, To> List<To> convertCollection(Collection<From> fromCollection, Mapper<From, To> itemMapper) {
		final List<To> result = new ArrayList<>();

		if (fromCollection != null) {
			for (From fromItem : fromCollection) {
				final To converted = itemMapper.convert(fromItem);
				if (converted != null) {
					result.add(converted);
				}
			}
		}

		return result;
	}

	public static <From, To, Dependency> List<To> convertCollection(Collection<From> fromCollection,
	                                                                Dependency dependency,
	                                                                LinkedMapper<From, To, Dependency> itemMapper) {
		final List<To> result = new ArrayList<>();

		if (fromCollection != null) {
			for (From fromItem : fromCollection) {
				final To converted = itemMapper.convert(fromItem, dependency);
				if (converted != null) {
					result.add(converted);
				}
			}
		}

		return result;
	}
}
