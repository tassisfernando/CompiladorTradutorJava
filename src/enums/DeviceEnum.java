package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum DeviceEnum {
    NOTEBOOK(asList("notebook")),
    COMPUTADOR(asList("computador")),
    CELULAR(asList("celular")),
    TABLET(asList("tablet")),
    PC(asList("pc")),
    NOTEBOOK_GAMER(asList("notebook gamer")),
    COMPUTADOR_GAMER(asList("computador gamer")),
    MAQUINA(asList("maquina", "máquina")),
    PC_GAMER(asList("pc gamer")),
    GAMER(asList("gamer")),
    KINDLE(asList("kindle"));

    private final List<String> descricao;

    DeviceEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findDevice(String word) {
        DeviceEnum deviceEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return deviceEnum != null ? deviceEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
