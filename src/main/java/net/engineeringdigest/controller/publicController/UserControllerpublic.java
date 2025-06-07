package net.engineeringdigest.controller.publicController;

import net.bytebuddy.implementation.bytecode.Throw;
import net.engineeringdigest.model.User;
import net.engineeringdigest.service.JwtService;
import net.engineeringdigest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/user")
public class UserControllerpublic {
    @Autowired
    private UserService us;
    @Autowired
    AuthenticationManager authenticationManagerBean ;
    @Autowired
    JwtService jwts;

//    // Get the data of all user
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUser(){
//        List<User> data =us.getAllUser();
//
//        return new ResponseEntity<>(data, HttpStatus.OK);
//
//    }

    //    Save New User
    @PostMapping
    public ResponseEntity addNewUser(@RequestBody User newUser){
        System.out.println("--->hitting");
        if (us.addUser(newUser)){
            return new ResponseEntity<>(HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        System.out.println("hitting");
        try{
        Authentication auth= authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(auth.isAuthenticated()){
          return ResponseEntity.ok(jwts.generateToken(user.getUsername()));

        }
        else {

            return  new ResponseEntity<>("samarth", HttpStatus.OK);



        }}
        catch (Exception err){
            System.out.println(err);
        }
        return  new ResponseEntity<>("samarth", HttpStatus.OK);
    }

//    //Get the data of the user
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUser(@PathVariable String username){
//        try {
//            User user = us.getUserByUsername(username.trim());
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }catch (Exception err){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//
//    }

//    //pswd update
//    @PutMapping("/{username}")
//    public ResponseEntity passwordChange(@PathVariable String username,@RequestBody User updatedUser){
//        try{
//            User oldUser=us.getUserByUsername(username.trim());
//            if(oldUser!=null){
//                oldUser.setPassword(updatedUser.getPassword());
//                us.addUser(oldUser);
//                return  new ResponseEntity<>(HttpStatus.OK);
//            }
//            else {
//                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//        }catch (Exception err){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//        }
//
//    }
//
//    @DeleteMapping("/{username}")
//    public ResponseEntity<?> deleteUser(@PathVariable String username){
//        try {
//            return ResponseEntity.ok(us.deleteUser(username));
//        }catch (Exception err){
//            return ResponseEntity.status(404).body("No user To Delete");
//        }
//
//    }

}
