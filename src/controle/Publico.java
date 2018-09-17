/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Vector;
import jogo.Player;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Publico {
    private int id;
    private String nome;
    private Vector<Fase> fases;
    private Vector<Nivel> niveis;
    private Vector<Vector<Integer>> configuracoes;
    private Vector<Vector<Player>> players;

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
     * @return the fases
     */
    public Vector<Fase> getFases() {
        return fases;
    }

    /**
     * @param fases the fases to set
     */
    public void setFases(Vector<Fase> fases) {
        this.fases = fases;
    }

    /**
     * @return the niveis
     */
    public Vector<Nivel> getNiveis() {
        return niveis;
    }

    /**
     * @param niveis the niveis to set
     */
    public void setNiveis(Vector<Nivel> niveis) {
        this.niveis = niveis;
    }

    /**
     * @return the configuracoes
     */
    public Vector<Vector<Integer>> getConfiguracoes() {
        return configuracoes;
    }

    /**
     * @param configuracoes the configuracoes to set
     */
    public void setConfiguracoes(Vector<Vector<Integer>> configuracoes) {
        this.configuracoes = configuracoes;
    }

    /**
     * @return the players
     */
    public Vector<Vector<Player>> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(Vector<Vector<Player>> players) {
        this.players = players;
    }
}
