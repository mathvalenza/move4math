/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Grade {
    private int lado;
    private int screenWidth;
    private int screenHeight;
    private int tamanhoGrade; //2 - grande / 1 - media / 0 - pequena
    private int numImagens;
    private Vector<Regiao> regioes;
    
    public Grade(int lado, int screenWidth, int screenHeight){
        this.lado = lado;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.regioes = new Vector<Regiao>();
    }

    /**
     * @return the lado
     */
    public int getLado() {
        return lado;
    }

    /**
     * @param lado the lado to set
     */
    public void setLado(int lado) {
        this.lado = lado;
    }

    /**
     * @return the screenWidth
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param screenWidth the screenWidth to set
     */
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * @return the screenHeight
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param screenHeight the screenHeight to set
     */
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * @return the tamanhoGrade
     */
    public int getTamanhoGrade() {
        return tamanhoGrade;
    }

    /**
     * @param tamanhoGrade the tamanhoGrade to set
     */
    public void setTamanhoGrade(int tamanhoGrade) {
        this.tamanhoGrade = tamanhoGrade;
        //Vector<Regiao> regioesTemp = new Vector<Regiao>();
        Regiao regiaoTemp;
        
        this.regioes.clear();
        
        int cellWidth = (int)(this.screenWidth*0.325)/16;
        int cellHeight = (int)(this.screenHeight*0.65)/24;
        
        if(this.lado==0){ //esquerdo
            int startWidth = (int)(this.screenWidth*0.025);
            int startHeight = (int)(this.screenHeight*0.1875);
            
            if(this.tamanhoGrade==2){ //GRANDE
                //regioesTemp = new Vector<Regiao>();
                regiaoTemp = new Regiao();
                Point p1 = new Point();
                Point p2 = new Point();
                int nCell = 6;
                
                //regiao unica em cima
                p1.x = startWidth   + 8*cellWidth;
                p1.y = startHeight  +  0*nCell*cellHeight;
                p2.x = startWidth   +  8*cellWidth + nCell*cellWidth;
                p2.y = startHeight  +  1*nCell*cellHeight;
                
                regiaoTemp.setpInicial(p1);
                regiaoTemp.setpFinal(p2);
                regiaoTemp.setOcupado(false);
                
                this.regioes.add(regiaoTemp);
                
                // duas regiões verticais a esquerda
                for(int i=1; i<3; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);

                    this.regioes.add(regiaoTemp);
                }
                
                // duas regiões verticais a direita
                for(int i=1; i<3; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  8*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  8*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);

                    this.regioes.add(regiaoTemp);
                }
                // região única em baixo
                regiaoTemp = new Regiao();
                p1 = new Point();
                p2 = new Point();
                    
                p1.x = startWidth   +  2*cellWidth;
                p1.y = startHeight  +  3*nCell*cellHeight;
                p2.x = startWidth   +  2*cellWidth + nCell*cellWidth;
                p2.y = startHeight  +  4*nCell*cellHeight;
                
                regiaoTemp.setpInicial(p1);
                regiaoTemp.setpFinal(p2);
                regiaoTemp.setOcupado(false);

                this.regioes.add(regiaoTemp);
                
            }//fim if grade grande
            else if(this.tamanhoGrade==1) { //MEDIA
                Point p1;
                Point p2;
                int nCell = 4;
                
                // as oito regiões em cima
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  8*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  8*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  12*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  12*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as oito regiões de baixo
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth;
                    p1.y = startHeight  +  8*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  nCell*cellWidth;
                    p2.y = startHeight  +  8*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  nCell*cellWidth;
                    p1.y = startHeight  +  8*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  2*nCell*cellWidth;
                    p2.y = startHeight  +  8*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
            }//fim if grade media
            else if(this.tamanhoGrade==0){//PEQUENA
                Point p1;
                Point p2;
                int nCell = 2;
                
                
                // as 27 regiões da esquerda
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  nCell*cellWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  2*nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  2*nCell*cellWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  3*nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as 24 regiões do meio
                for(int i=0; i<12; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  6*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  6*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<12; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  6*cellWidth + nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  6*cellWidth + 2*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as 27 regiões da direita
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth + nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + 2*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth + 2*nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + 3*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
            }//fim if grade pequena
        }//fim if lado esq
        else if(this.lado==1){
            int startWidth = (int)(this.screenWidth*0.65);
            int startHeight = (int)(this.screenHeight*0.1875);
            
            if(this.tamanhoGrade==2){ //GRANDE
                //regioesTemp = new Vector<Regiao>();
                regiaoTemp = new Regiao();
                Point p1 = new Point();
                Point p2 = new Point();
                int nCell = 6;
                
                //regiao unica em cima
                p1.x = startWidth   +  2*cellWidth;
                p1.y = startHeight  +  0*nCell*cellHeight;
                p2.x = startWidth   +  2*cellWidth + nCell*cellWidth;
                p2.y = startHeight  +  1*nCell*cellHeight;
                
                regiaoTemp.setpInicial(p1);
                regiaoTemp.setpFinal(p2);
                regiaoTemp.setOcupado(false);
                
                this.regioes.add(regiaoTemp);
                
                // duas regiões verticais a esquerda
                for(int i=1; i<3; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  2*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  2*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);

                    this.regioes.add(regiaoTemp);
                }
                
                // duas regiões verticais a direita
                for(int i=1; i<3; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);

                    this.regioes.add(regiaoTemp);
                }
                // região única em baixo
                regiaoTemp = new Regiao();
                p1 = new Point();
                p2 = new Point();
                    
                p1.x = startWidth   +  8*cellWidth;
                p1.y = startHeight  +  3*nCell*cellHeight;
                p2.x = startWidth   +  8*cellWidth + nCell*cellWidth;
                p2.y = startHeight  +  4*nCell*cellHeight;
                
                regiaoTemp.setpInicial(p1);
                regiaoTemp.setpFinal(p2);
                regiaoTemp.setOcupado(false);

                this.regioes.add(regiaoTemp);
                
            }//fim if grade grande
            else if(this.tamanhoGrade==1) { //MEDIA
                Point p1;
                Point p2;
                int nCell = 4;
                
                // as oito regiões em cima
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;;
                    p2.x = startWidth   +  nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  2*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as oito regiões de baixo
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  2*nCell*cellWidth;
                    p1.y = startHeight  +  8*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  3*nCell*cellWidth;
                    p2.y = startHeight  +  8*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                for(int i=0; i<4; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  3*nCell*cellWidth;
                    p1.y = startHeight  +  8*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  4*nCell*cellWidth;
                    p2.y = startHeight  +  8*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
            }//fim if grade media
            else if(this.tamanhoGrade==0){//PEQUENA
                Point p1;
                Point p2;
                int nCell = 2;
                
                
                // as 27 regiões da esquerda
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  2*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  2*nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  3*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as 24 regiões do meio
                for(int i=0; i<12; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  6*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  6*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<12; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  6*cellWidth + nCell*cellWidth;
                    p1.y = startHeight  +  i*nCell*cellHeight;
                    p2.x = startWidth   +  6*cellWidth + 2*nCell*cellWidth;
                    p2.y = startHeight  +  (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                
                // as 27 regiões da direita
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth + nCell*cellWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + 2*nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
                for(int i=0; i<9; i++){
                    regiaoTemp = new Regiao();
                    p1 = new Point();
                    p2 = new Point();
                    
                    p1.x = startWidth   +  10*cellWidth + 2*nCell*cellWidth;
                    p1.y = startHeight  +  6*cellHeight + i*nCell*cellHeight;
                    p2.x = startWidth   +  10*cellWidth + 3*nCell*cellWidth;
                    p2.y = startHeight  +  6*cellHeight + (i+1)*nCell*cellHeight;
                    
                    regiaoTemp.setpInicial(p1);
                    regiaoTemp.setpFinal(p2);
                    regiaoTemp.setOcupado(false);
                    
                    this.regioes.add(regiaoTemp);
                }
            }//fim if grade pequena
        }//fim if lado dir
    }

    /**
     * @return the numImagens
     */
    public int getNumImagens() {
        return numImagens;
    }

    /**
     * @param numImagens the numImagens to set
     */
    public void setNumImagens(int numImagens) {
        this.numImagens = numImagens;
    }

    /**
     * @return the regioes
     */
    public Vector<Regiao> getRegioes() {
        return regioes;
    }

    /**
     * @param regioes the regioes to set
     */
    public void setRegioes(Vector<Regiao> regioes) {
        this.regioes = regioes;
    }
    
    public void setRegiao(int posicao,Regiao regiao) {
        regioes.remove(posicao);
        regioes.add(posicao, regiao);
        
    }
    public void showRegioes(Mat cenario){
        for(int i=0; i<this.regioes.size();i++){
            //Point p1 = this.regioes.elementAt(i).getpInicial();
            //Point p2 = this.regioes.elementAt(i).getpFinal();
            //Imgproc.rectangle(cenario, p1, p2,new Scalar(255,255,255), 1);
            //ou assim:
            int x1 = (int)this.regioes.elementAt(i).getpInicial().x;
            int y1 = (int)this.regioes.elementAt(i).getpInicial().y;
            int x2 = (int)this.regioes.elementAt(i).getpFinal().x;
            int y2 = (int)this.regioes.elementAt(i).getpFinal().y;
            //System.out.println("Regiao: "+i+" pontos: "+x1+" "+y1+" "+x2+" "+y2);
            
            Imgproc.line(cenario,new Point(x1,y1),new Point(x2,y1),new Scalar(255,255,255), 1);
            Imgproc.line(cenario,new Point(x1,y1),new Point(x1,y2),new Scalar(255,255,255), 1);
            Imgproc.line(cenario,new Point(x1,y2),new Point(x2,y2),new Scalar(255,255,255), 1);
            Imgproc.line(cenario,new Point(x2,y1),new Point(x2,y2),new Scalar(255,255,255), 1);
            
        }
    }
    
    public void showImagens(Mat cenario){
        Mat dst;
        for(int i=0;i<getRegioes().size();i++){
            if(getRegioes().elementAt(i).isOcupado()){
                int x1 = (int)this.regioes.elementAt(i).getpInicial().x;
                int y1 = (int)this.regioes.elementAt(i).getpInicial().y;
                int x2 = (int)this.regioes.elementAt(i).getpFinal().x;
                int y2 = (int)this.regioes.elementAt(i).getpFinal().y;
                
                //System.out.println(regioes.elementAt(i).getImg().getDescricao());
                
                //System.out.println(regioes.elementAt(i).getImg().getImg().channels());

                dst = new Mat();
        
                //tentativas de conversao... ;-;
                //Imgproc.cvtColor(imagem, imagem2, Imgproc.COLOR_BGRA2BGR,CvType.CV_8U);
                //imagem.convertTo(imagem2, Imgproc.COLOR_BGRA2BGR);
                
                Mat mesc = cenario.submat(new Rect(new Point(x1, y1),new Point(x2, y2)));
                
                //Imgproc.cvtColor(mesc, convertido, Imgproc.COLOR_BGR2BGRA,CvType.CV_16U);
                
                Core.addWeighted(regioes.elementAt(i).getImg().getImg(),0.75,mesc,0.25,0.5,dst);
                //Core.add(mesc, convertido, cenario.colRange(x1, x2).rowRange(y1,y2),new Mat(),CvType.CV_8U);
                dst.copyTo(cenario.colRange(x1, x2).rowRange(y1,y2));
                //Imgcodecs.imwrite("hue.png", imagem);
                //Imgcodecs.imwrite("hue1.png", imagem2);
                //Imgcodecs.imwrite("hue2.png", mesc);
                //Imgcodecs.imwrite("hue3.png", convertido);
                //Imgcodecs.imwrite("hue4.png", dst);
                
            }
        }
    }
}
