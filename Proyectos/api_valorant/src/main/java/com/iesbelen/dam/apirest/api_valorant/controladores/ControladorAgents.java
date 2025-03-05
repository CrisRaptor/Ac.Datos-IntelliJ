package com.iesbelen.dam.apirest.api_valorant.controladores;

import com.iesbelen.dam.apirest.api_valorant.modelo.dao.IAgentDAO;
import com.iesbelen.dam.apirest.api_valorant.modelo.dao.ISkillDAO;
import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Agent;
import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class ControladorAgents {

    @Autowired
    IAgentDAO agentDAO;

    @GetMapping
    public List<Agent> buscarAgent(){
        return
    }

    @GetMapping{"/{name}"}
    public ResponseEntity<Agent> buscarAgentPorName(@PathVariable(value = "name") String name){

    }

    @PostMapping
    public Skill guardarAgent(@Validated @RequestBody Agent agent){

    }
}
