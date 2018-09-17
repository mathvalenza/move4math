/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Sessao {
    private int id;
    private String data;
    private String hora;
    private String jogo;
    private String fase;
    private String nivel;
    private int linhaNivel;
    private int pontuacaoGeral;
    private int tempoTotal;
    private Vector<Rodada> rodadas;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the jogo
     */
    public String getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(String jogo) {
        this.jogo = jogo;
    }

    /**
     * @return the fase
     */
    public String getFase() {
        return fase;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(String fase) {
        this.fase = fase;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the linhaNivel
     */
    public int getLinhaNivel() {
        return linhaNivel;
    }

    /**
     * @param linhaNivel the linhaNivel to set
     */
    public void setLinhaNivel(int linhaNivel) {
        this.linhaNivel = linhaNivel;
    }

    /**
     * @return the pontuacaoGeral
     */
    public int getPontuacaoGeral() {
        return pontuacaoGeral;
    }

    /**
     * @param pontuacaoGeral the pontuacaoGeral to set
     */
    public void setPontuacaoGeral(int pontuacaoGeral) {
        this.pontuacaoGeral = pontuacaoGeral;
    }

    /**
     * @return the tempoTotal
     */
    public int getTempoTotal() {
        return tempoTotal;
    }

    /**
     * @param tempoTotal the tempoTotal to set
     */
    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    /**
     * @return the rodadas
     */
    public Vector<Rodada> getRodadas() {
        return rodadas;
    }

    /**
     * @param rodadas the rodadas to set
     */
    public void setRodadas(Vector<Rodada> rodadas) {
        this.rodadas = rodadas;
    }
}
