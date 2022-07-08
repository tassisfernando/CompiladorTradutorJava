package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum ProblemEnum {
    LIGAR(asList("ligar")),
    DESLIGAR(asList("desligar")),
    QUEIMAR(asList("queimar")),
    REINICIAR(asList("reiniciar")),
    ATUALIZAR(asList("atualizar")),
    TROCAR(asList("trocar")),
    SINCRONIZAR(asList("sincronizar")),
    CONECTAR(asList("conectar")),
    QUEBRAR(asList("quebrar")),
    ABRIR(asList("abrir")),
    FECHAR(asList("fechar")),
    EXECUTAR(asList("executar")),
    COMPARTILHAR(asList("compartilhar")),
    INTEGRAR(asList("integrar")),
    INSTALAR(asList("instalar")),
    DESINSTALAR(asList("desinstalar"));

    private final List<String> descricao;

    ProblemEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findProblem(String word) {
        ProblemEnum problemEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return problemEnum != null ? problemEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
