package net.engineeringdigest.controller;

import net.engineeringdigest.model.Journal;
import net.engineeringdigest.model.User;
import net.engineeringdigest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    Authentication authentication=  SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    private UserService us;



//    Save New User
//    @PostMapping
//    public ResponseEntity addNewUser(@RequestBody User newUser){
//        if (us.addUser(newUser)){
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//    }

    //Get the data of the user
    @GetMapping("/details")
    public ResponseEntity<User> getUser(){

        String username=authentication.getName();
        try {
            User user = us.getUserByUsername(username.trim());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception err){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


    }

    //pswd update
    @PutMapping("/update")
    public ResponseEntity passwordChange(@RequestBody User updatedUser){
        try{  String username=authentication.getName();
        User oldUser=us.getUserByUsername(username.trim());
        if(oldUser!=null){
        oldUser.setPassword(updatedUser.getPassword());
        us.addUser(oldUser);
return  new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        }catch (Exception err){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
try {
    return ResponseEntity.ok(us.deleteUser(username));
}catch (Exception err){
    return ResponseEntity.status(404).body("No user To Delete");
}

    }

}
