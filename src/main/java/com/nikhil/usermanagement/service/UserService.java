package com.nikhil.usermanagement.service;

import com.nikhil.usermanagement.model.User;
import com.nikhil.usermanagement.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // ---------------- CREATE / SAVE ----------------
    public User saveUser(User user) {
        return repo.save(user);
    }

    // ---------------- GET ALL ----------------
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // ---------------- GET BY ID ----------------
    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    // ---------------- DELETE ----------------
    public void deleteUser(String id) {
        repo.deleteById(id);
    }

    // ---------------- GET BY EMAIL ----------------
    public Optional<User> getUserByEmail(String email) {
        return repo.findAll()
                   .stream()
                   .filter(u -> u.getEmail().equalsIgnoreCase(email))
                   .findFirst();
    }

    // ---------------- PASSWORD UTILITIES ----------------
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // ---------------- NOTIFICATIONS ----------------
    public User addNotification(String userId, String notification) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.getNotifications().add(notification);
        return saveUser(user);
    }

    public User clearNotifications(String userId) {
        Optional<User> userOpt = getUserById(userId);
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.getNotifications().clear();
        return saveUser(user);
    }

    // ---------------- VERIFY USER ----------------
    public User verifyUser(String userId) {
        Optional<User> userOpt = getUserById(userId);
        
        if (userOpt.isEmpty()) return null;

        User user = userOpt.get();
        user.setVerified(true);
        return saveUser(user);
    }
}
