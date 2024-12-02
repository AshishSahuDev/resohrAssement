package com.resohr.wedding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resohr.wedding.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
