package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum EquipmentEnum {
    FONE(asList("fone")),
    MOUSE(asList("mouse")),
    TECLADO(asList("teclado")),
    MONITOR(asList("monitor")),
    SSD(asList("ssd")),
    HD(asList("hd")),
    DISCO_RIGIDO(asList("disco rígido", "disco rigido")),
    PLACA_VIDEO(asList("placa de vídeo", "placa de video")),
    VIDEO(asList("vídeo", "video")),
    MAE(asList("mãe", "mae")),
    SOM(asList("som")),
    REDE(asList("rede")),
    PLACA_MAE(asList("placa mãe", "placa mae", "placa-mãe")),
    PLACA_DE_SOM(asList("placa de som")),
    PLACA_DE_REDE(asList("placa de rede")),
    WEBCAM(asList("webcam")),
    CARREGADOR(asList("carregador"));

    private final List<String> descricao;

    EquipmentEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findEquipment(String word) {
        EquipmentEnum equipmentEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return equipmentEnum != null ? equipmentEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
