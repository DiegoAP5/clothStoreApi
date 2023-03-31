package com.example.clothStore.repositories;

import com.example.clothStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User getUserByName(String name);
    @Query(value = "select users.* from users "+
            "where users.email = :email",nativeQuery = true)
    User getUserByEmail(String email);
}
