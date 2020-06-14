package com.hackathon.ccr.chatbot.domain;

import com.hackathon.ccr.chatbot.utils.EnumTypeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Menu implements EnumType {

    SAUDE(1, new ArrayList<>(Arrays.asList("saude", "saúde")), "Saúde", true),
    TELEMEDICINA(10, new ArrayList<>(Arrays.asList("telemedicina", "tele")), "Telemedicina", false),
    ATENDIMENTO_PRESENCIAL(11, new ArrayList<>(Arrays.asList("atendimento", "presencial")), "Atendimento presencial", false),
    ALIMENTACAO(2, new ArrayList<>(Arrays.asList("alimentacao", "comida", "lanche", "almoco", "janta", "almoçar", "jantar")), "Alimentação", false),
    DESCANSO(3, new ArrayList<>(Arrays.asList("descanso", "parada", "dormir")), "Descanso", false),
    SOCORRO(4, new ArrayList<>(Arrays.asList("socorro", "sos")), "Socorro", true),
    EMERGENCIA(40, new ArrayList<>(Arrays.asList("emergência", "emergencia")), "Emergência", false),
    MECANICO(41, new ArrayList<>(Arrays.asList("pane", "mecânico", "mecanico", "conserto")), "Mecânico", false),
    PARCERIAS(5, new ArrayList<>(Arrays.asList("parcerias", "parceiros")), "Parcerias", true),
    ACADEMIA(50, new ArrayList<>(Arrays.asList("academias", "academia")), "Academia", false),
    OTICA(51, new ArrayList<>(Arrays.asList("óticas", "otica", "oculos", "óculos")), "Óticas", false),
    OFICINAS(52, new ArrayList<>(Arrays.asList("oficinas")), "Oficinas", false),
    PNEUS(53, new ArrayList<>(Arrays.asList("pneus", "revendedores")), "Revendedores de Pneus", false),
    INFO(6, new ArrayList<>(Arrays.asList("info", "informações")), "Informações", true),
    PEDAGIOS(60, new ArrayList<>(Arrays.asList("pedagios", "pedágios")), "Pedágios", false),
    TELEFONES(61, new ArrayList<>(Arrays.asList("telefones", "utéis")), "Telefones utéis", false),
    ALERTS(62, new ArrayList<>(Arrays.asList("alertas")), "Alertas", false),
    OBRAS(63, new ArrayList<>(Arrays.asList("obras")), "Obras", false);

    private Menu(Integer id, List<String> keywords, String name, boolean hasSubmenu) {
        this.id = id;
        this.keywords = keywords;
        this.name = name;
        this.hasSubmenu = hasSubmenu;
    }

    private Integer id;

    private List<String> keywords;

    private String name;

    private boolean hasSubmenu;

    @Override
    public int getId() {
        return this.id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getName() {
        return name;
    }

    public boolean isHasSubmenu() {
        return hasSubmenu;
    }

    public static class Converter extends EnumTypeConverter<Menu> {
        public Converter() {
            super(Menu.class);
        }
    }

    public static Menu forValue(int id) {
        return EnumTypeUtils.getById(Menu.class, id);
    }
}
