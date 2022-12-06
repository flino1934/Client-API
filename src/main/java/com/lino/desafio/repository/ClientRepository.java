package com.lino.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lino.desafio.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
