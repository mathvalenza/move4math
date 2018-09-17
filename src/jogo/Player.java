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
public class Player {
    private int id;
    private String nome;
    private String dataNascimento;
    private String dataCadastro;
    private String sexo;
    private String publico;
    private String justificativaPublico;
    private String faseAlfabetizacao;
    private String justificativafaseAlfabetizacao;
    private int vidas;
    private Vector<Sessao> sessoes;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNascimento
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the dataCadastro
     */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the publico
     */
    public String getPublico() {
        return publico;
    }

    /**
     * @param publico the publico to set
     */
    public void setPublico(String publico) {
        this.publico = publico;
    }

    /**
     * @return the justificativaPublico
     */
    public String getJustificativaPublico() {
        return justificativaPublico;
    }

    /**
     * @param justificativaPublico the justificativaPublico to set
     */
    public void setJustificativaPublico(String justificativaPublico) {
        this.justificativaPublico = justificativaPublico;
    }

    /**
     * @return the faseAlfabetizacao
     */
    public String getFaseAlfabetizacao() {
        return faseAlfabetizacao;
    }

    /**
     * @param faseAlfabetizacao the faseAlfabetizacao to set
     */
    public void setFaseAlfabetizacao(String faseAlfabetizacao) {
        this.faseAlfabetizacao = faseAlfabetizacao;
    }

    /**
     * @return the justificativafaseAlfabetizacao
     */
    public String getJustificativafaseAlfabetizacao() {
        return justificativafaseAlfabetizacao;
    }

    /**
     * @param justificativafaseAlfabetizacao the justificativafaseAlfabetizacao to set
     */
    public void setJustificativafaseAlfabetizacao(String justificativafaseAlfabetizacao) {
        this.justificativafaseAlfabetizacao = justificativafaseAlfabetizacao;
    }

    /**
     * @return the vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * @return the sessoes
     */
    public Vector<Sessao> getSessoes() {
        return sessoes;
    }

    /**
     * @param sessoes the sessoes to set
     */
    public void setSessoes(Vector<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    }
