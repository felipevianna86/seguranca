package com.example.seguranca.init;

import com.example.seguranca.model.User;
import com.example.seguranca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        User user = this.userRepository.findByUsername("admin");

        if(user == null){
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("master123");
            user.getRoles().add("MANAGERS");
            this.userRepository.save(user);
        }

        user = this.userRepository.findByUsername("user");

        if(user == null){
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("user123");
            user.getRoles().add("USERS");
            this.userRepository.save(user);
        }

    }
}
