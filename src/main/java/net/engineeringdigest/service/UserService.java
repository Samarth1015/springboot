package net.engineeringdigest.service;

import net.engineeringdigest.Repo.JournalRepo;
import net.engineeringdigest.Repo.UserRepo;
import net.engineeringdigest.config.SpringSecurity;
import net.engineeringdigest.model.Journal;
import net.engineeringdigest.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo ur;
    @Autowired
    private JournalRepo jr;

    public List<User> getAllUser(){
      return  ur.findAll();

    }
    private static  final PasswordEncoder pswd= new BCryptPasswordEncoder();
    public Boolean addUser(User newUser){
        try{
            newUser.setPassword(  pswd.encode( newUser.getPassword()));
        ur.save(newUser);
        return true;
        }
        catch (Exception err){
            log.error("---->",err);
            return false;
        }

    }
    public User getUserByUsername(String username){
        return  ur.findByUsername(username);

    }

@Transactional
    public Boolean deleteUser(String username){
        try {

           jr.deleteByUserUsername(username);
            System.out.println("--->done");
             ur.deleteByUsername(username);
             return  true;

        }catch (Exception err){
            log.error("err"+err);
            throw new RuntimeException("no user To delete");
        }

    }

}
