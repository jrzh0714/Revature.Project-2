package com.jteam.project_2.services;


import com.jteam.project_2.models.User;
import com.jteam.project_2.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
        return userRepository.getById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public boolean userExists(String username){
        return userRepository.existsUserByUsername(username);
    }
}
