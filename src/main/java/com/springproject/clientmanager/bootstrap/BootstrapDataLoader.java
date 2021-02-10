package com.springproject.clientmanager.bootstrap;

import com.springproject.clientmanager.domains.Contact;
import com.springproject.clientmanager.domains.ServiceType;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.repositories.UserRepository;
import com.springproject.clientmanager.service.MasterService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class BootstrapDataLoader implements CommandLineRunner {

//    private final UserRepository userRepo;
    @Autowired
    MasterService masterService;

//    @Autowired
//    public BootstrapDataLoader(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Priyank","newuser@gmail.com","abc123");
//        Contact user1Contact = new Contact("newuser@gmail.com","9595959","Right Angle Overseas");
//        user1.setInfo(user1Contact);
//        userRepo.save(user1);
        System.out.println("Command line runner executed once more again !");
//        ServiceType service = ServiceType.valueOf("Host");
//        System.out.println(service)

//        masterService.deleteAllData();
    }
}
