package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum SoftwareEnum {
    OFFICE(asList("office")),
    ADOBE(asList("adobe")),
    NAVEGADOR(asList("navegador")),
    WINDOWS_XP(asList("windows xp", "win xp")),
    WINDOWS_VISTA(asList("windows vista", "win vista")),
    WINDOWS_SERVER(asList("windows server", "win server")),
    WINDOWS_7(asList("windows 7", "win 7")),
    WINDOWS_8(asList("windows 8", "win 8")),
    WINDOWS_10(asList("windows 10", "win 10")),
    WINDOWS_11(asList("windows 11", "win 11")),
    ARCH(asList("arch")),
    DEBIAN(asList("debian")),
    FEDORA(asList("fedora")),
    MINT(asList("mint")),
    UBUNTU(asList("ubuntu"));

    private final List<String> descricao;

    SoftwareEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findSoftware(String word) {
        SoftwareEnum softwareEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return softwareEnum != null ? softwareEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
