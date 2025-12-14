package com.nikhil.usermanagement.controller;

import com.nikhil.usermanagement.model.User;
import com.nikhil.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*") // allows frontend from different port
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ---------------- SIGNUP ----------------
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        user.setPassword(service.hashPassword(user.getPassword()));
        user.setVerified(false); // initially not verified
        return service.saveUser(user);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        Optional<User> userOpt = service.getUserByEmail(loginUser.getEmail());
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        if (!service.checkPassword(loginUser.getPassword(), user.getPassword())) {
            return "Invalid password";
        }

        if (!user.isVerified()) return "Account not verified";

        return "Login successful";
    }

    // ---------------- VERIFY OTP / Account ----------------
    @PostMapping("/verify/{id}")
    public String verifyUser(@PathVariable String id) {
        Optional<User> userOpt = service.getUserById(id);
        if (userOpt.isEmpty()) return "User not found";

        User user = userOpt.get();
        user.setVerified(true);
        service.saveUser(user);
        return "User verified successfully";
    }

    // ---------------- GET ALL USERS ----------------
    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    // ---------------- GET USER BY ID ----------------
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return service.getUserById(id);
    }

    // ---------------- UPDATE USER ----------------
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> userOpt = service.getUserById(id);
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setAddress(updatedUser.getAddress());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setRole(updatedUser.getRole());
        user.setDonationCount(updatedUser.getDonationCount());
        user.setProfileImageUrl(updatedUser.getProfileImageUrl());
        user.setVerified(updatedUser.isVerified());
        user.setNotifications(updatedUser.getNotifications());
        service.saveUser(user);
        return user;
    }

    // ---------------- DELETE USER ----------------
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }

    // ---------------- ADD NOTIFICATION ----------------
    @PostMapping("/{id}/notifications")
    public User addNotification(@PathVariable String id, @RequestBody String notification) {
        Optional<User> userOpt = service.getUserById(id);
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.getNotifications().add(notification);
        service.saveUser(user);
        return user;
    }

    // ---------------- CLEAR NOTIFICATIONS ----------------
    @DeleteMapping("/{id}/notifications")
    public User clearNotifications(@PathVariable String id) {
        Optional<User> userOpt = service.getUserById(id);
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.getNotifications().clear();
        service.saveUser(user);
        return user;
    }
}
