package com.rushi.demo.repository;

import com.rushi.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContainingIgnoreCase(String name);
    List<User> findByAgeGreaterThanEqual(int minAge);

    List<User> findAll(Sort sort);
}
