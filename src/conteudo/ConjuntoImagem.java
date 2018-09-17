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
public class ConjuntoImagem {

    private int id;
    private String descricao;
    private Vector< Vector<Imagem>> imagens;

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
     * @return the imagens
     */
    public Vector< Vector<Imagem>> getImagens() {
        return imagens;
    }

    /**
     * @param imagens the imagens to set
     */
    public void setImagens(Vector< Vector<Imagem>> imagens) {
        this.imagens = imagens;
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
}
