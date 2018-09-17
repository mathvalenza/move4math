/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Fase {
    private int numeroFase; //Numero da fase do jogo - 1, 2 ou 3
    private int CIT; //Conjunto Imagens de trabalho (numero do CIT)
    private int EST; //Estimulo - 0 = som, 1 = imagem e 2 = som e imagem

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


    /**
     * @return the EST
     */
    public int getEST() {
        return EST;
    }

    /**
     * @param EST the EST to set
     */
    public void setEST(int EST) {
        this.EST = EST;
    }

    /**
     * @return the numeroFase
     */
    public int getNumeroFase() {
        return numeroFase;
    }

    /**
     * @param numeroFase the numeroFase to set
     */
    public void setNumeroFase(int numeroFase) {
        this.numeroFase = numeroFase;
    }
    
}
