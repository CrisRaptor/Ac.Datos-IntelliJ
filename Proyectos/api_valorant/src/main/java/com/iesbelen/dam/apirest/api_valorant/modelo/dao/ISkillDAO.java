package com.iesbelen.dam.apirest.api_valorant.modelo.dao;

import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Agent;
import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillDAO extends CrudRepository<Skill, Integer> {
    Skill findByName(String name);
    Skill findByAgent(Agent agent);
}
