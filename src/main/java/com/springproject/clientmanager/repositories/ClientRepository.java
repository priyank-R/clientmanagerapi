package com.springproject.clientmanager.repositories;

import com.springproject.clientmanager.domains.Client;
import com.springproject.clientmanager.domains.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface ClientRepository extends CrudRepository<Client,Long> {
    List<Client> findByClientOf(User user);
}
