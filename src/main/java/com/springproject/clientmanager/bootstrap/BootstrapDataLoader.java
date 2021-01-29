package com.springproject.clientmanager.bootstrap;

import com.springproject.clientmanager.domains.Contact;
import com.springproject.clientmanager.domains.User;
import com.springproject.clientmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class BootstrapDataLoader implements CommandLineRunner {

    private final UserRepository userRepo;

    @Autowired
    public BootstrapDataLoader(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Priyank","newuser@gmail.com","abc123");
        Contact user1Contact = new Contact("newuser@gmail.com","9595959","Right Angle Overseas");
        user1.setInfo(user1Contact);
        userRepo.save(user1);
    }
}
