package org.octoconta.octobackend.repos;

import org.octoconta.octobackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // getIdByEmail
    Long getIdByEmail(String email);

    // Check if user exists by email
    boolean existsByEmail(String email);

}