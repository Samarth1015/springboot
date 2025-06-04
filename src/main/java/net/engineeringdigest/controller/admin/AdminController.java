package net.engineeringdigest.controller.admin;

import net.engineeringdigest.model.User;
import net.engineeringdigest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserService us;


    // Get the data of all user
    @GetMapping("/all-user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> data =us.getAllUser();

        return new ResponseEntity<>(data, HttpStatus.OK);

    }
}
