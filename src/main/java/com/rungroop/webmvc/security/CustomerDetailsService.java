package com.rungroop.webmvc.security;

import com.rungroop.webmvc.model.UserEntity;
import com.rungroop.webmvc.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findFirstByUsername(username);
        if(user != null) {
            User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList())
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
