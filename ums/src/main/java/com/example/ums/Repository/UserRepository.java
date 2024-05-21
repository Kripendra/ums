package com.example.ums.Repository;

import com.example.ums.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Page<User> findByFirstName(String firstName, Pageable pageable);

    Page<User> findByLastName(String lastname, Pageable pageable);

    User findByEmail(String email);
}
