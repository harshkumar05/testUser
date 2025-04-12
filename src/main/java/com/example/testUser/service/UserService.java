package com.example.testUser.service;

import com.example.testUser.model.User;
import com.example.testUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return repository.findById(id);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        return repository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAge(updatedUser.getAge());
            return repository.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return repository.save(updatedUser);
        });
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
