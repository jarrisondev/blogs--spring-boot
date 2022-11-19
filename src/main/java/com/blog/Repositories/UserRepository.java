package com.blog.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {  
    User findByUsername(String username);
}
