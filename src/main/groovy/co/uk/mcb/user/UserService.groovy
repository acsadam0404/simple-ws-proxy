package co.uk.mcb.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserService extends JpaRepository<User, Long> {
	User findByUsername(String username)
}
 