package com.rungroop.webmvc.repository;

import com.rungroop.webmvc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String userName);
    UserEntity findByEmail(String email);

    UserEntity findFirstByUsername(String username);
}
