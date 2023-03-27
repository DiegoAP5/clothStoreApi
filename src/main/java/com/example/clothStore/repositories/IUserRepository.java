package com.example.clothStore.repositories;

import com.example.clothStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = "select users.* from users"+
            "where users.email = :userEmail",nativeQuery = true)
    User getUserByEmail(String userEmail);

    @Query(value = "select users.* from users"+
            "where users.id = :userId",nativeQuery = true)
    User getUserById(Long userId);
}
