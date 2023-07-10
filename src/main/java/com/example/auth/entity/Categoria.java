package com.example.auth.entity;

import jakarta.persistence.*;

@Entity()
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private Long quantidade;

    public Categoria(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Categoria() {

    }

    public Categoria(Categoria categoria) {
        this.titulo = categoria.titulo;
        this.descricao = categoria.descricao;
        this.quantidade = Long.valueOf(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}