/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import conteudo.ConjuntoImagem;
import conteudo.Imagem;
import controle.Fase;
import controle.Jogos;
import controle.Nivel;
import controle.Parametros;
import controle.Publico;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.putText;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Partida implements Cloneable{
    private Player player;
    private ConjuntoImagem conjuntoImagem;
    private Fase fase;
    private Nivel nivel;
    private Jogos jogo;
    private Publico publico;
    private Vector<Imagem> filaElementos;
    private Vector<Imagem> filaElementosReferencia;
    private Parametros parametros;
    private int pontuacao;
    private List jogadasDoNivel = new ArrayList();
    
    /*
    int ptsEstrela1 = 20;
    int ptsEstrela2 = 50;
    int ptsEstrela3 = 150;
    int ptsEstrela4 = 180;
    int ptsEstrela5 = 540;
    */
    
    //Mat estrela = Imgcodecs.imread("Resources/images/estrela.png",1);
    Mat estrelaNegra = Imgcodecs.imread("Resources/images/Estrela-CinzaB.png",1);
    Mat vidasAtivas = Imgcodecs.imread("Resources/images/Vida_bom2.png",1);
    Mat vidasPerdidas = Imgcodecs.imread("Resources/images/vida-cinza.png",1);
    Mat barraBordas = Imgcodecs.imread("Resources/images/ref-background.png", 1);
    Mat escadaSubindo = Imgcodecs.imread("Resources/images/escada-subindo.png", 1);
    Mat escadaDescendo = Imgcodecs.imread("Resources/images/escada-descendo.png", 1);
    
            
    public List geraJogadasDoNivel(int nivelSelecionado){

        int linhaNivelSelecionado;
        linhaNivelSelecionado = (nivelSelecionado-1)*4;
        int aux = linhaNivelSelecionado;
        for(int i=0;i<4;i++){
                jogadasDoNivel.add(aux);
                aux ++;            
            }

        Collections.shuffle(jogadasDoNivel);
        return jogadasDoNivel;

    }

    public int selecionaLinhaNivel(int nivelSelecionado){
        int linhaNivelSelecionado;
        linhaNivelSelecionado = (nivelSelecionado-1)*4;
        return linhaNivelSelecionado;
    }
    
    public String [] imagensDaCena(int faseAtual, Nivel nivel){
        int tamanho = nivel.getQIS();
        
        String imagensCena[] = new String[tamanho];
        switch (faseAtual) {
            case 1:
                imagensCena = nivel.getPrimeiroICC().split(" ");
                break;
            case 2:
                imagensCena = nivel.getSegundoICC().split(" ");
                break;
            case 3:
                imagensCena = nivel.getTerceiroICC().split(" ");
                break;
            default:
                System.out.println("Erro! Fase não existente!");
        }

        for (int i=0;i<imagensCena.length;i++){
            //System.out.println(imagensCena[i]);
        }
        //filaElementosReferencia.add(imagensCena.)
        return imagensCena;
    }
    
    // refazer, criando um vetor de imagens de 4 posições, onde cada posição é a primeira imagem contida em ICC
    public void geraNovaFilaReferencias(String[] imagensCena){
        filaElementosReferencia.clear();
        Vector<Vector<Imagem>> imagens = new Vector<Vector<Imagem>>();
        imagens = conjuntoImagem.getImagens();
        MTRandom random = new MTRandom();
        int posicao;
        int tamanho;
        
        for(int i=0;i<imagens.size();i++){
            filaElementosReferencia.add(imagens.elementAt(i).elementAt(0));
            tamanho = imagens.elementAt(i).size();
 
            if(tamanho>1){
                posicao = 1+(random.nextInt((tamanho-1)));
                filaElementosReferencia.add(imagens.elementAt(i).elementAt(posicao));
            }else{
                filaElementosReferencia.add(imagens.elementAt(i).elementAt(0));
            }
        }
        
        Collections.shuffle(filaElementosReferencia);
        atualizaFilaElementos(filaElementosReferencia.firstElement().getGrupo());
        
    }
    
    public void atualizaFilaElementos(int indiceParaIgnorar){
        filaElementos.clear();
        Vector<Vector<Imagem>> imagens = new Vector<Vector<Imagem>>();
        imagens = conjuntoImagem.getImagens();
        
        Vector<Vector<Imagem>> imagensAux = (Vector<Vector<Imagem>>) imagens.clone();
        Imagem imgRef;
        
        String iccPrimeiro[] = nivel.getPrimeiroICC().split(" ");
        String iccSegundo[] = nivel.getSegundoICC().split(" ");
        String iccTerceiro[] = nivel.getTerceiroICC().split(" ");
        
        switch (move4math.Move4Math.indiceFaseAtual) {
            case 1:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                       for(int k=0; k<iccPrimeiro.length; k++){
                           if (Integer.parseInt(iccPrimeiro[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                               if (k==0){
                                   try {
                                       imgRef = imagens.elementAt(i).elementAt(j).clone();
                                       filaElementos.add(imgRef);
                                   } catch (CloneNotSupportedException ex) {
                                       Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                                   }
                                }else{
                                    filaElementos.add(imagens.elementAt(i).elementAt(j));
                                }

                            } 
                        }//verificar se o id pertence ao idsDoICC e, se sim, adicionar na filaElementos
                    }
                }
                break;
            case 2:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                       for(int k=0; k<iccSegundo.length; k++){
                           if (Integer.parseInt(iccSegundo[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                               if (k==0){
                                    try {
                                        imgRef = imagens.elementAt(i).elementAt(j).clone();
                                        filaElementos.add(imgRef);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    filaElementos.add(imagens.elementAt(i).elementAt(j));
                                }
                            } 
                        }//verificar se o id pertence ao idsDoICC e, se sim, adicionar na filaElementos
                    }
                }
                break;
            case 3:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                       for(int k=0; k<iccTerceiro.length; k++){
                           if (Integer.parseInt(iccTerceiro[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                                if (k==0){
                                    try {
                                        imgRef = imagens.elementAt(i).elementAt(j).clone();
                                        filaElementos.add(imgRef);
                                    } catch (CloneNotSupportedException ex) {
                                        Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    filaElementos.add(imagens.elementAt(i).elementAt(j));
                                }

                            } 
                        }//verificar se o id pertence ao idsDoICC e, se sim, adicionar na filaElementos
                    }
                }
                break;
            default:
                System.out.println("Não existe essa fase!");
        }

        //Collections.shuffle(filaElementos);
        //System.out.println("filaElementos: ");
        for (int i=0; i<filaElementos.size(); i++){
            //System.out.println("filaElementos.elementAt(" + i + "): " + filaElementos.elementAt(i).getId());
        }
        
    }
    
    public void shuffleElements(){
        Collections.shuffle(filaElementos);
    }
    
    public void geraFilaAleatoria(){
        filaElementos.clear();
        Vector<Vector<Imagem>> imagens = new Vector<Vector<Imagem>>();
        imagens = conjuntoImagem.getImagens();
        
        MTRandom number = new MTRandom();
        int referencia = number.nextInt(imagens.size());
        //System.out.println("Entrou no geraFilaAleatoria");
       
        String bufferPrimeiro[] = nivel.getPrimeiroICC().split(" ");
        String bufferSegundo[] = nivel.getSegundoICC().split(" ");
        String bufferTerceiro[] = nivel.getTerceiroICC().split(" ");

        System.out.println("Fase Atual: " + move4math.Move4Math.indiceFaseAtual);
        switch (move4math.Move4Math.indiceFaseAtual) {
            case 1:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                        for(int k=0; k<jogadasDoNivel.size(); k++){
                            if (Integer.parseInt(bufferPrimeiro[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                            filaElementos.add(imagens.elementAt(i).elementAt(j));
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                        for(int k=0; k<jogadasDoNivel.size(); k++){
                            if (Integer.parseInt(bufferSegundo[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                            filaElementos.add(imagens.elementAt(i).elementAt(j));
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<imagens.size();i++){
                    for(int j=0;j<imagens.elementAt(i).size();j++){
                        for(int k=0; k<jogadasDoNivel.size(); k++){
                            if (Integer.parseInt(bufferTerceiro[k]) == (imagens.elementAt(i).elementAt(j).getId())){
                            filaElementos.add(imagens.elementAt(i).elementAt(j));
                            }
                        }
                    }
                }
                break;
            default:
                System.out.println("Não existe essa fase!");
        }

        geraFilaReferenciaAleatoria(referencia);
    }

    public void geraFilaReferenciaAleatoria(int referencia){
        filaElementosReferencia.clear();
        Vector<Vector<Imagem>> imagens = new Vector<Vector<Imagem>>();
        imagens = conjuntoImagem.getImagens();
        
        Vector<Vector<Imagem>> imagensAux = new Vector<Vector<Imagem>>();
        imagensAux = conjuntoImagem.getImagens();
        
        //System.out.println("Entrou no geraFilaReferenciaAleatoria");
        String idsDoICC[] = imagensDaCena(move4math.Move4Math.indiceFaseAtual,nivel);
        //System.out.println(" id da referencia desejada: " + idsDoICC[0]);

        //Tipo de Jogo
        int iTipoJogoSelecionado = move4math.Move4Math.indiceJogoAtual;
        System.out.println("Jogo: " + iTipoJogoSelecionado);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Se o jogo for classificação, o primeiro ID do ICC é a imagem que será o objetivo
        // Se for o jogo de ordenação, contagem ou anterior e proximo, os primeiros 3 IDs do
        // ICC serão posicionados como objetivo da linha
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
       
        if (iTipoJogoSelecionado == 0){ //Jogo de Classificação
            for(int i=0;i<imagens.elementAt(referencia).size();i++){
                if (Integer.parseInt(idsDoICC[0]) == imagens.elementAt(referencia).elementAt(i).getId()){
                    filaElementosReferencia.add(imagens.elementAt(referencia).elementAt(i));
                }
            }
        } else { // Jogo de Ordenação, Contagem ou Anterior e Próximo
            for(int i=0;i<imagens.elementAt(referencia).size();i++){
                if ((Integer.parseInt(idsDoICC[0]) == imagens.elementAt(referencia).elementAt(i).getId()) || 
                        (Integer.parseInt(idsDoICC[1]) == imagens.elementAt(referencia).elementAt(i).getId()) ||
                        (Integer.parseInt(idsDoICC[2]) == imagens.elementAt(referencia).elementAt(i).getId())){
                    filaElementosReferencia.add(imagens.elementAt(referencia).elementAt(i));
                    //System.out.println("Adicionou na filaElementosReferencia: " + imagens.elementAt(referencia).elementAt(i).getId());
                }
            }
        }
        
        //filaElementosReferencia.add(imagens.elementAt(referencia).elementAt(0));
        // System.out.println("FilaElementosReferencia: ");
        for(int i=0;i<filaElementosReferencia.size();i++){
           // System.out.println(filaElementosReferencia.elementAt(i).getId());
        }
        //Collections.shuffle(filaElementosReferencia);
    }

    public void mostrarPontuacao(Mat cenario){
        String pontos;
        
        Mat roiPontos = cenario.submat(new Rect(new Point(500, 10), new Point(630, 110)));
        putText( roiPontos ,"Pontos", new Point(0,60), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );
        pontos = String.valueOf(pontuacao);
        putText( roiPontos , pontos, new Point(80,60), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );
    }
    
    public void mostrarVidas (Mat cenario, int tipoPublico){
        Mat dst;
        Imgproc.resize(vidasAtivas, vidasAtivas, new Size(30.0, 30.0));
        Imgproc.resize(vidasPerdidas, vidasPerdidas, new Size(30.0, 30.0));
        
        if(tipoPublico == 1){ //caso o publico seja Crianca Especial, o jogador tem 5 vidas
            int x1 = 25, x2 = 55;
            int x3 = 145, x4 = 175;
            //System.out.println("player.getVidas(): " + player.getVidas());
            //player.setVidas(2);
            for (int i=0; i<player.getVidas(); i++){ //desenha as vidas 
                dst = new Mat();            
                Mat vida1 = cenario.submat(new Rect(new Point(x1, 5),new Point(x2, 35)));
                Core.addWeighted(vidasAtivas,1.5,vida1,-0.5,0.0,dst);
                dst.copyTo(cenario.colRange(x1,x2).rowRange(5,35));
                x1 += 30;
                x2 += 30;
            }
            for (int i = player.getVidas(); i<5; i++){ //preenche o restante com sombras
                dst = new Mat();            
                Mat vida2 = cenario.submat(new Rect(new Point(x3, 5),new Point(x4, 35)));
                Core.addWeighted(vidasPerdidas,1.5,vida2,-0.02,0.0,dst);
                dst.copyTo(cenario.colRange(x3,x4).rowRange(5,35));
                x3 -= 30;
                x4 -= 30;
            }
        }else{ // caso contrário (crianca), o jogador possui 3 vidas
            int x1 = 25, x2 = 55;
            int x3 = 85, x4 = 115;
            //System.out.println("player.getVidas(): " + player.getVidas());
            //player.setVidas(1);
            //vidas
            for (int i=0; i<player.getVidas(); i++){
                dst = new Mat();            
                Mat vida1 = cenario.submat(new Rect(new Point(x1, 5),new Point(x2, 35)));
                Core.addWeighted(vidasAtivas,1.5,vida1,-0.5,0.0,dst);
                dst.copyTo(cenario.colRange(x1,x2).rowRange(5,35));
                x1 += 30;
                x2 += 30;
            }
            //sombras
            for (int i = player.getVidas(); i<3; i++){
                dst = new Mat();            
                Mat vida2 = cenario.submat(new Rect(new Point(x3, 5),new Point(x4, 35)));
                Core.addWeighted(vidasPerdidas,1.5,vida2,-0.02,0.0,dst);
                dst.copyTo(cenario.colRange(x3,x4).rowRange(5,35));
                x3 -= 30;
                x4 -= 30;
            }
        }
    }

    public void mostrarSombras (Mat cenario, int tipoPublico){
        Mat dst;

        //Tempo - Começo
        Imgproc.resize(barraBordas, barraBordas, new Size(206.0, 7.0));
        dst = new Mat();
        Mat roiBarra = cenario.submat(new Rect(new Point(220, 65), new Point(426, 72)));
        Core.addWeighted(barraBordas,1.0,roiBarra,0.65,0.0,dst);
        dst.copyTo(cenario.colRange(220,426).rowRange(65,72));
        //Tempo - Término
        
        //Estrela - Começo
        Imgproc.resize(estrelaNegra, estrelaNegra, new Size(30.0, 30.0));
        dst = new Mat();
        Mat roiEstrela1 = cenario.submat(new Rect(new Point(470, 5), new Point(500, 35)));
        Core.addWeighted(estrelaNegra,1.5,roiEstrela1,-0.02,0.0,dst);
        dst.copyTo(cenario.colRange(470,500).rowRange(5,35));
        dst = new Mat();
        Mat roiEstrela2 = cenario.submat(new Rect(new Point(500, 5), new Point(530, 35)));
        Core.addWeighted(estrelaNegra,1.5,roiEstrela2,-0.02,0.0,dst);
        dst.copyTo(cenario.colRange(500,530).rowRange(5,35));
        dst = new Mat();
        Mat roiEstrela3 = cenario.submat(new Rect(new Point(530, 5), new Point(560, 35)));
        Core.addWeighted(estrelaNegra,1.5,roiEstrela3,-0.02,0.0,dst);
        dst.copyTo(cenario.colRange(530,560).rowRange(5,35));
        dst = new Mat();
        Mat roiEstrela4 = cenario.submat(new Rect(new Point(560, 5), new Point(590, 35)));
        Core.addWeighted(estrelaNegra,1.5,roiEstrela4,-0.02,0.0,dst);
        dst.copyTo(cenario.colRange(560,590).rowRange(5,35));
        dst = new Mat();
        Mat roiEstrela5 = cenario.submat(new Rect(new Point(590, 5), new Point(620, 35)));
        Core.addWeighted(estrelaNegra,1.5,roiEstrela5,-0.02,0.0,dst);
        dst.copyTo(cenario.colRange(590,620).rowRange(5,35));
        //Estrela - Término
        
    }

    void mostraEscada (Mat cenario){
        Mat dst;
        
        Imgproc.resize(escadaSubindo, escadaSubindo, new Size(50.0, 50.0));
        Imgproc.resize(escadaDescendo, escadaDescendo, new Size(50.0, 50.0));
        
        int iSentido = nivel.getOTI();
        
        switch(iSentido){
            case 1:
                dst = new Mat();            
                Mat subindo = cenario.submat(new Rect(new Point(200, 15),new Point(250, 65)));
                Core.addWeighted(escadaSubindo,1.0,subindo,0.5,0.0,dst);
                dst.copyTo(cenario.colRange(200,250).rowRange(15,65)); 
                break;
            case 2:
                dst = new Mat();            
                Mat descendo = cenario.submat(new Rect(new Point(200, 15),new Point(250, 65)));
                Core.addWeighted(escadaDescendo,1.0,descendo,0.5,0.0,dst);
                dst.copyTo(cenario.colRange(200,250).rowRange(15,65)); 
                break;
        }
        System.out.println("\nPassou mostra escada\n");
    }
    
     void removeElementoDaFila (Vector<Imagem> filaElementos){
         filaElementos.remove(0);
     }   
        
    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the conjuntoImagem
     */
    public ConjuntoImagem getConjuntoImagem() {
        return conjuntoImagem;
    }

    /**
     * @param conjuntoImagem the conjuntoImagem to set
     */
    public void setConjuntoImagem(ConjuntoImagem conjuntoImagem) {
        this.conjuntoImagem = conjuntoImagem;
    }

    /**
     * @return the fase
     */
    public Fase getFase() {
        return fase;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(Fase fase) {
        this.fase = fase;
    }

    /**
     * @return the nivel
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the filaElementos
     */
    public Vector<Imagem> getFilaElementos() {
        return filaElementos;
    }

    /**
     * @param filaElementos the filaElementos to set
     */
    public void setFilaElementos(Vector<Imagem> filaElementos) {
        this.filaElementos = filaElementos;
    }

    /**
     * @return the filaElementosReferencia
     */
    public Vector<Imagem> getFilaElementosReferencia() {
        return filaElementosReferencia;
    }

    /**
     * @param filaElementosReferencia the filaElementosReferencia to set
     */
    public void setFilaElementosReferencia(Vector<Imagem> filaElementosReferencia) {
        this.filaElementosReferencia = filaElementosReferencia;
    }

    /**
     * @return the parametros
     */
    public Parametros getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the pontuacao
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * @param pontuacao the pontuacao to set
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * @return the jogo
     */
    public Jogos getJogo() {
        return jogo;
    }

    /**
     * @param jogo the jogo to set
     */
    public void setJogo(Jogos jogo) {
        this.jogo = jogo;
    }

    /**
     * @return the publico
     */
    public Publico getPublico() {
        return publico;
    }

    /**
     * @param publico the publico to set
     */
    public void setPublico(Publico publico) {
        this.publico = publico;
    }
    
}