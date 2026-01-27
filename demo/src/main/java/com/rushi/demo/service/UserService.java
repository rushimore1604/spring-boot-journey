package com.rushi.demo.service;

import com.rushi.demo.entity.User;
import com.rushi.demo.repository.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(
            int page,
            int size,
            String sortBy,
            String direction,
            String name
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (name != null && !name.isEmpty()) {
            return userRepository.findByNameContainingIgnoreCase(name, pageable);
        }

        return userRepository.findAll(pageable);
    }
}
