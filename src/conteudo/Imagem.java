/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conteudo;

import org.opencv.core.Mat;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Imagem implements Cloneable{
    private int id;
    private String descricao;
    private String imgStr;
    private Som som;
    private Mat img;
    private int grupo;
    
    public Imagem(Imagem imagem){
        this.id = imagem.getId();
        this.descricao = imagem.getDescricao();
        this.imgStr = imagem.getImgStr();
        this.som = imagem.getSom();
        this.img = imagem.getImg();
        this.grupo = imagem.getGrupo();
    }
    public Imagem(){
        
    }
    
    public void printImagem(){
        System.out.println("id: " + this.id);
        System.out.println("grupo: " + this.grupo);
        System.out.println("x: " + this.descricao);
        System.out.println("y: " + this.imgStr);    }
    
    @Override
    public Imagem clone() throws CloneNotSupportedException {
        Imagem clone = (Imagem) super.clone(); //Clona os tipos primitivos (no caso, binId)
        //Clona o resto.
        clone.img = img.clone();
        
        return clone;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the imgStr
     */
    public String getImgStr() {
        return imgStr;
    }

    /**
     * @param imgStr the imgStr to set
     */
    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    /**
     * @return the img
     */
    public Mat getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Mat img) {
        this.img = img;
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
