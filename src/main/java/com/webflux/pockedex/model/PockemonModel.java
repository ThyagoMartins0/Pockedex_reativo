package com.webflux.pockedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class PockemonModel {

    @Id
    private String id;
    private String name;
    private String categoria;
    private String habilidade;
    private Double peso;


    public PockemonModel(String id, String name, String categoria, String habilidade, Double peso) {
        this.id = id;
        this.name = name;
        this.categoria = categoria;
        this.habilidade = habilidade;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public PockemonModel() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public String toString() {
        return "Pockemon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoria='" + categoria + '\'' +
                ", habilidade='" + habilidade + '\'' +
                ", peso=" + peso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PockemonModel pockemon = (PockemonModel) o;
        return Objects.equals(id, pockemon.id) && Objects.equals(name, pockemon.name) && Objects.equals(categoria, pockemon.categoria) && Objects.equals(habilidade, pockemon.habilidade) && Objects.equals(peso, pockemon.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoria, habilidade, peso);
    }
}
