package net.engineeringdigest.Repo;

import net.engineeringdigest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);

}
