package com.example.seguranca.config;

import com.example.seguranca.model.User;
import com.example.seguranca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SecurityDatabaseService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().forEach( role ->
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role))
        );

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), authorities
        );
        return userDetails;
    }
}
