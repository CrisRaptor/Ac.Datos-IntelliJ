package com.iesbelen.dam.apirest.api_valorant.modelo.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "cost")
    private Integer cost;

    @ColumnDefault("1")
    @Column(name = "charges")
    private Integer charges;

    @Column(name = "type", length = Integer.MAX_VALUE)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_name")
    private Agent agentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCharges() {
        return charges;
    }

    public void setCharges(Integer charges) {
        this.charges = charges;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Agent getAgentName() {
        return agentName;
    }

    public void setAgentName(Agent agentName) {
        this.agentName = agentName;
    }

}