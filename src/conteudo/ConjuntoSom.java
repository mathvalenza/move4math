/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conteudo;

import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ConjuntoSom {
    private int id;
    private Vector<Som> sons;

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
     * @return the Sons
     */
    public Vector<Som> getSons() {
        return sons;
    }

    /**
     * @param Sons the Sons to set
     */
    public void setSons(Vector<Som> Sons) {
        this.sons = Sons;
    }
}
