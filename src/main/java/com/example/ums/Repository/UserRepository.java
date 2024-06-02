package com.example.ums.Repository;

import com.example.ums.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Page<User> findByFirstName(String firstName, Pageable pageable);

    Page<User> findByLastName(String lastname, Pageable pageable);

    Optional<User> findByEmail(String email);
}
