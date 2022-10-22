package com.blog;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.blog.Repositories.BlogRepository;

@SpringBootApplication
@EnableJpaRepositories("com.blog.Repositories")
public class BlogApplication  {

	@Autowired
	BlogRepository blogRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
