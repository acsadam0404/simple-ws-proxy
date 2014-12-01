package co.uk.mcb.service

import java.io.Serializable
import java.util.List

interface BaseService<T> extends Serializable {
	void save(T t)

	void delete(T t)

	List<T> findAll()

	T find(long id)
}
