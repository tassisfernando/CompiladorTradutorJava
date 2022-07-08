package enums;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum KeywordsEnum {
    COMPRAR(asList("comprar")),
    REEMBOLSAR(asList("reembolsar")),
    CORRIGIR(asList("corrigir")),
    CONSERTAR(asList("consertar")),
    CONSEGUIR(asList("conseguir")),
    CUSTAR(asList("custar")),
    MORAR(asList("morar")),
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
    KINDLE(asList("kindle")),
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
    CARREGADOR(asList("carregador")),
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
    DESINSTALAR(asList("desinstalar")),
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

    KeywordsEnum(List<String> descricao) {
        this.descricao = descricao;
    }

    public String findKeyword(String word) {
        KeywordsEnum keywordsEnum = Arrays.stream(values())
                .filter(d -> d.descricao.contains(word))
                .findFirst()
                .orElse(null);

        return keywordsEnum != null ? keywordsEnum.descricao.stream().findFirst().get() : null;
    }

    @Override
    public String toString() {
        return descricao.stream().findFirst().orElse("");
    }
}
