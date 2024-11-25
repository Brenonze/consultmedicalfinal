package br.com.brenofranco.medicalconsult.usuario.domain;

public enum Permissao {
    ADMIN("ADMIN"), //Faz tudo
    SECRETARIO("SECRETARIO"), // Pode criar mas nao pode deletar
    PACIENTE("PACIENTE"); // Pode ver as proprias consultas

    private final String descricao;

    Permissao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
