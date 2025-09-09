

import com.app.automacaofrequencia.model.Aluno;
import com.app.automacaofrequencia.model.Gabarito;

public class CorretorProvas {
    
    public void corrigirProvas(Aluno aluno, Gabarito gabarito) {
        Int acertos = 0;
        for (Int i = 0; i < gabarito.getRespostas().size(); i++){
            if (aluno.getResposta().get(i).equals(gabarito.getRespostas().get(i))) {
               this.acertos++;
            }
        }

        Double nota = aluno.setNota(String.valueOf(acertos)/gabarito.getRespostas().size() * 10.0);

    }
}
