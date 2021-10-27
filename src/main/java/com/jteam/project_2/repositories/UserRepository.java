package com.jteam.project_2.repositories;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * Gets the user with the specified username
     * @param username the username we are searching with
     * @return the user with the given username
     */
    User getUserByUsername(String username);

    /**
     * Determines whether a user with the given username already exists
     * @param username the username we are checking
     * @return whether the username is taken
     */
    boolean existsUserByUsername(String username);
}
