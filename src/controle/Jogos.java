/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Jogos {
    
    private int id;
    private String nome;
    private Vector<Publico> publico;
    public int CIT;

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
     * @return the publico
     */
    public Vector<Publico> getPublico() {
        return publico;
    }

    /**
     * @param publico the publico to set
     */
    public void setPublico(Vector<Publico> publico) {
        this.publico = publico;
    }

    /**
     * @return the CIT
     */
    public int getCIT() {
        return CIT;
    }

    /**
     * @param CIT the CIT to set
     */
    public void setCIT(int CIT) {
        this.CIT = CIT;
    }
    
    
}
