package com.revo.core.persistence;

public interface Persistable<T> {

	T getId();

	void setId(T id);

}
