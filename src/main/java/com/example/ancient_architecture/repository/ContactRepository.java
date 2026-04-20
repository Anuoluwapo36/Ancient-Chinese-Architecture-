package com.example.ancient_architecture.repository;

import com.example.ancient_architecture.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends  JpaRepository<Contact, Long> {

    
}
