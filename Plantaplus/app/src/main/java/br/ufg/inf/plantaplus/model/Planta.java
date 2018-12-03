package br.ufg.inf.plantaplus.model;

import java.util.Date;

public class Planta {

    private Date dataNasc;
    private String especie;
    private Integer intervaloNotificacao;
    private Integer horasNotificacao;
    private Integer minutosNotificacao;

    public Planta() {
        // default constructor, needed for FirebaseDatabase
    }

    public Planta(Date dataNasc, String especie, Integer intervaloNotificacao, Integer horasNotificacao, Integer minutosNotificacao) {
        this.dataNasc = dataNasc;
        this.especie = especie;
        this.intervaloNotificacao = intervaloNotificacao;
        this.horasNotificacao = horasNotificacao;
        this.minutosNotificacao = minutosNotificacao;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getIntervaloNotificacao() {
        return intervaloNotificacao;
    }

    public void setIntervaloNotificacao(Integer intervaloNotificacao) {
        this.intervaloNotificacao = intervaloNotificacao;
    }

    public Integer getHorasNotificacao() {
        return horasNotificacao;
    }

    public void setHorasNotificacao(Integer horasNotificacao) {
        this.horasNotificacao = horasNotificacao;
    }

    public Integer getMinutosNotificacao() {
        return minutosNotificacao;
    }

    public void setMinutosNotificacao(Integer minutosNotificacao) {
        this.minutosNotificacao = minutosNotificacao;
    }
}
