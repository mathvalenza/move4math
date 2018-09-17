/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import conteudo.Imagem;
import org.opencv.core.Point;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Regiao {
    private Point pInicial;
    private Point pFinal;
    private Imagem img;
    private boolean ocupado;

    /**
     * @return the img
     */
    public Imagem getImg() {
        return img;
        
    }

    /**
     * @param img the img to set
     */
    public void setImg(Imagem img) {
        this.img = img;
    }

    /**
     * @return the ocupado
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * @return the pInicial
     */
    public Point getpInicial() {
        return pInicial;
    }

    /**
     * @param pInicial the pInicial to set
     */
    public void setpInicial(Point pInicial) {
        this.pInicial = pInicial;
    }

    /**
     * @return the pFinal
     */
    public Point getpFinal() {
        return pFinal;
    }

    /**
     * @param pFinal the pFinal to set
     */
    public void setpFinal(Point pFinal) {
        this.pFinal = pFinal;
    }
    
}
