package co.uk.mcb.user

import groovy.transform.EqualsAndHashCode;

import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

import com.sun.org.glassfish.gmbal.IncludeSubclass;

import co.uk.mcb.domain.BaseEntity

@Entity
@Table(name = 'user')
@EqualsAndHashCode(includes = ["username"])
class User extends BaseEntity {
	@NotNull
	String username

	String password

	String firstName

	String lastName

	Date dateCreated

	@Override
	String toString() {
		return username
	}
}
