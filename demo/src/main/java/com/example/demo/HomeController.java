package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/")
//    public String index() {
//        return "index.html";
//    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/login";  // after registration, go to login page
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public void loginUser(@RequestParam String userName,
                          @RequestParam int password,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        User user = userRepository.findByUserNameAndPassword(userName, password);

        if (user != null) {
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("/users"); // welcome -> users
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Invalid username or password.'); window.location.href = '/login.html';</script>");
        }
    }

    @GetMapping("/users")
    public String listAllUsers(HttpServletRequest request, Model model) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // block access if not logged in
        }

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }



}
