package com.blog.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Models.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {  
}
