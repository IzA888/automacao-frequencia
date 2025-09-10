package com.app.automacaofrequencia.service;

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.model.Gabarito;

public class CorretorProvas {
    
    public void corrigirProvas(Aluno aluno, Gabarito gabarito) {
        int acertos = 0;
        for (int i = 0; i < gabarito.getRespostas().size(); i++){
            if (aluno.getRespostas().equals(gabarito.getRespostas().get(i))) {
                acertos++;
            }
        }

        Double nota = aluno.setNota(acertos/gabarito.getRespostas().size() * 10.0);

    }
}
