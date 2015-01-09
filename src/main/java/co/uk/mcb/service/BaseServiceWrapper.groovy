package co.uk.mcb.service

import org.springframework.data.jpa.repository.JpaRepository

class BaseServiceWrapper<T> implements BaseService<T> {
	private JpaRepository<T, Long> crudRepo

	BaseServiceWrapper(Class crudRepoClass) {
		crudRepo = ServiceLocator.getBean((Class<JpaRepository>) crudRepoClass)
	}

	@Override
	void save(T t) {
		crudRepo.save(t)
	}

	@Override
	void delete(T t) {
		crudRepo.delete(t)
	}

	@Override
	List<T> findAll() {
		return crudRepo.findAll()
	}

	@Override
	T find(long id) {
		return crudRepo.findOne(id)
	}
}
