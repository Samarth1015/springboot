package net.engineeringdigest.controller;

import lombok.extern.log4j.Log4j2;
import net.engineeringdigest.model.Journal;
import net.engineeringdigest.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/journal")
public class JournalController {

//    private static final Logger log = LoggerFactory.getLogger(JournalController.class);

    @Autowired
    private JournalService js;

    // Get All the journal Entry
    @GetMapping
    public ResponseEntity<List<Journal>> getAll() {
        List<Journal> journals = js.getAll();
        return ResponseEntity.ok(journals);
    }

    //    get Journal By username
    @GetMapping("/{username}")
    public ResponseEntity<?> getJournalByuser(@PathVariable String username){
        try {
            return ResponseEntity.ok(js.getAll(username));
        }catch (Exception err){
            log.error("e: ", err);
            return ResponseEntity.status(404).body("User not Found or no journal entry ");
        }

    }

//    Set The jorunal for particular user
    @PostMapping("/{username}")
    public ResponseEntity<String> addData(@RequestBody Journal res,@PathVariable String username) {
        try{
        js.saveData(res,username);
        return ResponseEntity.ok("successfully saved");
        }
        catch (Exception err){
            return ResponseEntity.status(404).body("no userExist");
        }
    }





    //delete the journal by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Long id) {
        try {
            js.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (Exception err) {

            return ResponseEntity.status(500).body("Error deleting journal");
        }
    }
}
