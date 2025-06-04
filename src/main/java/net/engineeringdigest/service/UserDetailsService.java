package net.engineeringdigest.service;


import net.engineeringdigest.Repo.UserRepo;
import net.engineeringdigest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

@Autowired
    UserRepo ur;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = ur.findByUsername(username);
            if(user!=null){
              return   org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword()).
                        roles(user.getRole())
                        .build();


            }


        throw new UsernameNotFoundException("username not found");
    }
}
