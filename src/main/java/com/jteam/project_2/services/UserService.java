package com.jteam.project_2.services;


import com.jteam.project_2.models.User;
import com.jteam.project_2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets a specific user based on their ID
     * @param id the unique numeric identifier of a user record
     * @return the user with the specified ID
     */
    public User getUserById(int id){
        return userRepository.getById(id);
    }

    /**
     * Gets the username of a user by their ID
     * @param id the unique numeric identifier of a user record
     * @return the username of the specified user
     */
    public String getUsernameById(int id){
        return userRepository.getById(id).getUsername();
    }

    /**
     * Gets all of the users in the database
     * @return a list containing all of the users
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * Persists a user to the database
     * @param user the user we are saving
     * @return the user that was saved
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * Gets a user by their unique username
     * @param username the unique string identifier of a user record
     * @return the user with the specified username
     */
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    /**
     * Tells whether a user with the given username exists in the database
     * @param username the username we are checking
     * @return whether a user under that username already exists
     */
    public boolean userExists(String username){
        return userRepository.existsUserByUsername(username);
    }
}
