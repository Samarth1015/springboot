package net.engineeringdigest.Repo;

import net.engineeringdigest.model.Journal;

import net.engineeringdigest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public interface JournalRepo extends JpaRepository <Journal,Long>{
    public List<Journal> findByUserUsername(String username);
    @Transactional
    @Modifying
    @Query("DELETE FROM Journal j WHERE j.user.id = (SELECT u.id FROM User u WHERE u.username = :username)")
    void deleteByUserUsername(@Param("username") String username);

}
