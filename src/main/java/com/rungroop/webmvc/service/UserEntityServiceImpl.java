package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.UserRegistationDto;
import com.rungroop.webmvc.model.Role;
import com.rungroop.webmvc.model.UserEntity;
import com.rungroop.webmvc.repository.RoleRepository;
import com.rungroop.webmvc.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserEntityServiceImpl implements UserEntityService{
    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public void saveUser(UserRegistationDto userRegistationDto) {

        UserEntity user =new UserEntity();
        user.setUsername(userRegistationDto.getUsername());
        user.setEmail(userRegistationDto.getEmail());
        user.setPassword(userRegistationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userEntityRepository.save(user);




    }

    @Override
    public UserEntity findByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userEntityRepository.findByUsername(username);
    }
}
