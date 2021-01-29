package com.springproject.clientmanager.repositories;

import com.springproject.clientmanager.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByName(String name);
    User findByEmail(String email);

}
