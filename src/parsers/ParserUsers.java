/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import jogo.Player;
import jogo.Rodada;
import jogo.Sessao;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ParserUsers {
    
    public static Vector<Player> parserUsuarios() throws FileNotFoundException, IOException{
        Vector<Player> vet = new Vector<Player>();
        Player aux;
         
        File[] arquivos = new File("Users/").listFiles(File::isFile);
        System.out.println("----------------------");
        
        
        
        for (int i=0; i<arquivos.length; i++){
            if(arquivos[i].getName().lastIndexOf('_')<2 && arquivos[i].getName().lastIndexOf('_')!=-1
                    && !arquivos[i].getName().contains("detalhado") && !arquivos[i].getName().contains("erros")){
                System.out.println(arquivos[i].getName());
            }
            //System.out.println(arquivos[i].getPath());
        }
        
        System.out.println("----------------------");
        
        for(int i=0; i<arquivos.length;i++){
            aux = new Player();
            
            if(arquivos[i].getName().lastIndexOf('_')<2 && arquivos[i].getName().lastIndexOf('_')!=-1
                    && !arquivos[i].getName().contains("detalhado") && !arquivos[i].getName().contains("erros")){
                aux = parserArquivoSimples(arquivos[i].getPath());
                System.out.println(arquivos[i].getName());
                vet.add(aux);
            }
        }
        
        //System.out.println(vet.size());
        //System.out.println(vet.elementAt(0).getPublico());
        
        return vet;
    }
    
    private static Player parserArquivoSimples(String arquivo) throws FileNotFoundException, IOException{
        Vector<Sessao> vetSessao = new Vector<Sessao>();
        Sessao sessao;
        Player aux = new Player();
        int i;
        Vector<Vector<String>> usuario = new Vector<Vector<String>>();
        
        usuario = ParserCSV.ReadCSVFile(arquivo, 0);
        
        aux.setDataCadastro(usuario.elementAt(0).elementAt(1));
        aux.setNome(usuario.elementAt(1).elementAt(1));
        //aux.setId(Integer.parseInt(usuario.elementAt(1).elementAt(1)));
        //estou setando o ID como sendo o número na frente do arquivo. o caminho começa com Users/
        aux.setId(Integer.parseInt(arquivo.substring(6, 7)));
        aux.setDataNascimento(usuario.elementAt(2).elementAt(1));
        aux.setSexo(usuario.elementAt(3).elementAt(1));
//        aux.setNomeMae(usuario.elementAt(3).elementAt(1));
//        aux.setNomeEspecialista(usuario.elementAt(4).elementAt(1));
        aux.setPublico(usuario.elementAt(4).elementAt(1));
        aux.setVidas(Integer.parseInt(usuario.elementAt(5).elementAt(1)));
        //System.out.println("Integer.parseInt(usuario.elementAt(4).elementAt(1)): " + Integer.parseInt(usuario.elementAt(4).elementAt(1)));
        aux.setJustificativaPublico(usuario.elementAt(6).elementAt(1));
        aux.setFaseAlfabetizacao(usuario.elementAt(7).elementAt(1));
        aux.setJustificativafaseAlfabetizacao(usuario.elementAt(8).elementAt(1));
        //por que maior que 7???
        System.out.println(usuario.size());
        if(usuario.size()>9){ //Alterado por causa do campo observações, antes era 8
            for(i=10;i<usuario.size();i++){
                sessao = new Sessao();
                sessao.setId(Integer.parseInt(usuario.elementAt(i).elementAt(0)));
                sessao.setData(usuario.elementAt(i).elementAt(1));
                sessao.setHora(usuario.elementAt(i).elementAt(2));
                sessao.setJogo(usuario.elementAt(i).elementAt(3));
                sessao.setFase(usuario.elementAt(i).elementAt(4));
                sessao.setNivel(usuario.elementAt(i).elementAt(5));
                sessao.setPontuacaoGeral(Integer.parseInt(usuario.elementAt(i).elementAt(6)));
                sessao.setTempoTotal(Integer.parseInt(usuario.elementAt(i).elementAt(7)));
                
                sessao.setRodadas(parserArquivoDetalhado(arquivo)); //<----------- Arquivo Detalhado!
                
                vetSessao.add(sessao);
            }
        }
        aux.setSessoes(vetSessao);
        
        return aux;
    }
    
    private static Vector<Rodada> parserArquivoDetalhado(String arquivo) throws IOException{
        Vector<Rodada> rodadas = new Vector<Rodada>();
        Rodada rodada;
        int i;
        
        String arquivoDetalhado = new StringBuilder(arquivo).insert(arquivo.length()-4, "_detalhado").toString();
        //System.out.println(arquivoDetalhado);
        
        Vector<Vector<String>> rodadasString = new Vector<Vector<String>>();
        
        rodadasString = ParserCSV.ReadCSVFile(arquivoDetalhado, 1);
        
        for(i=0;i<rodadasString.size();i++){
            rodada = new Rodada();
            rodada.setIdSessao(Integer.parseInt(rodadasString.elementAt(i).elementAt(0)));
            rodada.setData(rodadasString.elementAt(i).elementAt(1));
            rodada.setHora(rodadasString.elementAt(i).elementAt(2));
            rodada.setJogo(rodadasString.elementAt(i).elementAt(3));
            rodada.setFase(rodadasString.elementAt(i).elementAt(4));
            rodada.setNivel(Integer.parseInt(rodadasString.elementAt(i).elementAt(5)));
            rodada.setLinhaNivel(Integer.parseInt(rodadasString.elementAt(i).elementAt(6)));
            rodada.setImgRef(rodadasString.elementAt(i).elementAt(7));
            rodada.setImgTocada(rodadasString.elementAt(i).elementAt(8));
            rodada.setTempoToque(Integer.parseInt(rodadasString.elementAt(i).elementAt(9)));
            rodada.setAcao(rodadasString.elementAt(i).elementAt(10));
            rodada.setPontosMotor(Integer.parseInt(rodadasString.elementAt(i).elementAt(11)));
            rodada.setPontosCognitivo(Integer.parseInt(rodadasString.elementAt(i).elementAt(12)));
            
            rodadas.add(rodada);
        }
        return rodadas;
    }
}
