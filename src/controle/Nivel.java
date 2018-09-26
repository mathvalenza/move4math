/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import conteudo.Imagem;
import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Nivel {
    private int numeroLinha; // Numero da linha do arquivo niveis.csv
    private int numero; // Numero do nivel
    private int TAI; // Tamanho das imagens; P - Pequeno, M - Medio, G - Grande e GG - Extra Grande
    private int TEI; // Tempo de Exposicao da Imagem; P - Pequeno, M - Medio, G - Grande e GG - Extra Grande
    private int LAD; // Lado de Exposição do Simbolo, E - Esquerda, D - Direita e A - Ambos
    private int TIO; // Tamanho da Imagem Objetivo; P - Pequeno, M - Medio, G - Grande e GG - Extra Grande
    private int QIS; // Quantidade de Imagens Simultaneas; P - Pequeno, M - Medio, G - Grande e GG - Extra Grande
    private int QIO; // Quantidade de Imagens Objetivo; P - Pequeno (1), M - Medio (3), G - Grande (4) e GG - Extra Grande (5)
    private int OTI; // Ordem de Toque das Imagens; 0 - Indiferente; 1 - Crescente; 2 - Decrescente
    private int TEO; // Tempo de Exposição do Objetivo; P - Pequeno, M - Medio, G - Grande e GG - Extra Grande
    private String primeiroICC; //Imagens que compoem a cena da 1ª fase, respeitando o parametro QIS
    private String segundoICC; //Imagens que compoem a cena da 2ª fase, respeitando o parametro QIS
    private String terceiroICC; //Imagens que compoem a cena da 3ª fase, respeitando o parametro QIS

    /**
     * @return the numero
     */
     public int getNumeroLinha() {
        return numeroLinha;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumeroLinha(int numeroLinha) {
        this.numeroLinha = numeroLinha;
    }
    
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
       this.numero = numero;
        
    }

    /**
     * @return the TAI
     */
    public int getTAI() {
        return TAI;
    }

    /**
     * @param TAI the TAI to set
     */
    public void setTAI(int TAI) {
        this.TAI = TAI;
    }

    /**
     * @return the TEI
     */
    public int getTEI() {
        return TEI;
    }

    /**
     * @param TEI the TEI to set
     */
    public void setTEI(int TEI) {
        this.TEI = TEI;
    }

    /**
     * @return the LAD
     */
    public int getLAD() {
        return LAD;
    }

    /**
     * @param LAD the LAD to set
     */
    public void setLAD(int LAD) {
        this.LAD = LAD;
    }

    /**
     * @return the TIO
     */
    public int getTIO() {
        return TIO;
    }

    /**
     * @param TIO the TIO to set
     */
    public void setTIO(int TIO) {
        this.TIO = TIO;
    }

    /**
     * @return the QIS
     */
    public int getQIS() {
        return QIS;
    }

    /**
     * @param QIS the QIS to set
     */
    public void setQIS(int QIS) {
        this.QIS = QIS;
    }

    /**
     * @return the QIO
     */
    public int getQIO() {
        return QIO;
    }

    /**
     * @param QIO the QIO to set
     */
    public void setQIO(int QIO) {
        this.QIO = QIO;
    }

    /**
     * @return the OTI
     */
    public int getOTI() {
        return OTI;
    }

    /**
     * @param OTI the OTI to set
     */
    public void setOTI(int OTI) {
        this.OTI = OTI;
    }

    /**
     * @return the TEO
     */
    public int getTEO() {
        return TEO;
    }

    /**
     * @param TEO the TEO to set
     */
    public void setTEO(int TEO) {
        this.TEO = TEO;
    }

    /**
     * @return the primeiroICC
     */
    public String getPrimeiroICC() {
        return primeiroICC;
    }

    /**
     * @param primeiroICC the primeiroICC to set
     */
    public void setPrimeiroICC(String primeiroICC) {
        this.primeiroICC = primeiroICC;
    }

    /**
     * @return the segundoICC
     */
    public String getSegundoICC() {
        return segundoICC;
    }

    /**
     * @param segundoICC the segundoICC to set
     */
    public void setSegundoICC(String segundoICC) {
        this.segundoICC = segundoICC;
    }
    
        /**
     * @return the terceiroICC
     */
    public String getTerceiroICC() {
        return terceiroICC;
    }

    /**
     * @param terceiroICC the terceiroICC to set
     */
    public void setTerceiroICC(String terceiroICC) {
        this.terceiroICC = terceiroICC;
    }
}
