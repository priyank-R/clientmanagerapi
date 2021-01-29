package com.springproject.clientmanager.repositories;

import com.springproject.clientmanager.domains.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Long> {


}
