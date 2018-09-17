/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import controle.Jogos;
import controle.Publico;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ParserJogos {
    
    public static Vector<Jogos> loadJogos() throws IOException{
        
        File[] diretorios = new File("Public/").listFiles(File::isDirectory);
        
        //System.out.println(jogo);
        //System.out.println(diretorios[0].getName());;
        
        Vector<Jogos> vet = new Vector<Jogos>();
        Jogos aux;
        Vector<Publico> pub = new Vector<Publico>();
        
        for(int i=0; i<diretorios.length;i++){
            aux = new Jogos();
            
            aux.setId(i); //Acho que nao vai dar boa esse +1
            aux.setNome(diretorios[i].getName());
            pub = ParserPublics.loadPublicos(aux.getNome());
            aux.setPublico(pub);
            
            String arquivo = diretorios[i].getPath();
            System.out.println(arquivo);
            //aux.setConfiguracoes(ParserPublics.parserParametros(arquivo)ParserPublics.parserParametros(arquivo));
            //aux.setFases(parserFases(arquivo,aux.getConfiguracoes()));
            //aux.setNiveis(parserNiveis(arquivo,aux.getConfiguracoes()));
            
            vet.add(aux);
        }
        return vet;
    }
}
