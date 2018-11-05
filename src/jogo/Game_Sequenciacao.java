/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import com.opencsv.CSVWriter;
import conteudo.ConjuntoImagem;
import conteudo.Imagem;
import controle.Jogos;
import controle.Nivel;
import controle.Publico;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import move4math.MainWindow;
import move4math.Move4Math;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import static org.opencv.imgproc.Imgproc.putText;


/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Game_Sequenciacao extends javax.swing.JFrame {
        
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int) screenSize.getWidth();
    int screenHeight = (int) screenSize.getHeight();
    
    Mat frame, cenario, cenarioAnterior, dst, dst2;
    MatOfByte mem;
    
    private boolean gradesVisiveis;
    Grade gradeEsq;
    Grade gradeDir;
    
    int contGenerateBlob;
    int contChangeCenario;
    int contCheckCollision;
    
    int NST, contNST, numAcertos,numErros, numErrosLimite, numRodadasGeradas, somaTempoToque;
    int posicaoJogadasDoNivel = 1;
    int numSimbolosFilaGeradosAtual, numSimbolosParaGerar;
    int iPontosMotor, iPontosCognitivo, iNiveisRetrocedidos, iNiveisRepetidos;
    int iPontosAnt = 0, iPontosAtual = 0, iPontosAux = 0, iDiferenca = 0;
    
    int SLEEP_BEFORE_GENERATE_BLOB = 3000; //em milisegundos
    
    int elementoDaFila = 0;
    float diferenca;
    
    boolean piscarTopo;
    boolean fimDeJogo = false;
    boolean pausado = true;
    boolean feedback2 = false;
    boolean feedback2Aux = false;
    boolean reproduzirAudio = false;
    boolean reproduzirAudioRelogio = true;
     
    int tipoFeedback; //0 - Avança linha;1 - Avança nivel; 2 - Permanece nível; 3 - Retrocede nivel;
                      //4 - Avança fase; 5 - Retrocede fase; 6 - Perde vida;
    Referencia referencia, referencia2, referencia3;
    int tempoExposicao;

    boolean primeiroToque = true;
    boolean mostrarReferencias = true;
    boolean mostrarEstrelas = false;
    boolean jogando=false, irParaProximaLinha=true , geraProximaLinha=true;
    int numAcertosNaRodada=0;
    
    int segundos, segundosAux, segundosAux2=0;
    int minutos=0, minutosAux=0, minutosAux2=0;
    int auxTopoFeedback=0;
    int contCronometro=0;
    List numEstrelasNivel = new ArrayList();
    
    int idTemp=0;
    int contAguarda=0;
    
    double tamanhoEFeedback=100;
    
    int tipoColisao=0; //0 = omitiu; 1 = acertou; 2 = errou
    
    String descRef;
    
    Calendar espera;
    
    int[] testeSequenciaTriangulos = {25,26,27}; // triangulo borda fina, grossa e preenchida
    
    private void ReiniciaVariaveis(){
        piscarTopo = true;
        fimDeJogo = false;
        pausado = true;
        feedback2 = false;
        feedback2Aux = false;
        reproduzirAudio = true;
        reproduzirAudioRelogio = true;
        primeiroToque = true;
        mostrarReferencias = true;
        mostrarEstrelas = false;
        jogando=false;
        irParaProximaLinha=true;
        geraProximaLinha=true;
        numAcertosNaRodada=0;
        
        tipoFeedback = 0;
        
        segundos=0;
        segundosAux=0;
        segundosAux2=0;
        minutos=0;
        minutosAux=0;
        minutosAux2=0;
        auxTopoFeedback=0;
        contCronometro=0;
        
        numEstrelasNivel = new ArrayList();
        idTemp=0;
        contAguarda=0;
        tamanhoEFeedback=100;
        tipoColisao=0;
        
    }
    
    public Game_Sequenciacao(){
        this.referencia = new Referencia();
        this.referencia2 = new Referencia();
        this.referencia3 = new Referencia();
        initComponents();
                
        fimDeJogo = false;
        MainWindow.tecla = null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        
        //System.out.println("jogo.Game.formKeyPressed() " + fimDeJogo);
        if(fimDeJogo){
            this.dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed
    
// NOVO    
    public void Iniciar(Game_Sequenciacao gameWindow, Jogos jogo, Publico publico, Player player, Vector<ConjuntoImagem> conjuntosDeTrabalho,int indexFase, int indexNivel) throws IOException, InterruptedException{
        try{
            jPanel1.setSize(screenWidth, screenHeight);
            //System.out.println(gameWindow.getClass() + " \n" + jogo.getClass() + " \n" + player.getClass());
            //System.out.println("Nivel em 01: " + indexNivel); 
//            System.out.println("01J: " + jogo.getNome() + " Pu: " + publico.getNome() + " Pl: " + player.getNome() + " CIT:" + conjuntosDeTrabalho.size() + " F:" + indexFase + " N:" + indexNivel);
            Thread t = new Thread(new WebcamFeed(gameWindow, jogo, publico,  player, conjuntosDeTrabalho, indexFase,  indexNivel));
            t.start();            
            //t.join();
            //gameWindow.dispose();
        }catch (Exception e){
            System.out.println(e.getClass() + e.getMessage() + e.getStackTrace());
       }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


    public boolean isGradesVisiveis() {
        return gradesVisiveis;
    }

    public void setGradesVisiveis(boolean gradesVisiveis) {
        this.gradesVisiveis = gradesVisiveis;
    }

    class WebcamFeed implements Runnable{
        
        private Game_Sequenciacao gamewindow;
        private Game_Sequenciacao gameWindow;
        private Jogos jogo;
        private Publico publico;
        private Player player;
        private Vector<ConjuntoImagem> conjuntosDeTrabalho;
        private int indexFase; int indexNivel;
        private List jogadasDoNivel;
        private int linhaNivelSelecionado;
        
        Mat topo = Imgcodecs.imread("Resources/images/topo.png",1);
        Mat topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
        Mat estrela = Imgcodecs.imread("Resources/images/star_.png",1);
        Mat sombraObjetivo = Imgcodecs.imread("Resources/images/sombraObjetivo.png",1);
        Calendar tempoCenario;  //tempo de espera entre salvar os cenarios
        Calendar tempoAtual;    //usado para calcular o cronometro do jogo
        Calendar cronometro;    //o tempo de jogo
        Calendar gerarRodada;   //guarda quando a rodada foi gerada
        Calendar mostrarBlobs;  //guarda quando os blobs foram mostrados
        
        public WebcamFeed(Game_Sequenciacao gameWindowParametro, Jogos jogo, Publico publico, Player player, Vector<ConjuntoImagem> conjuntosDeTrabalho,int indexFase, int indexNivel){
            System.out.println("\n________________________________________");
            System.out.println("\nJogo: " + jogo.getNome() + "\nPublico: " + publico.getNome() + "\nPlayer: " + player.getNome() + "\nconjuntosDeTrabalho.size(): " + conjuntosDeTrabalho.size() + "\nFase: " + indexFase + "\nNivel: " + indexNivel);
            System.out.println("-------");
            int nivelParaSetar = 0;
            
            // o vetor de níveis é percorrido setando-se corretamente os níveis
            for (int i = 0; i<publico.getNiveis().size(); i++){
                //System.out.println("i: " + i + " | i%4: " + i%4);
                if (i%4 == 0){
                    nivelParaSetar++;
                }
                publico.getNiveis().elementAt(i).setNumero(nivelParaSetar);
            }
            
            this.gamewindow = gameWindowParametro;
            this.gameWindow = gameWindow;
            this.jogo = jogo;
            this.publico = publico;
            this.player = player;
            this.conjuntosDeTrabalho = conjuntosDeTrabalho;
            this.indexFase = indexFase;
            this.indexNivel = indexNivel;
            
        }

        @Override
        public void run() {
            
            ReiniciaVariaveis();
             
            //seta a webcam e resolução
            //o zero na linha abaixo é o indice da webcam no sistema - se houver mais de uma é necessario alterar (ou criar uma caixa de dialogo para escolher... xD)
            VideoCapture webSource= new VideoCapture(0);
            webSource.set(Videoio.CV_CAP_PROP_FRAME_WIDTH,Move4Math.webcamWidth);
            webSource.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT,Move4Math.webcamHeight);

            //cria nova partida
            Partida partida = new Partida();
            partida.setJogo(jogo);
            partida.setPublico(publico);
            //partida.setJogo(publico.getJogos().elementAt(jogo.getId()).getNome().setId(Move4Math.getJogoId(jogo.getId());
            partida.setFase(publico.getFases().elementAt(indexFase-1)); //gambiarra

            jogadasDoNivel = partida.geraJogadasDoNivel(indexNivel);
            System.out.println("jogadas do nivel " + jogadasDoNivel.toString());

            linhaNivelSelecionado = partida.selecionaLinhaNivel(indexNivel);

            int o = Integer.parseInt(jogadasDoNivel.get(0).toString());// dava problema, pois eu setava o próximo nivel a partir do cálculo da primeira linha; mas este deve ser setado com a primeira posição do jogadasDoNivel

            partida.setNivel(publico.getNiveis().elementAt(o)); // é setado o "nivel" pelo JOGADASDONIVEL na primeira posição. O avanço depois é dado pela transiçãoDeLinha

            partida.setPlayer(player); //acho que não precisa, pois podemos alterar o player que foi passado por parametro na funcao
            //int CIT2 = partida.getJogo().getCIT();
            //int CIT = publico.getFases().elementAt(indexFase - 1).getCIT();
//            System.out.println("AQUI: " + partida.getJogo().getNome() + " " + partida.getPublico().getNome() + " " + partida.getFase().getNumeroFase()+" "+partida.getNivel().getNumero());

            partida.imagensDaCena(move4math.Move4Math.indiceFaseAtual,partida.getNivel());

            partida.setPontuacao(0);

            partida.setFilaElementos(new Vector<Imagem>());
            partida.setFilaElementosReferencia(new Vector<Imagem>());

            //procura a fase no vetor de conjuntos de trabalho

            //publico.get
            int CIT = publico.getFases().elementAt(indexFase-1).getCIT();//gambiarra
            
            System.out.println("\n opaaa " + player.getSexo());
            
            if ("Masculino".equals(player.getSexo())) {
                System.out.println("\n\nMasculino\n\n");
                estrela = Imgcodecs.imread("Resources/images/bola.png",1);
            } else {
                estrela = Imgcodecs.imread("Resources/images/star_.png",1);
            }
            
            if (partida.getNivel().getNumero() <= 6) {
                for(int i=0;i<conjuntosDeTrabalho.size();i++){
                    if(conjuntosDeTrabalho.elementAt(i).getId()==CIT){
                        partida.setConjuntoImagem(conjuntosDeTrabalho.elementAt(i));
                        //partida.getConjuntoImagem().setImagens(conjuntosDeTrabalho.elementAt(i).getImagens());
                        break;
                    }
                }
            } else if (partida.getNivel().getNumero() <= 8) {
                partida.setConjuntoImagem(conjuntosDeTrabalho.elementAt(1));
            } else {
                partida.setConjuntoImagem(conjuntosDeTrabalho.elementAt(0));
            }
            
            //imagens do cenario

            Mat degrade = Imgcodecs.imread("Resources/images/TES-degrade.png", 1);
            Mat barraBordas = Imgcodecs.imread("Resources/images/TES-vazio.png", 1);
            Mat ref_background = Imgcodecs.imread("Resources/images/ref-background.png", 1);
            Mat silhueta = Imgcodecs.imread("Resources/images/corpo.png",1);

            //adicionando a imagem das vidas
            //Mat imgVida = Imgcodecs.imread("Resources/images/vida.png",1);

            //Imgproc.resize(imgVida, imgVida, new Size(25, 25));

            gradeEsq = new Grade(0, 640, 480); //devia ser webSource.get(Videoio.CV_CAP_PROP_FRAME_WIDTH e HEIGHT
            gradeDir = new Grade(1, 640, 480); // mas aí teria que normalizar os pontos das partes em que se desenham os elementos na tela...

            gradeEsq.setNumImagens(0); //começa vazia
            gradeDir.setNumImagens(0); //começa vazia

            gradeEsq.setTamanhoGrade(partida.getNivel().getTAI());
            gradeDir.setTamanhoGrade(partida.getNivel().getTAI());

            setGradesVisiveis(false);

            contGenerateBlob = 3; //tempo de espera para gerar a rodada
            contChangeCenario = 0;
            contCheckCollision = 0;

            NST = partida.getNivel().getQIO(); //numero de simbolos para recalcular o nivel (??)
            System.out.println("\n\n partida.getNivel().getQIO(): " + partida.getNivel().getQIO());
            // NST = 3; //teste

            contNST = 0;

            Sessao sessao = new Sessao();
            Vector<Rodada> rodadas = new Vector<Rodada>();
            String jogo = "";
            int idJogoAtual = Move4Math.indiceJogoAtual;
            switch (idJogoAtual){
                case 0:
                    jogo = "Classificacao";
                    break;
                case 1:
                    jogo = "Ordenacao";
                    break;
                case 2:
                    jogo = "Sequenciacao";
                    break;
                case 3:
                    jogo = "AnterioProximo";
                    break;
                default:
                    break;
            }
            //if(partida.getPlayer().getSessoes().size()>0){
            // se a primeira sessao do player a fase estiver em branco, significa que nao tinha sessao
            // ou que a sessao estava em branco
            //ID da sessão    
            sessao.setId(partida.getPlayer().getSessoes().size()+1);
            //Data de Uso
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = sdf.format(new Date());
            sessao.setData(date);
            //Hora de Uso
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
            Date hora = Calendar.getInstance().getTime();
            String horaInicio = sdfHora.format(hora);
            sessao.setHora(horaInicio);
            //Jogo
            sessao.setJogo(jogo);
            //Fase
            sessao.setFase(Integer.toString(partida.getFase().getNumeroFase()));
            //Nível
            sessao.setNivel(Integer.toString(partida.getNivel().getNumero()));
            //sessao.setTempoTotal(partida.getTempo());
            sessao.setRodadas(rodadas);
            player.getSessoes().add(sessao);
            frame = new Mat();
            cenario = new Mat();
            mem = new MatOfByte();
            //cenarioAnterior = new Mat();
            cenarioAnterior = new Mat().zeros((int)webSource.get(Videoio.CV_CAP_PROP_FRAME_WIDTH),(int)webSource.get(Videoio.CV_CAP_PROP_FRAME_HEIGHT), CvType.CV_8UC3);

            boolean gerouImagens = false;
            boolean gerouBlobs = false;
            int houveColisao = 0;
            tipoColisao = 0;

            numSimbolosFilaGeradosAtual = 0;            
            segundosAux2 = segundosAux = segundos;
            //tela de calibração
            while(webSource.grab()){
                 try{
                    jPanel1.setSize(screenWidth, screenHeight);
                    webSource.retrieve(frame);
                    Core.flip(frame,cenario,1);
                    // -+-+-+-+-+-+ grava o topo-background
                    Imgproc.resize(topo, topo, new Size(640.0, 77.0));
                    dst = new Mat();
                    Mat roiTopo = cenario.submat(new Rect(new Point(0, 0),new Point(640, 77)));
                    Core.addWeighted(roiTopo,1.5,topo,2.0,0.0,dst);
                    dst.copyTo(cenario.colRange(0,640).rowRange(0,77));
                    // -+-+-+-+-+-+  mostra Silhueta
                    dst = new Mat();
                    Mat roiSilhueta = cenario.submat(new Rect(new Point(220, 230),new Point(420, 480)));
                    Core.addWeighted(roiSilhueta,1.0,silhueta,0.8,0.0,dst);
                    dst.copyTo(cenario.colRange(220,420).rowRange(230,480));

                    Mat roiCalibrando = cenario.submat(new Rect(new Point(260, 25), new Point(420, 110)));
                    putText( roiCalibrando,"Posicione-se!", new Point(0,20),Core.FONT_HERSHEY_COMPLEX,0.6,new Scalar(0,0,0), 2, 9, false );
                    gradeEsq.showRegioes(cenario);
                    gradeDir.showRegioes(cenario);

                    Imgcodecs.imencode(".bmp", cenario, mem);
                    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                    BufferedImage buff = (BufferedImage) im;
                    //desenha o cenario no jpanel
                    Graphics g=jPanel1.getGraphics();

                    if(buff!=null){
                        g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);
                    }

                    //teclas de atalho ESC, enter ou space pra começar
                    if((MainWindow.tecla.getKeyCode() == KeyEvent.VK_ESCAPE)||(MainWindow.tecla.getKeyCode()==KeyEvent.VK_ENTER)||(MainWindow.tecla.getKeyCode()==KeyEvent.VK_SPACE)){
                        MainWindow.tecla = null;
                        break;
                    }
                } //fim try
                catch(Exception ex){
                    //System.out.println("erro gravando frame");
                }
            }//fim while

            int segAnterior=0;
            int tempoExposicao = partida.getNivel().getTEI()*1000; // Define o tempo de exposição das imagens.
            int tempoExposicaoReferencia = (int)(tempoExposicao*0.25);
            tempoCenario = Calendar.getInstance();
            tempoAtual  = Calendar.getInstance();//usado para calcular o cronometro do jogo
            cronometro = Calendar.getInstance();//o tempo de jogo
            gerarRodada = Calendar.getInstance();//guarda quando a rodada foi gerada
            mostrarBlobs = Calendar.getInstance();//guarda quando os blobs fora mostrados na tela para calculo de pontuação

            cronometro.set(Calendar.SECOND, 0);
            cronometro.set(Calendar.MINUTE, 0);

            //tela do jogo 
            while(webSource.grab()){
                try{
                    jPanel1.setSize(screenWidth, screenHeight);
                    webSource.retrieve(frame);
                    Core.flip(frame,cenario,1);

                    // -+-+-+-+-+-+ grava o topo-background
                    Imgproc.resize(topo, topo, new Size(640.0, 77.0));
                    dst = new Mat();
                    Mat roiTopo = cenario.submat(new Rect(new Point(0, 0),new Point(640, 77)));
                    Core.addWeighted(roiTopo,1.5,topo,2.0,0.0,dst);
                    dst.copyTo(cenario.colRange(0,640).rowRange(0,77));

                    // -+-+-+-+-+-+ mostra o relogio
                    /*
                    Mat roiClock = cenario.submat(new Rect(new Point(70, 10), new Point(170, 110)));
                    putText( roiClock ,"Tempo", new Point(5,20),Core.FONT_HERSHEY_COMPLEX,0.6,new Scalar(0,0,0), 2, 9, false );
                    Mat roiTempo = roiClock.submat(new Rect(0,20,80,80));
                    */
                    if(Calendar.getInstance().getTimeInMillis()>tempoAtual.getTimeInMillis()+1000){
                        tempoAtual = Calendar.getInstance();
                        cronometro.set(Calendar.SECOND, cronometro.get(Calendar.SECOND)+1);
                    }
                    segundos = cronometro.get(Calendar.SECOND); //segundos começando de 00
                    minutos = cronometro.get(Calendar.MINUTE); //minutos começando de 00

                    // -+-+-+-+-+-+  mostra logoEsq e logoDir
                    
                    /*dst = new Mat();
                    Mat roiLogoEsq = cenario.submat(new Rect(new Point(0, 0),new Point(25, 27)));
                    Core.addWeighted(roiLogoEsq,1.0,logoEsq,0.8,0.0,dst);
                    dst.copyTo(cenario.colRange(0,25).rowRange(0,27));
                      
                    dst = new Mat();
                    Mat roiLogoDir = cenario.submat(new Rect(new Point(615, 0),new Point(640, 27)));
                    Core.addWeighted(roiLogoDir,1.0,logoDir,0.8,0.0,dst);
                    dst.copyTo(cenario.colRange(615,640).rowRange(0,27));

                    */
                    //tentando acrescentar a imagem do coração

                    partida.mostrarVidas(cenario,Move4Math.indicePublicoAtual);
                    partida.mostrarSombras(cenario,Move4Math.indicePublicoAtual);
                    
                    /*
                    for(int i =0; i< partida.getPlayer().getVidas(); i++){
                        dst = new Mat();
                        Mat roiImgVidas = cenario.submat(new Rect(new Point(32+(i*25), 15),new Point(82+(i*25), 65)));
                        Core.addWeighted(imgVida,1.0,roiImgVidas,0.3,0.0,dst);
                        dst.copyTo(cenario.colRange(32+(i*25),82+(i*25)).rowRange(15,65));
                    }
                    */
                    
                    //fim tentando incluir imagem do coração

                    // -+-+-+-+-+-+  mostra Fase e Nivel
                    int qtdFases = publico.getFases().lastElement().getNumeroFase();
                    Mat roiFase = cenario.submat(new Rect(new Point(20, 40),new Point(150, 100)));
                    if (partida.getNivel().getNumero() < 10){
                        putText( roiFase, "Fase: " + partida.getFase().getNumeroFase() + " de " + qtdFases, new Point(10,12), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );
                    }else{
                        putText( roiFase, "Fase: " + partida.getFase().getNumeroFase() + "  de " + qtdFases, new Point(10,12), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );
                    }

                    int qtdNiveis = publico.getNiveis().size() / 4;
                    Mat roiNivel = cenario.submat(new Rect(new Point(20, 60),new Point(150, 120)));
                    putText( roiNivel ,"Nivel: " + String.valueOf(partida.getNivel().getNumero()) + " de " + qtdNiveis, new Point(10,11), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );
                    //putText( roiNivel , String.valueOf(partida.getNivel().getNumero()), new Point(5,28), Core.FONT_HERSHEY_SIMPLEX,0.5,new Scalar(0,0,0),1,8,false );

                    // -+-+-+-+-+-+ mostra grades isGradesVisiveis()
                    /*
                    if(isGradesVisiveis()){
                            //gradeEsq.showRegioes(cenario);
                            //gradeDir.showRegioes(cenario);
                    }
                    */
                    if (tipoColisao != 0 && mostrarEstrelas){
                        //System.out.println("dif: " + iDiferenca + " | ptsAtual: " + iPontosAtual + " | ptsAnt: " + iPontosAnt);
                        mostrarEstrelas(cenario, iDiferenca);
                        if (numAcertosNaRodada < partida.getNivel().getQIO()){
                            //System.out.println("Chamou o mostrarTopoFeedback junto com o mostrarEstrelas");
                            mostrarTopoFeedback(piscarTopo, partida); 
                        }else{
                            //topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                            mostrarTopoFeedback(piscarTopo, partida);
                        }
                            //iDiferenca = 0;
                    }

                    // -+-+-+-+-+-+ mostra pontuação
                    partida.mostrarPontuacao(cenario);

                    //Jogo de Ordenação
                    if (Move4Math.indiceJogoAtual == 1){
                        partida.mostraEscada(cenario);
                    }
                    /*
                        partida.mostrarTrofeus(cenario,partida.getNivel().getNumero()*3);
                        // -+-+-+-+-+-+ gerar rodada
                        if ((segundos == 0 || segundos == 1)){
                            if (60 - segundosAux2 < 3){    
                                segundosAux2 = 57;
                            }
                            if (60 - segundosAux < partida.getNivel().getTEO()){
                                System.out.println("segundosAux = 60 - partida.getNivel().getTEO();");
                                segundosAux = 60 - partida.getNivel().getTEO();
                            }
                        }

                        System.out.println("minutos: " + minutos);
                        System.out.println("tempoAtual = " + minutos + ":" + segundos + 
                                " | tempoAux = " + minutosAux + ":" + segundosAux + 
                                " | tempoAux2 = " + minutosAux2 + ":" + segundosAux2);
                   // */
                        
                    if (irParaProximaLinha == false){
                        if ((60*minutos + segundos) - (60*minutosAux2 + segundosAux2) < 2){
                            //System.out.println("aguardando...");
                            geraProximaLinha = false;
                            piscarTopo = false;
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedbackAcerto.png",1);
                            if (feedback2Aux){ //pra nao sobrepor imagens de feedback
                                //aguarda(0);
                                aguarda(tipoFeedback);
                            }else{
                                aguarda(0); //imagem de transicao de linha
                            }
                            segundosAux = segundos;
                            minutosAux = minutos;
                        }else{
                                //if((partida.getNivel().getNumeroLinha() - 1) != (int) jogadasDoNivel.get(0) && mostrarReferencias){
                                //    System.out.println("Chamou o mostrarTopoFeedback que NAO EH da primeira linha");
                                //    mostrarTopoFeedback(true, tempoAtual.getTimeInMillis());
                                //}
                                if (feedback2){
                                    irParaProximaLinha = false;
                                    segundosAux2 = segundos;
                                    feedback2Aux = true;
                                }else{
                                    irParaProximaLinha = false;
                                    geraProximaLinha = true;
                                    contAguarda=0;
                                    if (feedback2Aux){
                                        mostrarReferencias = true;
                                        feedback2Aux = false;
                                    }
                                }
                                feedback2 = false;
                            }
                        }

                        if((partida.getNivel().getNumeroLinha() - 1) == (int) jogadasDoNivel.get(0) && mostrarReferencias){
                                mostrarTopoFeedback(piscarTopo, partida);
                        }

                        if ((partida.getNivel().getNumeroLinha() - 1) == (int) jogadasDoNivel.get(3) && (numAcertosNaRodada == NST || numErrosLimite == 16)){
                            segundosAux2 = segundos;
                            minutosAux2 = minutos;
                            verificaTransicaoDeNivel(partida);
                        }
                        if((gradeEsq.getNumImagens()==0)&&(gradeDir.getNumImagens()==0) && geraProximaLinha){
                            /*
                            * Antes de gerar, espera um tempo com o contador SLEEP_B
                            * para que o jogador possa baixar os braços antes de gerar novas imagens.
                            */
                            if((partida.getNivel().getNumeroLinha() - 1) == (int) jogadasDoNivel.get(0) && mostrarReferencias){
                                piscarTopo = true;
                                topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                            }
                            float tempoJogada = ((mostrarBlobs.getTimeInMillis()+tempoExposicao) - Calendar.getInstance().getTimeInMillis());
                            tempoJogada = (tempoJogada/1000 < 0)? 0 : tempoJogada/1000;
                            float tempoExposicaoObjetivo = partida.getNivel().getTEO();
                            auxTopoFeedback = 0; //auxTopoFeedback é a variavel que faz piscar o topo amarelo
                            //incrementa NST
                            contNST++;
                            if(numRodadasGeradas != NST*4){
                                numRodadasGeradas++;
                            }
                            switch (partida.getNivel().getTAI()) {
                                case 3:
                                    numSimbolosParaGerar=2;
                                    break;
                                case 2:
                                    numSimbolosParaGerar=4;
                                    break;
                                case 1:
                                    numSimbolosParaGerar=6;
                                    break;
                                default:
                                    numSimbolosParaGerar=8;
                                    break;
                            }
                            /*
                            numSimbolosParaGerar = partida.getNivel().getQIS();
                            se vai gerar na esquerda, direita ou ambos
                            //*/
                            reproduzirAudioRelogio = true;
                            switch (partida.getNivel().getLAD()) {
                                case 1:
                                    // 1=esquerda, 2=direita, 3=ambos
                                    gerarImagens2(gradeEsq, partida, numSimbolosParaGerar, true, true);
                                    gerarRodada = Calendar.getInstance();
                                    break;
                                case 2:
                                    gerarImagens2(gradeDir, partida, numSimbolosParaGerar, true, true);
                                    gerarRodada = Calendar.getInstance();
                                    break;
                                case 3:
                                    // sorteia um lado para conter o elemento igual ao da referencia
                                    MTRandom number = new MTRandom();
                                    int escolha = number.nextInt(2);
                                    numSimbolosParaGerar = (numSimbolosParaGerar/2);
                                    if(escolha == 0){
                                        partida.geraFilaAleatoriaSequenciacao(partida.getNivel().getQIO());
                                        gerarImagens2(gradeEsq, partida, numSimbolosParaGerar, true, true);
                                        gerarImagens2(gradeDir, partida, numSimbolosParaGerar, false,false);

                                    }else if(escolha == 1){
                                        partida.geraFilaAleatoriaSequenciacao(partida.getNivel().getQIO());
                                        gerarImagens2(gradeDir, partida, numSimbolosParaGerar, true, true);
                                        gerarImagens2(gradeEsq, partida, numSimbolosParaGerar, false, false);

                                    }
                                    gerarRodada = Calendar.getInstance();
                                    break;
                                default:
                                    break;
                            }

                                gerouImagens = true;
                            //}//fimteste
                        }else{

                            // -+-+-+-+-+-+ mostra imagem e/ou som de REFERENCIA
                            if( partida.getFase().getEST() != 0) {
                                //mostra quantidade de imagens do objetivo
                                int deslocamento=0;
                                if (partida.getNivel().getQIO() == 3){
                                    deslocamento = 50;
                                } else if (partida.getNivel().getQIO() == 2) {
                                    deslocamento = 75;
                                } else if(partida.getNivel().getQIO() == 4){
                                    deslocamento = 25;
                                }
                                referencia.setX(referencia.getX() + deslocamento);
                                referencia2.setX(referencia.getX() + referencia2.getWidth());
                                referencia3.setX(referencia3.getX() + deslocamento);

                                // mostrando imagens referencia
                                if(mostrarReferencias && numAcertosNaRodada < partida.getNivel().getQIO()){ //No lugar do '3' seria partida.getNivel().getQIO() ????
                                    //mostrarReferencias é uma variável booleana que é desabilitada quando a função ocultaReferencia é chamada
                                    for (int i = 0;i<partida.getNivel().getQIO();i++){
                                        if (i != 0) {
                                            Imagem elemento = partida.getFilaElementosReferencia().get(i);
                                            Imagem imgRefTemp = new Imagem(elemento);
                                            referencia2.setWidth(partida.getNivel().getTIO());
                                            referencia2.setHeight(partida.getNivel().getTIO());
//
                                            referencia2.setY(10);
                                            Mat tempRef = imgRefTemp.getImg();
                                            Imgproc.resize(tempRef,tempRef, new Size(50.0, 50.0));
                                            referencia2.setImagem(tempRef);
                                            referencia2.setId(imgRefTemp.getId());
//                                            
                                            descRef = imgRefTemp.getDescricao();
//                                            //coloca as descricoes
                                            referencia2.setRefImgStr(imgRefTemp.getDescricao());
                                            referencia2.setSom(imgRefTemp.getSom());
                                            referencia2.setGrupo(imgRefTemp.getGrupo());
                                            
                                            dst2 = new Mat();
                                            Mat mescRef = cenario.submat(new Rect(new Point(referencia2.getX(), referencia2.getY()),new Point(referencia2.getX() + referencia2.getWidth(), referencia2.getY() + referencia2.getHeight())));
                                            
                                            Core.addWeighted(referencia2.getImagem(),1.0,mescRef , 0.3, 0.0, dst2);
                                            dst2.copyTo(cenario.colRange(referencia2.getX(),referencia2.getX() + referencia2.getWidth()).rowRange(referencia2.getY(),referencia2.getY() + referencia2.getHeight()));

                                            referencia2.setX(referencia2.getX() + referencia2.getWidth());
                                        } else {
                                            dst = new Mat();
                                            Mat mescRef = cenario.submat(new Rect(new Point(referencia.getX(), referencia.getY()),new Point(referencia.getX() + referencia.getWidth(), referencia.getY() + referencia.getHeight())));
                                            
                                            Core.addWeighted(referencia.getImagem(),1.0,mescRef , 0.3, 0.0, dst);
                                            dst.copyTo(cenario.colRange(referencia.getX(),referencia.getX() + referencia.getWidth()).rowRange(referencia.getY(),referencia.getY() + referencia.getHeight()));

                                            referencia.setX(referencia.getX() + referencia.getWidth());

                                        }
                                    }
                                }

                                if (jogando){ // jogando é um bool que indica que o usuário está tocando os objetos e por isso devem aparecer os acertos no lugar das referências
                                    for (int i = 0;i<numAcertosNaRodada;i++){
                                        Imagem elemento = partida.getFilaElementosReferencia().get(i);
                                        Imagem imgRefTemp = new Imagem(elemento);
                                        referencia3.setWidth(partida.getNivel().getTIO());
                                        referencia3.setHeight(partida.getNivel().getTIO());
//
                                        referencia3.setY(10);
                                        Mat tempRef = imgRefTemp.getImg();
                                        Imgproc.resize(tempRef,tempRef, new Size(50.0, 50.0));
                                        referencia3.setImagem(tempRef);
                                        referencia3.setId(imgRefTemp.getId());
//                                            
                                        descRef = imgRefTemp.getDescricao();
//                                            //coloca as descricoes
                                        referencia3.setRefImgStr(imgRefTemp.getDescricao());
                                        referencia3.setSom(imgRefTemp.getSom());
                                        referencia3.setGrupo(imgRefTemp.getGrupo());

                                        dst = new Mat();
                                        Mat mescRef = cenario.submat(new Rect(new Point(referencia3.getX(), referencia3.getY()),new Point(referencia3.getX() + referencia3.getWidth(), referencia3.getY() + referencia3.getHeight())));
                                        Core.addWeighted(referencia3.getImagem(),1.0,mescRef , 0.3, 0.0, dst);
                                        dst.copyTo(cenario.colRange(referencia3.getX(),referencia3.getX() + referencia3.getWidth()).rowRange(referencia3.getY(),referencia3.getY() + referencia3.getHeight()));
                                        referencia3.setX(referencia3.getX() + referencia3.getWidth());
                                    }
//                                    if (!mostrarEstrelas){ //talvez tirar esse if
//                                        for (int i=numAcertosNaRodada; i<partida.getNivel().getQIO(); i++){
//                                            Imgproc.resize(sombraObjetivo, sombraObjetivo, new Size(50.0, 50.0));
//                                            dst = new Mat();
//                                            Mat roiSombraObjetivo = cenario.submat(new Rect(new Point(referencia.getX(), referencia.getY()),new Point(referencia.getX() + referencia.getWidth(), referencia.getY() + referencia.getHeight())));
//                                            Core.addWeighted(roiSombraObjetivo,0.0,sombraObjetivo,1.0,0.0,dst);
//                                            dst.copyTo(cenario.colRange(referencia.getX(),referencia.getX() + referencia.getWidth()).rowRange(referencia.getY(),referencia.getY() + referencia.getHeight()));
//                                            referencia.setX(referencia.getX() + referencia.getWidth());
//                                        } 
//                                    }
                                }

                                if (Move4Math.indiceJogoAtual == 0 || Move4Math.indiceJogoAtual == 2){
                                    referencia.setX(195);
                                    referencia3.setX(195);
                                    referencia2.setX(195 + referencia.getWidth());
                                }else{
                                    if (Move4Math.indiceJogoAtual == 1){
                                        referencia.setX(250);
                                    }else{
                                        referencia.setX(280);
                                    }
                                }
                                //cenario.notifyAll();
                                //cenario.wait(partida.getNivel().getTEO()*1000);
                                //int tempoExposicao = partida.getNivel().getTEI()*1000;
                            }
        
                            //Thread.sleep(partida.getNivel().getTEO()*1000);


                            //Só mostra os blobs se já se passou um tempo de referência
                  //          if(Calendar.getInstance().getTimeInMillis()>(gerarRodada.getTimeInMillis()+tempoExposicaoReferencia)){
                                //guarda o instante que os blobs sao mostrados na tela

                                if ( ( (((60*minutos) + segundos) - ((60*minutosAux) + segundosAux)) > partida.getNivel().getTEO() && primeiroToque == true ) || ( (((60*minutos) + segundos) - ((60*minutosAux) + segundosAux)) > partida.getNivel().getTEO()/2 && primeiroToque == false ) ){

                                //topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                                mostrarEstrelas = false;
                                mostrarReferencias = false;
                                jogando = true;
                                if(gerouBlobs==false){
                                    mostrarBlobs = Calendar.getInstance();
                                    gerouBlobs=true;
                                }       

                                //mostra a barrinha de tempo
                                //criando a barrinha de tempo que vai decrescendo
                                diferenca = ((mostrarBlobs.getTimeInMillis()+tempoExposicao) - Calendar.getInstance().getTimeInMillis());
                                diferenca = (diferenca/1000 < 0)? 0 : diferenca/1000;
                                float te = tempoExposicao/1000;

                                ocultaReferencia(partida.getNivel());

                                Imgproc.line(cenario, new Point(221, 68), new Point(225+(diferenca/te)*200, 68), new Scalar(0, 0, 255, 255),5);
                                
                                long tempoFaltante = (tempoExposicao - (Calendar.getInstance().getTimeInMillis() - mostrarBlobs.getTimeInMillis()));
                                
                                if (reproduzirAudio) {
                                    if (reproduzirAudioRelogio) {
                                        File yourFile = new File("Resources/sounds/clocktick.wav");
                                            AudioInputStream stream;
                                            AudioFormat format;
                                            DataLine.Info info;
                                            Clip clip;

                                            stream = AudioSystem.getAudioInputStream(yourFile);
                                            format = stream.getFormat();
                                            info = new DataLine.Info(Clip.class, format);
                                            clip = (Clip) AudioSystem.getLine(info);
                                            clip.open(stream);
                                        if (tempoFaltante < 2000) {
                                            reproduzirAudioRelogio = false;
                                            clip.start();
                                        } else {
                                            clip.stop();
                                        }
                                    }
                                }
                                
                                //fim da barrinha de tempo
                                // mostra rodada (blobs na tela)
                                switch (partida.getNivel().getLAD()) {
                                    case 1:
                                        gradeEsq.showImagens(cenario);
                                        break;
                                    case 2:
                                        gradeDir.showImagens(cenario);
                                        break;
                                    case 3:
                                        gradeEsq.showImagens(cenario);
                                        gradeDir.showImagens(cenario);
                                        break;
                                    default:
                                        break;
                                }
                                /*
                                * Copia o cenário anterior. 'cenarioAnterior' só é usado na função de colisão.
                                * Antes de copiar, espera um tempo para não copiar a mesma imagem, ja que a webcam gera em torno de 20~30 FPS
                                * Imagens muito parecidas farão com qua a função de colisao nao funcione direito
                                */
                                if(Calendar.getInstance().getTimeInMillis()>tempoCenario.getTimeInMillis()+100){ //a cada 100 milisegundos copia o cenario
                                    cenario.copyTo(cenarioAnterior);
                                    tempoCenario = Calendar.getInstance();
                                }

                                //espera 200 milisegundos desde o inicio da rodada para checar a colisao
                                //isso garante que o cenario tenha sido copiado para o cenarioAnterior
                                if(Calendar.getInstance().getTimeInMillis()>mostrarBlobs.getTimeInMillis()+200){
                                    // -+-+-+-+-+-+ verificar colisão (se houve colisão zera as grades)
                                    switch (partida.getNivel().getLAD()) {// 1=esquerda, 2=direita, 3=ambos
                                        case 1:
                                            houveColisao = checarColisao(cenario, cenarioAnterior, gradeEsq, partida);
                                            break;
                                        case 2:
                                            houveColisao = checarColisao(cenario, cenarioAnterior, gradeDir, partida);
                                            break;
                                        case 3:
                                            houveColisao = checarColisao(cenario, cenarioAnterior, gradeEsq, partida);
                                            if (houveColisao==0){
                                                houveColisao = checarColisao(cenario, cenarioAnterior, gradeDir, partida);
                                            }   break;
                                        default:
                                            break;
                                    }
                                    if(houveColisao==1){
                                        mostrarEstrelas = true;
                                        primeiroToque = false;
                                        iPontosAux = iPontosAtual;
                                        iPontosAtual = partida.getPontuacao();
                                        iPontosAnt = iPontosAux;
                                        iDiferenca = iPontosAtual - iPontosAnt;

                                        segundosAux = segundos;
                                        minutosAux = minutos;
                                        iPontosMotor = 0;
                                        iPontosCognitivo = 0;
                                        switch (tipoColisao) {
                                            case 1:
                                                //acertou
                                                piscarTopo = false;
                                                topoFeedback = Imgcodecs.imread("Resources/images/topoFeedbackAcerto.png",1);
                                                break;
                                            case 2:
                                                //errou
                                                piscarTopo = false;
                                                topoFeedback = Imgcodecs.imread("Resources/images/topoFeedbackErro.png",1);
                                                numErros++;
                                                break;
                                            default:
                                                piscarTopo = true;
                                                break;
                                        }
                                        atualizaVidas();
                                        
                                        mostrarReferencias = false;
                                        jogando = true;
                                        houveColisao=0;
                                        gradeEsq.setNumImagens(0);
                                        gradeDir.setNumImagens(0);
                                        gerouBlobs=false;
                                        gerouImagens=false;
                                        gerarRodada = Calendar.getInstance();
                                        //if(contNST==NST){

                                        if(numAcertosNaRodada==NST && (partida.getNivel().getNumeroLinha() - 1) != (int) jogadasDoNivel.get(3)){ // era contNST == NST, mas como queremos que avance de linha apenas quando o usuário completar os objetivos propostos, precisamos verificar se o número de acertos é igual ao de objetivos e se ele não está na última linha do nível.
                                            contNST = 0;
                                            segundosAux2 = segundos;
                                            minutosAux2 = minutos;
                                            irParaProximaLinha = false;

                                            verificaTransicaoDeLinha(partida);
                                        }else{
                                            irParaProximaLinha = true;
                                        }

                                    }else if(Calendar.getInstance().getTimeInMillis()>(mostrarBlobs.getTimeInMillis()+tempoExposicao)){ //se passou o tempo e não houve colisao
                                        mostrarReferencias = true;
                                        
                                        jogando = false;
                                        houveColisao=0;
                                        
                                        gradeEsq.setNumImagens(0);
                                        gradeDir.setNumImagens(0);
                                        gerouBlobs=false;
                                        gerouImagens=false;
                                        gerarRodada = Calendar.getInstance();

                                        Rodada rodada = new Rodada();
                                        //ID da sessão
                                        rodada.setIdSessao(partida.getPlayer().getSessoes().lastElement().getId());

                                        buscaDadosCSVDetalhado(rodada,partida);

                                        //Nivel
                                        rodada.setNivel(partida.getNivel().getNumero());
                                        //Linha
                                        rodada.setLinhaNivel(partida.getNivel().getNumeroLinha());
                                        //Imagem Objetivo
                                        rodada.setImgRef(referencia.getRefImgStr());
                                        //Imagem Tocada
                                        rodada.setImgTocada("Nao tocou");
                                        //Tempo de toque
                                        rodada.setTempoToque(tempoExposicao/1000);
                                        //Ação
                                        rodada.setAcao("Omitiu");
                                        //Pontos Motor
                                        rodada.setPontosMotor(0);
                                        //Pontos Cognitivo
                                        rodada.setPontosCognitivo(0);

                                        piscarTopo = true;
                                        segundosAux = segundos;
                                        minutosAux = minutos;

                                        partida.getPlayer().getSessoes().lastElement().getRodadas().add(rodada);

                                        escreveCSVDetalhado(rodada);

                                        somaTempoToque += tempoExposicao/1000;

                                        if(numAcertosNaRodada==NST){
                                            contNST = 0;
                                           verificaTransicaoDeLinha(partida);
                                        }
                                    }
                                }
                                }else if(jogando){
                                    mostrarReferencias = false;
                                }
                        }

                        Imgcodecs.imencode(".bmp", cenario, mem);
                        Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                        BufferedImage buff = (BufferedImage) im;

                        //desenha o cenario no jpanel
                        Graphics g=jPanel1.getGraphics();

                        if(buff!=null){
                            g.drawImage(buff, 0, 0, jPanel1.getWidth(), jPanel1.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);
                        }                    

                        //capturar teclas atalho
                        if((MainWindow.tecla.getKeyCode() == KeyEvent.VK_ESCAPE)){//Fecha o jogo
                            MainWindow.tecla = null;
                            break;
                        }
                        if((MainWindow.tecla.getKeyCode() == KeyEvent.VK_G)){//Liga/Desliga a grade
                            if(isGradesVisiveis())
                                setGradesVisiveis(false);
                            else
                                setGradesVisiveis(true);
                            MainWindow.tecla = null;
                        }

                        if(MainWindow.tecla.getKeyCode() == KeyEvent.VK_LEFT){//Retorna o nivel

                            posicaoJogadasDoNivel=1;
                            irParaProximaLinha = false;
                            geraProximaLinha = false;
                            numAcertosNaRodada=0;
                            mostrarReferencias = true;
                            jogando = false;
                            numErrosLimite = 0;
                            feedback2 = true;
                            primeiroToque = true;

                            numRodadasGeradas = 0;
                            tamanhoEFeedback = 350;

                            retrocedeNivel(partida);

                            somaTempoToque = 0;
                            numAcertos = 0;
                            numErros = 0;

                            segundosAux = segundos;
                            minutosAux = minutos;

                            tipoFeedback = 3;
                            MainWindow.tecla = null;
                        }
                        if (MainWindow.tecla.getKeyCode() == KeyEvent.VK_RIGHT){//Avanca o nivel
                            posicaoJogadasDoNivel=1;
                            irParaProximaLinha = false;
                            geraProximaLinha = false;
                            numAcertosNaRodada=0;

                            jogando = false;
                            numErrosLimite = 0;
                            feedback2 = true;
                            primeiroToque = true;

                            numRodadasGeradas = 0;
                            tamanhoEFeedback = 350;

                            //partida.getNivel().setNumero(partida.getNivel().getNumero() + 1);
                            avancaNivel(partida);
                            mostrarReferencias = false;

                            segundosAux2 = segundos;
                            minutosAux2 = minutos;

                            //tempoExposicao = partida.getNivel().getTEI();

                            somaTempoToque = 0;
                            numAcertos = 0;
                            numErros = 0;


                            segundosAux = segundos;
                            minutosAux = minutos;

                            //gerarRodada = Calendar.getInstance();
                            //diferenca = 0;
                            tipoFeedback = 1;
                            MainWindow.tecla = null;
                        }

                        if (MainWindow.tecla.getKeyCode() == KeyEvent.VK_S){//Liga/Desliga o Audio

                            if (reproduzirAudio){
                                reproduzirAudio = false;
                            }else{
                                reproduzirAudio = true;
                            }

                            MainWindow.tecla = null;
                        }

                        if (MainWindow.tecla.getKeyCode() == KeyEvent.VK_SPACE){//Pausa o jogo
                            pausado = true;

                            pausaJogo();
                            //diferenca = ((mostrarBlobs.getTimeInMillis()+tempoExposicao) - Calendar.getInstance().getTimeInMillis());
                            MainWindow.tecla = null;
                        }
                        
                        if(MainWindow.tecla.getKeyCode() == KeyEvent.VK_UP){//Aumenta o tempo.
                            if(tempoExposicao < 15000){
                                tempoExposicao += 1000;
                            }
                            int a = tempoExposicao / 1000;
                            partida.getNivel().setTEI(a);
                            
                            MainWindow.tecla = null;
                        }
                        
                        if(MainWindow.tecla.getKeyCode() == KeyEvent.VK_DOWN){//Diminui o tempo.
                            if(tempoExposicao > 3000){
                                tempoExposicao -= 1000;
                            }
                            int a = tempoExposicao / 1000;
                            partida.getNivel().setTEI(a);
                            
                            MainWindow.tecla = null;
                        }

                    } //fim try
                    catch(Exception ex){
                        //System.out.println("erro gravando frame");
                    }
            }//fim while
            try {
                escreveCSV(sessao, partida);
            } catch (IOException ex) {
                Logger.getLogger(Game_Sequenciacao.class.getName()).log(Level.SEVERE, null, ex);
            }



            fimDeJogo = true;

            webSource.release();
            gamewindow.dispose();

            }//fim run

        //------------------------------------------------------------------------------------------------------------------------------------------

        void verificaTransicaoDeLinha (Partida partida){
            System.out.println("\n\nchamou verifica transição de linha\n\n");
            // a transicao de linha sempre é feita, pois a avaliação se dá ao fim de cada nível
            partida.setNivel(publico.getNiveis().elementAt((int) jogadasDoNivel.get(posicaoJogadasDoNivel)));
            posicaoJogadasDoNivel++;
            numAcertosNaRodada = 0;
            mostrarReferencias = true;
            jogando = false;
            geraProximaLinha = false;
            primeiroToque = true;
            tamanhoEFeedback = 100;
            numErros = 0;
        }

        void escreveCSV(Sessao sessao, Partida partida) throws IOException{
            String arquivo = "Users/"+String.valueOf(partida.getPlayer().getId())+"_"+String.valueOf(partida.getPlayer().getNome())+".csv";

            FileWriter mFileWriter = new FileWriter(arquivo, true);
            CSVWriter writer = new CSVWriter(mFileWriter,';',CSVWriter.NO_QUOTE_CHARACTER);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            String a = s.format(c.getTime());

            String[] record = {String.valueOf(sessao.getId()),String.valueOf(sessao.getData()),String.valueOf(sessao.getHora()),String.valueOf(sessao.getJogo()),String.valueOf(sessao.getFase()),String.valueOf(sessao.getNivel()),String.valueOf(partida.getPontuacao()),String.valueOf(sessao.getTempoTotal())};
            writer.writeNext(record);
            writer.close();
        }

        void escreveCSVDetalhado(Rodada rodada) throws IOException{
            String arquivoDetalhado = "Users/"+String.valueOf(player.getId())+"_"+String.valueOf(player.getNome())+"_detalhado.csv";

            FileWriter mFileWriter = new FileWriter(arquivoDetalhado, true);
            CSVWriter writerDet = new CSVWriter(mFileWriter,';',CSVWriter.NO_QUOTE_CHARACTER);

            String[] record = {String.valueOf(rodada.getIdSessao()),String.valueOf(rodada.getData()),String.valueOf(rodada.getHora()),String.valueOf(rodada.getJogo()),String.valueOf(rodada.getFase()),String.valueOf(rodada.getNivel()),String.valueOf(rodada.getLinhaNivel()),rodada.getImgRef(),rodada.getImgTocada(),String.valueOf(rodada.getTempoToque()),rodada.getAcao(),String.valueOf(rodada.getPontosMotor()),String.valueOf(rodada.getPontosCognitivo())};

            writerDet.writeNext(record);
            writerDet.close();
        }

        void escreveCorpoHTML (Rodada rodada, String horaErro, Partida partida, String dataErro) throws IOException{
            String arquivoErrosHtml = "Users/"+String.valueOf(player.getId())+"_"+String.valueOf(player.getNome())+"_erros.HTML";

            String ref[] = partida.imagensDaCena(move4math.Move4Math.indiceFaseAtual, partida.getNivel());
            
            FileWriter fw = new FileWriter(arquivoErrosHtml, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("<div><img src='PrintsDosErros/" + player.getId() + "_" + player.getNome() + "_" + horaErro + ".jpeg" + "' height='384px' width='680px'>"
                    + "<h3>Jogo: " + jogo.getNome() + "&emsp; &emsp; &emsp;"
                    + "Fase: " + partida.getFase().getNumeroFase() + "&emsp; &emsp; &emsp;"
                    + "Nível: " + partida.getNivel().getNumero() + "&emsp; &emsp; &emsp;"
                    + "Data: " + dataErro + "<br>"
                    + "Referência: " + descRef + "</h3></div>"
            );
            bw.close();
            fechaHTML ();

        }

        void fechaHTML () throws IOException{
            String arquivoErrosHtml = "Users/"+String.valueOf(player.getId())+"_"+String.valueOf(player.getNome())+"_erros.HTML";

            FileWriter fw = new FileWriter(arquivoErrosHtml, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.append("</body></html>");
            bw.close();
        }

        void verificaTransicaoDeNivel(Partida partida) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
            posicaoJogadasDoNivel=1;
            irParaProximaLinha = false;
            geraProximaLinha = false;
            numAcertosNaRodada=0;
            mostrarReferencias = true;
            jogando = false;
            numErrosLimite = 0;
            feedback2 = true;
            primeiroToque = true;
            mostrarEstrelas = false;
            tamanhoEFeedback = 100;

            double desempenhoCognitivo, desempenhoMotor, mediaTempoToque;
            tempoExposicao = partida.getNivel().getTEI();
            desempenhoCognitivo = (double)(numAcertos-numErros)/numRodadasGeradas;
            mediaTempoToque = (double)somaTempoToque / numRodadasGeradas;
            desempenhoMotor = (double)(mediaTempoToque/(tempoExposicao));
            System.out.println("----------");
            System.out.println("numAcertos: " + numAcertos +" numErros: " + numErros);
            System.out.println("desempenhoCognitivo: " + desempenhoCognitivo);
            System.out.println("somaTempoToque: " + somaTempoToque + " numRodadasGeradas: " + numRodadasGeradas+ " mediaTempoToque: "+mediaTempoToque);
            System.out.println("desempenhoMotor: " + desempenhoMotor + " tempoExposicao: " + tempoExposicao);
            System.out.println("----------");

            if((desempenhoMotor<0.4)&&(desempenhoCognitivo>0.6)){
                System.out.println("avancou nivel");
                avancaNivel(partida);
                tipoFeedback = 1;

            }else if((desempenhoMotor>0.6) && (desempenhoCognitivo<0.3)){ // tava OU 
                System.out.println("retrocedeu nivel");
                iNiveisRetrocedidos++;
                retrocedeNivel(partida);
                tipoFeedback = 3;
            }else{
                System.out.println("permaneceu no nivel");
                iNiveisRepetidos++;
                permaneceNivel(partida);
                tipoFeedback = 2;
            }

            atualizaVidas();
            NST = partida.getNivel().getQIO();
//            if(true){
//                avançaNivel(partida);
//            }

            tempoExposicao = partida.getNivel().getTEI();
            numRodadasGeradas = 0;
            somaTempoToque = 0;
            numAcertos = 0;
            numErros = 0;

            segundosAux = segundos;
            minutosAux = minutos;
        }

        void avancaNivel(Partida partida) throws UnsupportedAudioFileException, LineUnavailableException, IOException{
            int numeroNivelAntigo = partida.getNivel().getNumero();
            int numeroNivelNovo;

            //if(indexNivel<publico.getNiveis().size()-1){
            if(indexNivel<publico.getNiveis().size()/4){   
                numeroNivelNovo = numeroNivelAntigo + 1;

                jogadasDoNivel.clear();
                jogadasDoNivel = partida.geraJogadasDoNivel(numeroNivelNovo);
                //System.out.println(" ");
                System.out.println("novo jogadasDoNivel: " + jogadasDoNivel.toString());
                int o = Integer.parseInt(jogadasDoNivel.get(0).toString()); // dava problema, pois eu setava o próximo nivel a partir do cálculo da primeira linha; mas este deve ser setado com a primeira posição do jogadasDoNivel

                partida.setNivel(publico.getNiveis().elementAt(o)); //Matheus
                numRodadasGeradas=1;

            }else{
                //Transição do último nível da fase 1 para o primeiro da fase 2
                numeroNivelNovo = 1;
                partida.setNivel(publico.getNiveis().elementAt((numeroNivelNovo)));
                numRodadasGeradas=1;
                jogadasDoNivel.clear();
                avancaFase(partida);
                tipoFeedback = 4;
                jogadasDoNivel = partida.geraJogadasDoNivel(numeroNivelNovo);
            }

            gradeEsq.setTamanhoGrade(partida.getNivel().getTAI());
            gradeDir.setTamanhoGrade(partida.getNivel().getTAI());
        }
    
        void retrocedeNivel(Partida partida) throws UnsupportedAudioFileException, LineUnavailableException, IOException{
            int numeroNivelAntigo = 0;
            int numeroNivelNovo;
            //int iVidas = player.getVidas();
            
            if(indexNivel>0){ // talvez tenha que testar o partida.getNivel().getNumero();
                numeroNivelAntigo = partida.getNivel().getNumero();
                numeroNivelNovo = numeroNivelAntigo - 1;
                partida.setNivel(publico.getNiveis().elementAt((numeroNivelNovo-1)*4)); //Matheus
                
                jogadasDoNivel.clear();
                jogadasDoNivel = partida.geraJogadasDoNivel(numeroNivelNovo);
                System.out.println("novo jogadasDoNivel: " + jogadasDoNivel.toString());
                int o = Integer.parseInt(jogadasDoNivel.get(0).toString()); // dava problema, pois eu setava o próximo nivel a partir do cálculo da primeira linha; mas este deve ser setado com a primeira posição do jogadasDoNivel
                
                partida.setNivel(publico.getNiveis().elementAt(o)); //Matheus
                numRodadasGeradas=1;
                
                gradeEsq.setTamanhoGrade(partida.getNivel().getTAI());
                gradeDir.setTamanhoGrade(partida.getNivel().getTAI());
            }else if (indexNivel==0){ //Precisa voltar para a fase 1 se retroceder do nível 1 da fase 2
                permaneceNivel(partida);
            }else{
                retrocedeFase(partida);
                tipoFeedback = 5;
                gradeEsq.setTamanhoGrade(partida.getNivel().getTAI());
                gradeDir.setTamanhoGrade(partida.getNivel().getTAI());
            }
        }
        
        void permaneceNivel (Partida partida){
            jogadasDoNivel.clear();
            jogadasDoNivel = partida.geraJogadasDoNivel(partida.getNivel().getNumero());

            System.out.println("novo jogadasDoNivel: " + jogadasDoNivel.toString());
            int o = Integer.parseInt(jogadasDoNivel.get(0).toString()); // dava problema, pois eu setava o próximo nivel a partir do cálculo da primeira linha; mas este deve ser setado com a primeira posição do jogadasDoNivel

            partida.setNivel(publico.getNiveis().elementAt(o)); //Matheus

            numRodadasGeradas=1;
            numAcertosNaRodada = 0;
            mostrarReferencias = true;
            
            gradeEsq.setTamanhoGrade(partida.getNivel().getTAI());
            gradeDir.setTamanhoGrade(partida.getNivel().getTAI());
        }
        
        void avancaFase(Partida partida){
            int numeroFaseAntiga = partida.getFase().getNumeroFase();
            int numeroFaseNova = numeroFaseAntiga + 1;
            if(indexFase<publico.getFases().size()){
                partida.setFase(publico.getFases().elementAt(numeroFaseNova));
            }
        }
        
        void retrocedeFase(Partida partida){
            int numeroFaseAntigo = 0;
            int numeroFaseNovo;
            if(indexFase>1){
                numeroFaseAntigo = partida.getFase().getNumeroFase();
                numeroFaseNovo = numeroFaseAntigo - 1;
                partida.setFase(publico.getFases().elementAt((numeroFaseNovo)));
            }            
        }

        void atualizaVidas() throws UnsupportedAudioFileException, LineUnavailableException, IOException{
            int iVidas = player.getVidas();
            switch (Move4Math.indicePublicoAtual){ 
                case 0: // Criança
                    if (iNiveisRetrocedidos == 3 || iNiveisRepetidos == 5 || numErros >= 3){
                        iVidas--;
                        player.setVidas(iVidas);
                        iNiveisRetrocedidos = 0;
                        iNiveisRepetidos = 0;
                        numErros = 0;
                        tipoFeedback = 6;
                    }
                    break;
                case 1: // Criança com Deficiencia
                    if(iNiveisRetrocedidos == 1 || iNiveisRepetidos == 3 || numErros >= 5){
                        iVidas--;
                        player.setVidas(iVidas);
                        iNiveisRetrocedidos = 0;
                        iNiveisRepetidos = 0;
                        numErros = 0;
                        tipoFeedback = 6;
                    }
                    break;
                default:
                    System.out.println("Indice Inválido !!!");
            }
        }
        
        int checarColisao(Mat cenario,Mat cenarioAnterior, Grade grade,Partida partida) throws UnsupportedAudioFileException, IOException, LineUnavailableException, AWTException{
            int colisao = 0;
            int threshold = 10;

            for(int i=0;i<grade.getRegioes().size();i++){
                if(grade.getRegioes().elementAt(i).isOcupado()){
                    int x1 = (int)grade.getRegioes().elementAt(i).getpInicial().x;
                    int y1 = (int)grade.getRegioes().elementAt(i).getpInicial().y;
                    int x2 = (int)grade.getRegioes().elementAt(i).getpFinal().x;
                    int y2 = (int)grade.getRegioes().elementAt(i).getpFinal().y;

                    Mat cena1 = new Mat();
                    Mat cena2 = new Mat();
                    cenario.submat(new Rect(new Point(x1, y1),new Point(x2, y2))).copyTo(cena1);
                    cenarioAnterior.submat(new Rect(new Point(x1, y1),new Point(x2, y2))).copyTo(cena2);

                    Mat dst = new Mat();

                    Imgproc.GaussianBlur(cena1, cena1, new Size(3, 3), 0);
                    Imgproc.GaussianBlur(cena2, cena2, new Size(3, 3), 0);

                    Core.subtract(cena1, cena2, dst);

                    Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);

                    Imgproc.threshold(dst, dst, threshold, 255, Imgproc.THRESH_BINARY);

                    Mat v = new Mat();

                    List<MatOfPoint> contours = new ArrayList();

                    Imgproc.findContours(dst, contours, v, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

                    double maxArea = 40;//valor fixo para sensibilidade da colisão

                    for(int idx = 0; idx < contours.size(); idx++){
                        Mat contour = contours.get(idx);
                        double contourarea = Imgproc.contourArea(contour);
                        if(contourarea > maxArea){
                            colisao = 1;
                        }
                    }
                }

                if(colisao==1){
                    Rodada rodada = new Rodada();
                    int tempoToque = (int) (Calendar.getInstance().getTimeInMillis()-mostrarBlobs.getTimeInMillis());
                    tempoToque = tempoToque/1000; //tempo que o player levou para tocar na figura em segundos

                    int pontuacaoPorTempo = partida.getNivel().getTEI()/5;
                    rodada.setIdSessao(partida.getPlayer().getSessoes().lastElement().getId());
                    rodada.setNivel(partida.getNivel().getNumero());
                    rodada.setLinhaNivel(partida.getNivel().getNumeroLinha());
                    rodada.setImgRef(referencia.getRefImgStr());
                    rodada.setImgTocada(grade.getRegioes().elementAt(i).getImg().getDescricao());
                    rodada.setTempoToque(tempoToque);

                    somaTempoToque += tempoToque;

                    System.out.println("\npartida.getFilaElementos().get(numAcertosNaRodada).getId(): " + partida.getFilaElementos().get(numAcertosNaRodada).getId());
                    System.out.println("\n tocada: " + grade.getRegioes().elementAt(i).getImg().getId());
                    //o if abaixo decide se a imagem tocada está certa ou errada
                    if(grade.getRegioes().elementAt(i).getImg().getId()==partida.getFilaElementos().get(numAcertosNaRodada).getId()){
//                    if(grade.getRegioes().elementAt(i).getImg().getId()==referencia.getId()){
                        //Acho que é assim que separa os pontos...
                        tipoColisao = 1;
                        rodada.setAcao("Acertou");
                        //escreveHTML(rodada, data);
                        numAcertos++;
                        numAcertosNaRodada++;
                        jogando = true;

                        if(tempoToque<pontuacaoPorTempo){
                            partida.setPontuacao(partida.getPontuacao()+10);
                            iPontosCognitivo = iPontosCognitivo + 5;
                            iPontosMotor = iPontosMotor + 5;                            
                        }else{
                            if(tempoToque<(pontuacaoPorTempo*2)){
                                partida.setPontuacao(partida.getPontuacao()+9);
                                iPontosCognitivo = iPontosCognitivo + 5;
                                iPontosMotor = iPontosMotor + 4;
                            }else{
                                if(tempoToque<(pontuacaoPorTempo*3)){
                                    partida.setPontuacao(partida.getPontuacao()+8);
                                    iPontosCognitivo = iPontosCognitivo + 5;
                                    iPontosMotor = iPontosMotor + 3;
                                }else{
                                    if(tempoToque<(pontuacaoPorTempo*4)){
                                        partida.setPontuacao(partida.getPontuacao()+7);
                                        iPontosCognitivo = iPontosCognitivo + 5;
                                        iPontosMotor = iPontosMotor + 2;
                                    }else{
                                        partida.setPontuacao(partida.getPontuacao()+6);
                                        iPontosCognitivo = iPontosCognitivo + 5;
                                        iPontosMotor = iPontosMotor + 1;
                                    }
                                }
                            }
                        }

                        //som de acerto
                        if (reproduzirAudio){
                            File yourFile = new File("Resources/sounds/acertou3.wav");
                            AudioInputStream stream;
                            AudioFormat format;
                            DataLine.Info info;
                            Clip clip;

                            stream = AudioSystem.getAudioInputStream(yourFile);
                            format = stream.getFormat();
                            info = new DataLine.Info(Clip.class, format);
                            clip = (Clip) AudioSystem.getLine(info);
                            clip.open(stream);
                            clip.start();
                        }
                    }else{
                        tipoColisao = 2;
                        rodada.setAcao("Errou");

                        geraRelatorioErros(cenario, rodada, partida, numErros);
                        if(tempoToque<pontuacaoPorTempo){
                            partida.setPontuacao(partida.getPontuacao()+5);
                            iPontosMotor = iPontosMotor + 5;
                        }else{
                            if(tempoToque<(pontuacaoPorTempo*2)){
                                partida.setPontuacao(partida.getPontuacao()+4);
                                iPontosMotor = iPontosMotor + 4;
                            }else{
                                if(tempoToque<(pontuacaoPorTempo*3)){
                                    partida.setPontuacao(partida.getPontuacao()+3);
                                    iPontosMotor = iPontosMotor + 3;
                                }else{
                                    if(tempoToque<(pontuacaoPorTempo*4)){
                                        partida.setPontuacao(partida.getPontuacao()+2);
                                        iPontosMotor = iPontosMotor + 2;
                                    }else{
                                        partida.setPontuacao(partida.getPontuacao()+1);
                                        iPontosMotor = iPontosMotor + 1;
                                    }
                                }
                            }
                        }

                        //som de erro
                        if (reproduzirAudio){
                            File yourFile = new File("Resources/sounds/errou.wav");
                            AudioInputStream stream;
                            AudioFormat format;
                            DataLine.Info info;
                            Clip clip;

                            stream = AudioSystem.getAudioInputStream(yourFile);
                            format = stream.getFormat();
                            info = new DataLine.Info(Clip.class, format);
                            clip = (Clip) AudioSystem.getLine(info);
                            clip.open(stream);
                            clip.start();
                        }

                        //setar a vida menos 1;
                        //partida.getPlayer().setVidas(partida.getPlayer().getVidas() -1 );
                        //Se zerou as vidas, Game Over
                        if(partida.getPlayer().getVidas()<=0){
                            MainWindow.tecla = null;
                            break;  
                        }
                    }
                    
                    rodada.setPontosCognitivo(iPontosCognitivo);
                    rodada.setPontosMotor(iPontosMotor);

                    partida.getPlayer().getSessoes().lastElement().getRodadas().add(rodada);

                    buscaDadosCSVDetalhado(rodada,partida);

                    escreveCSVDetalhado(rodada);
                        break; //nao precisa verificar o resto das imagens já que houve colisao
                }
            }
            return colisao;
        }

        void gerarImagens2(Grade grade, Partida partida, int numSimbolosParaGerar, boolean emitirSom,boolean isReferencia) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
            boolean controle = false;
            
            if(isReferencia){                                      //se a referencia ainda sera gerada
                partida.atualizaFilaElementos(partida.getFilaElementosReferencia().firstElement().getGrupo());
            }else{                                                   //a referencia já foi gerada e removida do vetor
                partida.atualizaFilaElementos(referencia.getGrupo());
            }
            int GRADE_MAX = 6;
            switch (grade.getTamanhoGrade()) {
                case 2:
                    GRADE_MAX = 6; //2 = grande
                    break;
                case 1:
                    GRADE_MAX = 16; //1 = medio
                    break;
                case 0:
                    GRADE_MAX = 78; //0 = pequeno
                    break;
                default:
                    break;
            }

            //ajusta tamanho da imagem de acordo com a grade atual
            int cellWidth = (int)(grade.getScreenWidth()*0.325)/16;
            int cellHeight = (int)(grade.getScreenHeight()*0.65)/24;
            double width=0.0, height=0.0;
            
            switch (grade.getTamanhoGrade()) {
                case 2:
                    width = cellWidth*6;
                    height = cellHeight*6;
                    break;
                case 1:
                    width = cellWidth*4;
                    height = cellHeight*4;
                    break;
                case 0:
                    width = cellWidth*2;
                    height = cellHeight*2;
                    break;
                default:
                    break;
            }

            if(isReferencia){ //seta a referencia que vai aparecer no topo da tela
//                System.out.println("\nFILA ELEMENTOS REFERENCIA EM GAME:\n");
//                for(int i=0; i<partida.getFilaElementosReferencia().size(); i++) {
//                    System.out.println(partida.getFilaElementosReferencia().get(i).getId() + " ");
//                }
                Imagem elemento = partida.getFilaElementosReferencia().firstElement();
                Imagem imgRefTemp = new Imagem(elemento);
                referencia.setWidth(partida.getNivel().getTIO());
                referencia.setHeight(partida.getNivel().getTIO());

                switch(Move4Math.indiceJogoAtual){
                    case 2:
//                        referencia.setX(250);
                        referencia.setX(195);
                        referencia3.setX(195);
                        break;
                    default :
                        referencia.setX(280);
                        break;
                }
                
                referencia.setY(10);
                Mat tempRef = new Mat();
                tempRef = imgRefTemp.getImg();
                Imgproc.resize(tempRef,tempRef,new Size(partida.getNivel().getTIO(), partida.getNivel().getTIO()));
                referencia.setImagem(tempRef);
                referencia.setId(imgRefTemp.getId());

                if (idTemp != imgRefTemp.getId()){
                    if((partida.getNivel().getNumeroLinha() - 1) != (int) jogadasDoNivel.get(0)){
                        System.out.println("Alterou o topoFeedback no gerarImagens2");
                        piscarTopo = true;
                        topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1); 
                    }
                    idTemp = imgRefTemp.getId();
                }
                descRef = imgRefTemp.getDescricao();
                //coloca as descricoes
                referencia.setRefImgStr(imgRefTemp.getDescricao());
                referencia.setSom(imgRefTemp.getSom());
                referencia.setGrupo(imgRefTemp.getGrupo());
            }
            // Primeiro deixamos todas as regiões como "livres"
            for(int i=0;i<grade.getRegioes().size();i++)
                grade.getRegioes().elementAt(i).setOcupado(false);
            // Criamos um vetor das posições ocupadas zerado
            ArrayList<Integer> posicoesOcupadas = new ArrayList<Integer>();
            while(posicoesOcupadas.size()<grade.getRegioes().size())
                posicoesOcupadas.add(0);
            
            MTRandom number = new MTRandom();
            for(int i=0; i<numSimbolosParaGerar;i++){
                if(isReferencia && controle && false){ //seta a imagem equivalente à referencia em uma das posições da grade
                    //nunca ta entrando porque fiz controle = false (ou seja, agora pode deixar filaElementosReferencia com apenas um elemento)
                    Imagem imgRefTemp = null;
                    for(int j=0;j<partida.getFilaElementosReferencia().size();j++){
                        if(partida.getFilaElementosReferencia().elementAt(j).getGrupo()==referencia.getGrupo()){
                            imgRefTemp = new Imagem(partida.getFilaElementosReferencia().get(j));
                            break;
                        }
                    }
                    //sorteia uma posição na grade
                    //ps: refazer essa rotina para sortear com probabilidades
                    int posicao = number.nextInt(grade.getRegioes().size());
                    while(posicoesOcupadas.get(posicao)==1)
                        posicao = number.nextInt(grade.getRegioes().size());
                    
                    posicoesOcupadas.set(posicao,1);

                    Mat tempRef = new Mat();
                    tempRef = imgRefTemp.getImg();
                    Imgproc.resize(tempRef,tempRef,new Size(width, height));
                    imgRefTemp.setImg(tempRef);

                    grade.getRegioes().elementAt(posicao).setImg(imgRefTemp);

                    //seta regiao como ocupada
                    grade.getRegioes().elementAt(posicao).setOcupado(true);

                    int numImagens = grade.getNumImagens();
                
                    grade.setNumImagens(numImagens+1);       

                    controle = false;
                    
                }else {
                    // aqui
//                    System.out.println("\npartida.getFilaElementos(): ");
//                    for (int j=0; j<partida.getFilaElementos().size(); j++) {
//                        System.out.println("id: " + partida.getFilaElementos().get(j).getId()
//                                + " img str: " + partida.getFilaElementos().get(j).getImgStr()
//                                + " desc: " + partida.getFilaElementos().get(j).getDescricao()
//                                + " desc: " + partida.getFilaElementos().get(j).getImg());
//                    }
                    
                    if (elementoDaFila == partida.getFilaElementos().size()) {
                        elementoDaFila = 0;
                    }
                    
                    Imagem elemento = new Imagem();
                    
//                    if (elementoDaFila != 1) {
//                        elemento = partida.getFilaElementos().elementAt(elementoDaFila);
//                    } else {
//                        elemento = partida.getFilaElementos().lastElement();
//                    }
                    
                    elemento = partida.getFilaElementos().elementAt(elementoDaFila);
                    
                    Imagem imgTemp = new Imagem(elemento);
                 // Imagem imgTemp = new Imagem(partida.getFilaElementos().elementAt(1)); ELEMENT AT 1 NÃO FUNCIONA
                    


                    elementoDaFila++;

                    int posicao = number.nextInt(grade.getRegioes().size());
                    while(posicoesOcupadas.get(posicao)==1)
                        posicao = number.nextInt(grade.getRegioes().size());

                    posicoesOcupadas.set(posicao,1);

                    Mat tempRef = new Mat();
                    tempRef = imgTemp.getImg();
                    Imgproc.resize(tempRef,tempRef,new Size(width, height));
                    imgTemp.setImg(tempRef);
                    grade.getRegioes().elementAt(posicao).setImg(imgTemp);

                    grade.getRegioes().elementAt(posicao).setOcupado(true);
                    int numImagens = grade.getNumImagens();
                    grade.setNumImagens(numImagens+1);
                }
                    
            }
            
            //emite o som de referencia
            //if(isReferencia){
            if(emitirSom){
                if((partida.getFase().getEST()==2)||(partida.getFase().getEST()==0)){
                    //System.out.println(referencia.getSom().getSom());
                    File yourFile = new File(referencia.getSom().getSom());
                    AudioInputStream stream;
                    AudioFormat format;
                    DataLine.Info info;
                    Clip clip;
                    stream = AudioSystem.getAudioInputStream(yourFile);
                    format = stream.getFormat();
                    info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(stream);
                    clip.start();
                }
            }
        }

        void ocultaReferencia(Nivel nivel){
            if (move4math.Move4Math.indiceJogoAtual == 0 || move4math.Move4Math.indiceJogoAtual == 2){
                mostrarReferencias = false;
            } else {
                if (move4math.Move4Math.indiceJogoAtual == 1){
                    //Jogo de Ordenação ou Contagem
                    mostrarReferencias = true;
                    //Criar uma imagem para ocupar o espaço da segunda imagem da sequencia
                    //de imagens do objetivo da linha
                    //As coordenadas estão corretas

                    Mat silhueta = Imgcodecs.imread("Resources/images/corpo.png",1);            
                    Imgproc.resize(silhueta, silhueta, new Size(50.0, 50.0));
                    dst = new Mat();
                    Mat roiSilhueta = cenario.submat(new Rect(new Point(250, 15),new Point(300, 65)));
                    Core.addWeighted(roiSilhueta,1.0,silhueta,0.8,0.0,dst);
                    dst.copyTo(cenario.colRange(250,300).rowRange(15,65));
                } else {
                    //Jogo de Anterior/Proximo
                    if (nivel.getOTI()==1){
                        Mat silhueta = Imgcodecs.imread("Resources/images/corpo.png",1);            
                        Imgproc.resize(silhueta, silhueta, new Size(50.0, 50.0));
                        dst = new Mat();
                        Mat roiSilhueta = cenario.submat(new Rect(new Point(250, 15),new Point(300, 65)));
                        Core.addWeighted(roiSilhueta,1.0,silhueta,0.8,0.0,dst);
                        dst.copyTo(cenario.colRange(250,300).rowRange(15,65));
                    }else{
                        Mat silhueta = Imgcodecs.imread("Resources/images/corpo.png",1);            
                        Imgproc.resize(silhueta, silhueta, new Size(50.0, 50.0));
                        dst = new Mat();
                        Mat roiSilhueta = cenario.submat(new Rect(new Point(250, 15),new Point(300, 65)));
                        Core.addWeighted(roiSilhueta,1.0,silhueta,0.8,0.0,dst);
                        dst.copyTo(cenario.colRange(250,300).rowRange(15,65));
                    }
                }
            }
        }

        public void aguarda (int iOpcao) throws UnsupportedAudioFileException, IOException, LineUnavailableException{        
            /*Talvez seja interessante mandar o tipo de som a ser tocado ao chamar a funçao....
             0 - Avança linha;
             1 - Avança nivel;
             2 - Permanece nível;
             3 - Retrocede nivel;
             4 - Avança fase;
             5 - Retrocede fase;
             6 - Perde vida;
            */        

            int somaEstrelasNivel = 0, mediaEstrelasNivel = 0;
            if(iOpcao == 1 || iOpcao == 2 || iOpcao == 3){
                for (int i=0; i<numEstrelasNivel.size();i++){
                    somaEstrelasNivel += (int)numEstrelasNivel.get(i);
                }
                mediaEstrelasNivel = somaEstrelasNivel / numEstrelasNivel.size();
            }        

            //System.err.println("Opção: " + iOpcao);

            switch (iOpcao){
                case 0:
                    //Imagem para avanço de linha
                    if (tamanhoEFeedback < 350.0){
                        tamanhoEFeedback += 25;
                    }

                    Mat avancaLinha = Imgcodecs.imread("Resources/images/EAL.png",1);
                    Imgproc.resize(avancaLinha, avancaLinha, new Size(tamanhoEFeedback, tamanhoEFeedback));
                    dst = new Mat();
                    Mat roiImgAvancaLinha = cenario.submat(new Rect(new Point(150, 100),new Point(150+tamanhoEFeedback, 100+tamanhoEFeedback)));
                    Core.addWeighted(roiImgAvancaLinha,0.0,avancaLinha,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150, (int) (150 + tamanhoEFeedback)).rowRange(100, (int) (100 + tamanhoEFeedback)));

                    break;
                case 1:
                    if (tamanhoEFeedback < 350.0){
                        tamanhoEFeedback += 25;
                    }
                    //Imagem para avanço de nível
                    Mat avancaNivel = Imgcodecs.imread("Resources/images/EAN.png",1);
                    if (mediaEstrelasNivel == 1){
                        avancaNivel = Imgcodecs.imread("Resources/images/EAN-1.png",1);
                    }else if(mediaEstrelasNivel == 2){
                        avancaNivel = Imgcodecs.imread("Resources/images/EAN-2.png",1);
                    }else if (mediaEstrelasNivel == 3){
                        avancaNivel = Imgcodecs.imread("Resources/images/EAN-3.png",1);
                    }else if (mediaEstrelasNivel == 4){
                        avancaNivel = Imgcodecs.imread("Resources/images/EAN-4.png",1);
                    }else{
                        avancaNivel = Imgcodecs.imread("Resources/images/EAN-5.png",1);
                    }

                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    mostrarReferencias = false;

                    Imgproc.resize(avancaNivel, avancaNivel, new Size(tamanhoEFeedback, tamanhoEFeedback));
                    dst = new Mat();
                    Mat roiImgAvancaNivel = cenario.submat(new Rect(new Point(150, 100),new Point(150+tamanhoEFeedback, 100+tamanhoEFeedback)));
                    Core.addWeighted(roiImgAvancaNivel,0.0,avancaNivel,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150, (int) (150+tamanhoEFeedback)).rowRange(100, (int) (100+tamanhoEFeedback)));

                    break;
                case 2:
                    //Imagem para permanecer no mesmo nível
                    Mat permaneceNivel = Imgcodecs.imread("Resources/images/EPN.png",1);
                    if (mediaEstrelasNivel == 1){
                        permaneceNivel = Imgcodecs.imread("Resources/images/EPN-1.png",1);
                    }else if(mediaEstrelasNivel == 2){
                        permaneceNivel = Imgcodecs.imread("Resources/images/EPN-2.png",1);
                    }else if (mediaEstrelasNivel == 3){
                        permaneceNivel = Imgcodecs.imread("Resources/images/EPN-3.png",1);
                    }else if (mediaEstrelasNivel == 4){
                        permaneceNivel = Imgcodecs.imread("Resources/images/EPN-4.png",1);
                    }else{
                        permaneceNivel = Imgcodecs.imread("Resources/images/EPN-5.png",1);
                    }

                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    mostrarReferencias = false;

                    Imgproc.resize(permaneceNivel, permaneceNivel, new Size(350.0, 350.0));
                    dst = new Mat();
                    Mat roiPermaneceNivel = cenario.submat(new Rect(new Point(150, 100),new Point(500, 450)));
                    Core.addWeighted(roiPermaneceNivel,0.0,permaneceNivel,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150,500).rowRange(100,450));

                    break;
                case 3:
                    //Imagem para retroceder nível
                    Mat retrocedeNivel = Imgcodecs.imread("Resources/images/ERN.png",1);
                    if (mediaEstrelasNivel == 1){
                        retrocedeNivel = Imgcodecs.imread("Resources/images/ERN-1.png",1);
                    }else if(mediaEstrelasNivel == 2){
                        retrocedeNivel = Imgcodecs.imread("Resources/images/ERN-2.png",1);
                    }else if (mediaEstrelasNivel == 3){
                        retrocedeNivel = Imgcodecs.imread("Resources/images/ERN-3.png",1);
                    }else if (mediaEstrelasNivel == 4){
                        retrocedeNivel = Imgcodecs.imread("Resources/images/ERN-4.png",1);
                    }else{
                        retrocedeNivel = Imgcodecs.imread("Resources/images/ERN-5.png",1);
                    }

                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    mostrarReferencias = false;

                    Imgproc.resize(retrocedeNivel, retrocedeNivel, new Size(350.0, 350.0));
                    dst = new Mat();
                    Mat roiRetrocedeNivel = cenario.submat(new Rect(new Point(150, 100),new Point(500, 450)));
                    Core.addWeighted(roiRetrocedeNivel,0.0,retrocedeNivel,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150,500).rowRange(100,450));

                    break;
                case 4:
                    //Imagem para avançar de fase
                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    Mat avancaFase = Imgcodecs.imread("Resources/images/EAF.png",1);
                    Imgproc.resize(avancaFase, avancaFase, new Size(350.0, 350.0));
                    dst = new Mat();
                    Mat roiAvancaFase = cenario.submat(new Rect(new Point(150, 100),new Point(500, 450)));
                    Core.addWeighted(roiAvancaFase,0.0,avancaFase,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150,500).rowRange(100,450));

                    break;
                case 5:
                    //Imagem para retroceder fase
                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    Mat retrocedeFase = Imgcodecs.imread("Resources/images/ERF.png",1);
                    Imgproc.resize(retrocedeFase, retrocedeFase, new Size(350.0, 350.0));
                    dst = new Mat();
                    Mat roiRetrocedeFase = cenario.submat(new Rect(new Point(150, 100),new Point(500, 450)));
                    Core.addWeighted(roiRetrocedeFase,0.0,retrocedeFase,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150,500).rowRange(100,450));

                    break;
                case 6:
                    //Imagem perde vida
                    topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    mostrarReferencias = false;
                    Mat perdeVida = Imgcodecs.imread("Resources/images/EPV.png",1);
                    Imgproc.resize(perdeVida, perdeVida, new Size(350.0, 350.0));
                    dst = new Mat();
                    Mat roiPerdeVida = cenario.submat(new Rect(new Point(150, 100),new Point(500, 450)));
                    Core.addWeighted(roiPerdeVida,0.0,perdeVida,1.0,0.0,dst);
                    dst.copyTo(cenario.colRange(150,500).rowRange(100,450));

                    break;

                default:
            }
            //*/
            //System.out.println("Entrou no aguarda");
    //        contAguarda++;
    //        if (contAguarda==1){
    //            File yourFile = new File("Resources/sounds/acertou.wav");
    //            AudioInputStream stream;
    //            AudioFormat format;
    //            DataLine.Info info;
    //            Clip clip;
    //
    //            stream = AudioSystem.getAudioInputStream(yourFile);
    //            format = stream.getFormat();
    //            info = new DataLine.Info(Clip.class, format);
    //            clip = (Clip) AudioSystem.getLine(info);
    //            clip.open(stream);
    //            clip.start(); 
    //        }


    //         Mat silhueta = Imgcodecs.imread("Resources/images/corpo.png",1);
    //         
    //          // -+-+-+-+-+-+  mostra Silhueta
    //                    dst = new Mat();
    //                    Mat roiSilhueta = cenario.submat(new Rect(new Point(220, 230),new Point(420, 480)));
    //                    Core.addWeighted(roiSilhueta,1.0,silhueta,0.8,0.0,dst);
    //                    dst.copyTo(cenario.colRange(220,420).rowRange(230,480));
        }

        public void geraRelatorioErros (Mat cenario, Rodada rodada, Partida partida, int numErros) throws AWTException, IOException{
            SimpleDateFormat formatoData = new SimpleDateFormat("HH:mm:ss");
            Date hora = Calendar.getInstance().getTime();
            String dataErroAux = formatoData.format(hora);
            String[] aux = dataErroAux.split(":");
            String horaErro = aux[0]+"_"+aux[1]+"_"+aux[2];

            String dataErro;
            String data = "dd/MM/yyyy";
            String hora2 = "dd/MM/yyyy";

            java.util.Date agora = new java.util.Date();;
            SimpleDateFormat formata = new SimpleDateFormat(data);
            dataErro = formata.format(agora);
            formata = new SimpleDateFormat(hora2);
            //System.out.print(dataErro+" -- ");
            //System.err.println("Data: " + dataErro + "Novo...." + dataErroCerto);
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(screenSize);
            BufferedImage imagem = robot.createScreenCapture(screenRect);
            ImageIO.write(imagem, "JPEG", new File ("Users/PrintsDosErros/"+String.valueOf(player.getId())+"_"+String.valueOf(player.getNome())+"_"+horaErro+".JPEG"));
            //ImageIO.write(imagem, "PNG", new File ("Users/Erros/"+String.valueOf(player.getId())+"_"+String.valueOf(player.getNome())+"_"+dataErro+".PNG"));
            escreveCorpoHTML (rodada, horaErro, partida, dataErro);
        }
    
        public void mostrarEstrelas(Mat cenario, int pontos){
            Mat dst;
            Imgproc.resize(estrela, estrela, new Size(30.0, 30.0));
            double alpha = 1.25, beta = -0.2, gamma = -0.8;
            int ex1=470, ex2=500, ex3=530, ex4=560, ex5=590;

            if(pontos == 1 || pontos == 2){
                dst = new Mat();
                Mat roiEstrela1 = cenario.submat(new Rect(new Point(ex1, 5), new Point(ex1+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela1,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex1,ex1+30).rowRange(5,35));
                numEstrelasNivel.add(1);
            }
            if(pontos == 3 || pontos == 4){
                dst = new Mat();
                Mat roiEstrela1 = cenario.submat(new Rect(new Point(ex1, 5), new Point(ex1+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela1,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex1,ex1+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela2 = cenario.submat(new Rect(new Point(ex2, 5), new Point(ex2+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela2,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex2,ex2+30).rowRange(5,35));
                numEstrelasNivel.add(2);
            }
            if(pontos == 5 || pontos == 6){
                dst = new Mat();
                Mat roiEstrela1 = cenario.submat(new Rect(new Point(ex1, 5), new Point(ex1+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela1,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex1,ex1+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela2 = cenario.submat(new Rect(new Point(ex2, 5), new Point(ex2+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela2,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex2,ex2+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela3 = cenario.submat(new Rect(new Point(ex3, 5), new Point(ex3+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela3,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex3,ex3+30).rowRange(5,35));
                numEstrelasNivel.add(3);
            }
            if(pontos == 7 || pontos == 8){
                dst = new Mat();
                Mat roiEstrela1 = cenario.submat(new Rect(new Point(ex1, 5), new Point(ex1+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela1,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex1,ex1+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela2 = cenario.submat(new Rect(new Point(ex2, 5), new Point(ex2+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela2,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex2,ex2+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela3 = cenario.submat(new Rect(new Point(ex3, 5), new Point(ex3+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela3,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex3,ex3+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela4 = cenario.submat(new Rect(new Point(ex4, 5), new Point(ex4+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela4,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex4,ex4+30).rowRange(5,35));
                numEstrelasNivel.add(4);
            }
            if(pontos == 9 || pontos == 10){
                dst = new Mat();
                Mat roiEstrela1 = cenario.submat(new Rect(new Point(ex1, 5), new Point(ex1+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela1,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex1,ex1+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela2 = cenario.submat(new Rect(new Point(ex2, 5), new Point(ex2+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela2,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex2,ex2+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela3 = cenario.submat(new Rect(new Point(ex3, 5), new Point(ex3+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela3,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex3,ex3+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela4 = cenario.submat(new Rect(new Point(ex4, 5), new Point(ex4+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela4,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex4,ex4+30).rowRange(5,35));

                dst = new Mat();
                Mat roiEstrela5 = cenario.submat(new Rect(new Point(ex5, 5), new Point(ex5+30, 35)));
                Core.addWeighted(estrela,alpha,roiEstrela5,beta,gamma,dst);
                dst.copyTo(cenario.colRange(ex5,ex5+30).rowRange(5,35));
                numEstrelasNivel.add(5);
            }
        }
        
        public void mostrarTopoFeedback(boolean piscarTopo, Partida partida){
            if (piscarTopo){
                auxTopoFeedback++;
                int TEO = partida.getNivel().getTEO();
                //System.out.println("TEO: " + TEO);
                switch (TEO){ // este switch verifica o Tempo de Exposição dos Objetivos e, dependendo deste, faz piscar o topo N vezes
                    case 2:
                        if (auxTopoFeedback == 5 || auxTopoFeedback == 20 || auxTopoFeedback == 40){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 30){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 3:
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 35){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 20){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 4: 
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 35 || auxTopoFeedback == 65){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 20 || auxTopoFeedback == 50){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 5: 
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 35 || auxTopoFeedback == 65 || auxTopoFeedback == 95){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 20 || auxTopoFeedback == 50 || auxTopoFeedback == 80){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 6: 
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 32 || auxTopoFeedback == 58 || auxTopoFeedback == 82 || auxTopoFeedback == 106){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 20 || auxTopoFeedback == 44 || auxTopoFeedback == 70 || auxTopoFeedback == 94){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 7:
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 30 || auxTopoFeedback == 60 || auxTopoFeedback == 90 || auxTopoFeedback == 120){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 15 || auxTopoFeedback == 45 || auxTopoFeedback == 75 || auxTopoFeedback == 105){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                    case 8:
                        if (auxTopoFeedback == 10 || auxTopoFeedback == 30 || auxTopoFeedback == 60 || auxTopoFeedback == 90 || auxTopoFeedback == 120 || auxTopoFeedback == 150){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                        }
                        if (auxTopoFeedback == 15 || auxTopoFeedback == 45 || auxTopoFeedback == 75 || auxTopoFeedback == 105 || auxTopoFeedback == 135){
                            topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        }
                    break;
                }

                Imgproc.resize(topoFeedback, topoFeedback, new Size(270.0, 76.0));    
                dst = new Mat();
                Mat roiTopoFeedback = cenario.submat(new Rect(new Point(195-5-1, 0), new Point(195 -5 + 270 -1, 76)));
                Core.addWeighted(topoFeedback,1.25,roiTopoFeedback,0.5,0.0,dst);
                dst.copyTo(cenario.colRange(195-5-1 ,195 - 5 + 270 -1).rowRange(0,76));
            
            }else{
            
                Imgproc.resize(topoFeedback, topoFeedback, new Size(270.0, 76.0));    
                dst = new Mat();
                Mat roiTopoFeedback = cenario.submat(new Rect(new Point(195-5-1, 0), new Point(195 -5 + 270 -1, 76)));
                Core.addWeighted(topoFeedback,1.25,roiTopoFeedback,0.5,0.0,dst);
                dst.copyTo(cenario.colRange(195-5-1 ,195 - 5 + 270 -1).rowRange(0,76));
            }
    /*          if(piscarTopo){
                    if(cronometro_old != cronometro){
                        topoFeedback = Imgcodecs.imread("Resources/images/topoReferencia.png",1);
                        cronometro_old = cronometro;
                    }else{
                        topoFeedback = Imgcodecs.imread("Resources/images/topoFeedback.png",1);
                    }
                    System.out.println("cronometro: " + cronometro);
                }// */
        }

        public void buscaDadosCSVDetalhado(Rodada rodada, Partida partida){
            //Data da sessão
            SimpleDateFormat sdfRodada = new SimpleDateFormat("dd/MM/yyyy");
            String dateRodada = sdfRodada.format(new Date());
            rodada.setData(dateRodada);
            //Hora de Uso
            SimpleDateFormat sdfHoraRodada = new SimpleDateFormat("HH:mm:ss");
            Date horaRodada = Calendar.getInstance().getTime();
            String horaInicioRodada = sdfHoraRodada.format(horaRodada);
            rodada.setHora(horaInicioRodada);
            //Jogo
            rodada.setJogo(partida.getJogo().getNome());
            //Fase
            rodada.setFase(Integer.toString(partida.getFase().getNumeroFase()));
        }

        public void pausaJogo() throws InterruptedException{
            MainWindow.tecla.setKeyCode(ERROR);
            //geraProximaLinha = false;
            while (pausado){
                if (MainWindow.tecla.getKeyCode() == KeyEvent.VK_SPACE){
                    pausado = false;
                }
                Thread.sleep(1000);
                //segundosAux2 = segundos;
            }   
        }
    }//fim classe webcamfeed
}//fim classe Game
