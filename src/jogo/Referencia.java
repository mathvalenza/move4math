/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import conteudo.Som;
import org.opencv.core.Mat;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Referencia {
    private Mat imagem;
    private int id;
    private int grupo;
    private int x;
    private int y;
    private int width;
    private int height;
    private Som som;
    private String refImgStr;
    private int tempoExposicao;

    public void printReferencia(){
        System.out.println("id: " + this.id);
        System.out.println("grupo: " + this.grupo);
        System.out.println("x: " + this.x);
        System.out.println("y: " + this.y);
        System.out.println("width: " + this.width);
        System.out.println("height: " + this.height);
        System.out.println("refImgStr: " + this.refImgStr);
        System.out.println("tempoExposicao: " + this.tempoExposicao);
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
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the som
     */
    public Som getSom() {
        return som;
    }

    /**
     * @param som the som to set
     */
    public void setSom(Som som) {
        this.som = som;
    }

    /**
     * @return the refImgStr
     */
    public String getRefImgStr() {
        return refImgStr;
    }

    /**
     * @param refImgStr the refImgStr to set
     */
    public void setRefImgStr(String refImgStr) {
        this.refImgStr = refImgStr;
    }

    /**
     * @return the tempoExposicao
     */
    public int getTempoExposicao() {
        return tempoExposicao;
    }

    /**
     * @param tempoExposicao the tempoExposicao to set
     */
    public void setTempoExposicao(int tempoExposicao) {
        this.tempoExposicao = tempoExposicao;
    }

    /**
     * @return the grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    
}
