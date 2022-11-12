package com.blog.Controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
       
    @GetMapping("/login")
	public String Index(Model model) {
		try {
			// List<Blog> blogs = blogRepository.findAll();

			// model.addAttribute("blogs", blogs);
			return "login";

		}catch (Exception e){
			return "error";
		}
	}

    // @PostMapping("/login")
	// public String login(@ModelAttribute Blog blog, Model model) {
	// 	try {
			
	// 		if(blog.getTitle() =="" || blog.getContent() == "") {
	// 			model.addAttribute("error", "Title or Content is empty");
	// 			return "newBlog";
	// 		}
	// 		blogRepository.save(new Blog(blog.getTitle(), blog.getContent(), true));
	// 		return Index(model);
		

	// 	} catch (Exception e) {
	// 		return "error";
	// 	}
	// }

}
