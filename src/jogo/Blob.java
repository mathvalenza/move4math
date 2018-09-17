/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import org.opencv.core.Mat;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Blob {
    private int idI;
    private Mat imagem;

    /**
     * @return the idI
     */
    public int getIdI() {
        return idI;
    }

    /**
     * @param idI the idI to set
     */
    public void setIdI(int idI) {
        this.idI = idI;
    }

    /**
     * @return the imagem
     */
    public Mat getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(Mat imagem) {
        this.imagem = imagem;
    }
}
