package com.revo.core.persistence;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public final class PersistableTransformer {

	private PersistableTransformer() {
	}

	public static <T extends Persistable<Integer>, K extends Persistable<Integer>> Map<Integer, T> loadDependencies(List<K> list,
	                                                                                                                Function<K, Integer> mapper,
	                                                                                                                Function<List<Integer>, List<T>> dao) {
		final var ids = list.stream().map(mapper).collect(toList());
		return dao.apply(ids).stream().collect(toMap(Persistable::getId, value -> value));
	}

	public static <T extends Persistable<Integer>, K extends Persistable<Integer>> Map<Integer, T> loadDependenciesAsMap(List<K> list,
																													Function<K, Integer> mapper,
																													Function<List<Integer>, Map<Integer, T>> dao) {
		final var ids = list.stream().map(mapper).collect(toList());
		return dao.apply(ids);
	}

}
