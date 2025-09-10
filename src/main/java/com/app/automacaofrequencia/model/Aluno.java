package com.app.automacaofrequencia.model;

import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private List<String> respostas;
    private Double nota;

    public Aluno(String nome, String matricula, List<String> respostas) {
        this.nome = nome;
        this.matricula = matricula;
        this.respostas = respostas;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String setMatricula(String matricula) {
    return this.matricula = matricula;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public List<String> setRespostas(List<String> respostas) {
        return this.respostas = respostas;
    }

    public Double getNota() {
        return nota;
    }

    public Double setNota(Double nota) {
        return this.nota = nota;
    }
}
