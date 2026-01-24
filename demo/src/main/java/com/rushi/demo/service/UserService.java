package com.rushi.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rushi.demo.UserRequest;
import com.rushi.demo.UserResponse;
import com.rushi.demo.entity.User;
import com.rushi.demo.exception.UserNotFoundException;
import com.rushi.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());

        User saved = userRepository.save(user);

        return new UserResponse("User created successfully", saved.getName(), saved.getAge());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User updateUser(Long id, UserRequest request) {
        User user = getUserById(id);

        user.setName(request.getName());
        user.setAge(request.getAge());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public Page<User> getUsersWithPagination(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAll(pageable);
    }
}
