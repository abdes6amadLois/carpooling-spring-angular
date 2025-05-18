package org.example.covoiturage.security.repository;



import org.example.covoiturage.security.entities.Role;
import org.example.covoiturage.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    List<User> findByRole(Role role);
}
