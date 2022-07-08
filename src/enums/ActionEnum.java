package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum ActionEnum {
    COMPRAR(asList("comprar")),
    REEMBOLSAR(asList("reembolsar")),
    CORRIGIR(asList("corrigir")),
    CONSERTAR(asList("consertar")),
    CONSEGUIR(asList("conseguir")),
    CUSTAR(asList("custar")),
    MORAR(asList("morar"));

    private final List<String> descricao;

    ActionEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findAction(String word) {
        ActionEnum actionEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return actionEnum != null ? actionEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
