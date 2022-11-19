package com.blog.Controllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.blog.Models.User;
import com.blog.Repositories.UserRepository;


@Controller
public class LoginController {

	
	@Autowired
    UserRepository userRepository;
	
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public LoginController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

       
    @GetMapping("/login")
	public String Index(Model model) {
		try {
			return "login";

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

    @GetMapping("/register")
	public String Register(Model model) {
		try {
			return "register";

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

    @PostMapping("/register")
	public String RegisterUser(@ModelAttribute User user, Model model) {
		try {
			if(user.getName() == "" || user.getUsername() == "" || user.getUsername().contains(" ") || user.getPassword() == "" ){
				model.addAttribute("error", "Algunos datos son incorrectos");
				return Register(model);

			}
			if(userRepository.findByUsername(user.getUsername()) != null){
				model.addAttribute("error", "El usuario ya existe");
				return Register(model);
			}
			
			userRepository.save(new User(user.getName(), user.getUsername().toLowerCase(), user.getPassword()));
			inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder().username(user.getUsername().toLowerCase()).password(user.getPassword()).roles("USER").build());
			return Index(model);
		

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

	@GetMapping("/logout")
	public String Logout(Model model) {
		try {
			return "redirect:/login";

		}catch (Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}

}
