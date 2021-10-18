package com.jteam.project_2.repositories;

import com.jteam.project_2.models.Recipe;
import com.jteam.project_2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
