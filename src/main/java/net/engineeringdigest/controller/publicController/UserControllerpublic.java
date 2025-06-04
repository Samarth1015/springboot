package net.engineeringdigest.controller.publicController;

import net.engineeringdigest.model.User;
import net.engineeringdigest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/user")
public class UserControllerpublic {
    @Autowired
    private UserService us;

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
