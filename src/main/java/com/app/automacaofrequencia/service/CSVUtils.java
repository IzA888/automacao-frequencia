package main.java.com.app.automacaofrequencia.service;

import com.app.automacaofrequencia.model.Aluno;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtils {

    // CSV no formato: Nome,Matricula,Resp1,Resp2,...
    public static List<Aluno> lerAlunos(String caminho) throws IOException {
        List<Aluno> alunos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                String matricula = partes[1];
                List<String> respostas = Arrays.asList(Arrays.copyOfRange(partes, 2, partes.length));
                alunos.add(new Aluno(nome, matricula, respostas));
            }
        }
        return alunos;
    }

    public static void escreverAlunos(List<Aluno> alunos, String caminho) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (Aluno a : alunos) {
                String linha = a.getNome() + "," + a.getMatricula();
                for (String r : a.getRespostas()) {
                    linha += "," + r;
                }
                linha += "," + a.getNota();
                bw.write(linha);
                bw.newLine();
            }
        }
    }
}
