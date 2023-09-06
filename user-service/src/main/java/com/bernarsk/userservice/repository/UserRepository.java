package com.bernarsk.userservice.repository;

import com.bernarsk.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
