package com.blog.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.Models.Blog;
import com.blog.Repositories.BlogRepository;

@Controller
public class BlogController {
    
    @Autowired
    BlogRepository blogRepository;

	@GetMapping("/")
	public String Index(Model model) {
		try {
			List<Blog> blogs = blogRepository.findAll();

			model.addAttribute("blogs", blogs);
			return "index";

		}catch (Exception e){
			return "error";
		}
	}

	@GetMapping("/newBlog")
	public String NewBlog(Model model) {
		try {
			return "newBlog";

		}catch (Exception e){
			return "error";
		}
	}

	@PostMapping("/newBlog")
	public String createBlog(@ModelAttribute Blog blog, Model model) {
		try {
			
			if(blog.getTitle() =="" || blog.getContent() == "") {
				model.addAttribute("error", "Title or Content is empty");
				return "newBlog";
			}
			blogRepository.save(new Blog(blog.getTitle(), blog.getContent(), true));
			return Index(model);
		

		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/blogs/{id}")
	public String getBlog( Model model, @PathVariable("id") Long id) {
		try {
			Optional<Blog> blog = blogRepository.findById(id);

			if(blog.isPresent()) {
				System.out.println(blog.get().getTitle());
				model.addAttribute("blog", blog.get());
				return "blog";
			}
			return Index(model);

		}catch (Exception e){
			return "error";
		}
	}

	@GetMapping("/deleteBlog/{id}")
	public String deleteBlog( Model model, @PathVariable("id") Long id) {
		try {
			blogRepository.deleteById(id);
			return Index(model);

		}catch (Exception e){
			return "error";
		}
	}
}
