package com.iesbelen.dam.apirest.api_valorant.modelo.dao;

import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentDAO extends CrudRepository<Agent, Integer> {
    Agent findByName(String name);
}
