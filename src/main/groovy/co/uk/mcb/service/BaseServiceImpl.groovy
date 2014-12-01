package co.uk.mcb.service

import java.util.List

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional(propagation = Propagation.REQUIRED)
abstract class BaseServiceImpl<T> implements BaseService<T> {
	private JpaRepository<T, Long> crudRepo

	BaseServiceImpl(JpaRepository<T, Long> crudRepo) {
		this.crudRepo = crudRepo
	}

	@Override
	void save(T t) {
		crudRepo.save(t)
	}

	@Override
	void delete(T t) {
		crudRepo.delete(t)
	}

	@Transactional(readOnly = true)
	@Override
	List<T> findAll() {
		return crudRepo.findAll()
	}

	@Transactional(readOnly = true)
	@Override
	T find(long id) {
		return crudRepo.findOne(id)
	}
}
