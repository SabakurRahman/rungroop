package com.rungroop.webmvc.service;

import com.rungroop.webmvc.dto.UserRegistationDto;
import com.rungroop.webmvc.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserEntityService {
void saveUser(UserRegistationDto userRegistationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
