package com.iesbelen.dam.apirest.api_valorant.controladores;

import com.iesbelen.dam.apirest.api_valorant.modelo.dao.ISkillDAO;
import com.iesbelen.dam.apirest.api_valorant.modelo.entidades.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class ControladorSkills {

    @Autowired
    ISkillDAO skillDAO;

    @GetMapping
    public List<Skill>buscarSkill(){

    }

    @GetMapping{"/{name}"}
    public List<Skill> buscarSkillByName(@PathVariable(value = "name") String name){

    }

    @PostMapping
    public Skill guardarSkill(@Validated @RequestBody Skill skill){

    }
}
