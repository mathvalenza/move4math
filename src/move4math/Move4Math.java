/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package move4math;

import conteudo.ConjuntoImagem;
import controle.Jogos;
import controle.Publico;
import java.io.IOException;
import java.util.Vector;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import jogo.Player;
import parsers.ParserJogos;
import parsers.ParserTables;
import parsers.ParserUsers;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class Move4Math {

    static public Vector<ConjuntoImagem> conjuntosDeTrabalho = new Vector<ConjuntoImagem>();
    static public Vector<Publico> publicos = new Vector<Publico>();
    static public Vector<Player> players = new Vector<Player>();
    static public Vector<Jogos> jogos = new Vector<Jogos>();
    static public int webcamWidth = 640;
    static public int webcamHeight = 480;
    static public int indiceJogoAtual;
    static public int indicePublicoAtual;
    static public int indicePlayerAtual;
    static public int indiceFaseAtual;
    static public int indiceNivelAtual;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        String jogo;
        MainWindow janelaPrincipal = new MainWindow();
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setVisible(true);
        jogos = ParserJogos.loadJogos();
        conjuntosDeTrabalho = ParserTables.parserTabelas();
        players = ParserUsers.parserUsuarios();
    }

    public static Publico getPublicoId(int id) {
        System.out.println("Publico: " + publicos.size());
        for (Publico publico : publicos) {
            if (publico.getId() == id) {
                return publico;
            }
        }
        return null;
    }

    public static Jogos getJogoNome(String nome) {
        for (Jogos jogo : jogos) {
            if (jogo.getNome() == nome) {
                return jogo;
            }
        }
        return null;
    }
}
