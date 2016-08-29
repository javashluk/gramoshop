package tfzr.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tfzr.store.model.Role;
import tfzr.store.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);
	
	@Query("SELECT user FROM tfzr.store.model.User user WHERE user.role = ?1")
	List<User> findAllUnapproved(Role role);

}
