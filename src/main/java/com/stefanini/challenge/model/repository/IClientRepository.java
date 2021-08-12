package com.stefanini.challenge.model.repository;

import com.stefanini.challenge.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {
}
