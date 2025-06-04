package net.engineeringdigest.service;

import net.bytebuddy.implementation.bytecode.Throw;
import net.engineeringdigest.Repo.JournalRepo;
import net.engineeringdigest.Repo.UserRepo;
import net.engineeringdigest.model.Journal;
import net.engineeringdigest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class JournalService {
    @Autowired
    private JournalRepo jr;
    @Autowired
    private UserRepo ur;

    public List<Journal> getAll(){
        return  jr.findAll();


    }
//   Journal By username
    public List<Journal> getAll(String username){
        try {
           List <Journal> resData=jr.findByUserUsername(username);
           if (resData.size()==0){
               throw new RuntimeException("no Data or no User Exist");
           }
            return resData;
        }catch (Exception err){
           throw new RuntimeException("User not found");
        }

    }

    public void saveData(Journal data,String username){
        try {
            User user= ur.findByUsername(username);
            data.setUser(user);
            data.setDate(new Date());
            jr.save(data);
        } catch (RuntimeException e) {
            throw new RuntimeException("User Not Found");
        }

    return;
    }

    public void deleteByID(Long id){
       Journal  oldData =jr.findById(id).get();
//        System.out.println(o);

        jr.deleteById(id);
        return;

    }
}
