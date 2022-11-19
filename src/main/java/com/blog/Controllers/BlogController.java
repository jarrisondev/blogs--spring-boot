package com.blog.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.Models.Blog;
import com.blog.Models.User;
import com.blog.Repositories.BlogRepository;
import com.blog.Repositories.UserRepository;

@Controller
public class BlogController {
    
    @Autowired
    BlogRepository blogRepository;

	@Autowired
    UserRepository userRepository;
	
	
	@GetMapping("/")
	public String Index(Model model) {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			List<Blog> blogs = blogRepository.findAll();

			model.addAttribute("currentUser",auth.getName());
			model.addAttribute("blogs", blogs);
			return "index";

		}catch (Exception e){
			System.out.println(e.toString());
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
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			
			if(blog.getTitle() =="" || blog.getContent() == "") {
				model.addAttribute("error", "Title or Content is empty");
				return "redirect:/";
			}

			User user = userRepository.findByUsername(auth.getName());
			blogRepository.save(new Blog(blog.getTitle(), blog.getContent(), true, user));
			return "redirect:/";
		

		} catch (Exception e) {
			System.out.println(e.toString());
			return "error";
		}
	}

	@GetMapping("/blogs/{id}")
	public String getBlog( Model model, @PathVariable("id") Long id) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Optional<Blog> blog = blogRepository.findById(id);

			System.out.println( blog.get().getUsername().equals(auth.getName()));

			if(blog.isPresent()) {
				System.out.println(blog.get().getTitle());
				model.addAttribute("canDelete",  blog.get().getUsername().equals(auth.getName()));
				model.addAttribute("blog", blog.get());
				return "blog";
			}
			return Index(model);

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

	@GetMapping("/deleteBlog/{id}")
	public String deleteBlog( Model model, @PathVariable("id") Long id) {
		try {
			blogRepository.deleteById(id);
			return "redirect:/";

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

	@GetMapping("/addToFavorite/{id}")
	public String addToFavorite( Model model, @PathVariable("id") Long id) {
		try {
			// Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			// Optional<Blog> blog = blogRepository.findById(id);
			// User user = userRepository.findByUsername(auth.getName());



			return "redirect:/";

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}
}
