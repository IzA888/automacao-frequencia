package com.app.automacaofrequencia.model;

public class Aluno {
    private String nome;
    private String matricula;
    private String resposta;
    private String nota;

    public Aluno(String nome, String matricula, String resposta) {
        this.nome = nome;
        this.matricula = matricula;
        this.resposta = resposta;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getResposta() {
        return resposta;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
