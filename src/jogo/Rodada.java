/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Rodada {
    private int idSessao;
    private String data;
    private String hora;
    private String jogo;
    private String fase;
    private int nivel;
    private String imgRef;
    private String somRef;
    private String imgTocada;
    private int tempoToque;
    private String acao;
    private int pontosMotor;
    private int pontosCognitivo;
    private int linhaNivel; // Linha correspondente ao vetor aleatório de 4 posições de cada nível

    /**
     * @return the idSessao
     */
    public int getIdSessao() {
        return idSessao;
    }

    /**
     * @param idSessao the idSessao to set
     */
    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
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
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the imgRef
     */
    public String getImgRef() {
        return imgRef;
    }

    /**
     * @param imgRef the imgRef to set
     */
    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    /**
     * @return the somRef
     */
    public String getSomRef() {
        return somRef;
    }

    /**
     * @param somRef the somRef to set
     */
    public void setSomRef(String somRef) {
        this.somRef = somRef;
    }

    /**
     * @return the imgTocada
     */
    public String getImgTocada() {
        return imgTocada;
    }

    /**
     * @param imgTocada the imgTocada to set
     */
    public void setImgTocada(String imgTocada) {
        this.imgTocada = imgTocada;
    }

    /**
     * @return the tempoToque
     */
    public int getTempoToque() {
        return tempoToque;
    }

    /**
     * @param tempoToque the tempoToque to set
     */
    public void setTempoToque(int tempoToque) {
        this.tempoToque = tempoToque;
    }

    /**
     * @return the acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the pontosMotor
     */
    public int getPontosMotor() {
        return pontosMotor;
    }

    /**
     * @param pontosMotor the pontosMotor to set
     */
    public void setPontosMotor(int pontosMotor) {
        this.pontosMotor = pontosMotor;
    }

    /**
     * @return the pontosCognitivo
     */
    public int getPontosCognitivo() {
        return pontosCognitivo;
    }

    /**
     * @param pontosCognitivo the pontosCognitivo to set
     */
    public void setPontosCognitivo(int pontosCognitivo) {
        this.pontosCognitivo = pontosCognitivo;
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

}
