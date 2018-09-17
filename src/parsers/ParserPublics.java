/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.opencsv.CSVReader;
import controle.Fase;
import controle.Jogos;
import controle.Nivel;
import controle.Publico;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import move4math.MainWindow;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ParserPublics {
    public static int linhaAtual;
    static boolean jaContouAsLinhas = false;
    public static Vector<Publico> loadPublicos(String jogos) throws IOException{
        
        File[] diretorios = new File("Public/" + jogos).listFiles(File::isDirectory);
        
        //System.out.println(jogo);
      //  System.out.println(diretorios[0].getName());;
        
        Vector<Publico> vet = new Vector<Publico>();
        Publico aux;
        
        for(int i=0; i<diretorios.length;i++){
            aux = new Publico();
            
            aux.setId(i); //Acho que nao vai dar boa esse +1
            aux.setNome(diretorios[i].getName());
            
            String arquivo = diretorios[i].getPath();
            System.out.println(arquivo);
            aux.setConfiguracoes(parserParametros(arquivo));
            aux.setFases(parserFases(arquivo,aux.getConfiguracoes()));
            aux.setNiveis(parserNiveis(arquivo,aux.getConfiguracoes()));
            
            vet.add(aux);
            
            //System.out.println(diretorios[i].getName() + "" + aux.getId());
        }
        return vet;
    }
    
    private static Vector<Fase> parserFases(String publico, Vector<Vector<Integer>> parametros) throws FileNotFoundException, IOException{
        Vector<Fase> fase = new Vector<Fase>();
        Fase aux;
        
        CSVReader reader = new CSVReader(new FileReader(publico + "/fases.csv"),';','"',1);
                List<String[]> vet = reader.readAll();
        
        Vector<String> vet1;
        //Vector<Vector<String>> vet2 = new Vector<Vector<String>>();
        
        for(String[] row : vet){
            aux = new Fase();
            vet1 = new Vector<String>(Arrays.asList(row));
            //vet1.removeElementAt(0);

            aux.setNumeroFase(Integer.parseInt(vet1.elementAt(0)));
            aux.setCIT(Integer.parseInt(vet1.elementAt(1)));
            aux.setEST(Integer.parseInt(vet1.elementAt(2)));

            System.out.println("Numero Fase: " + aux.getNumeroFase() + ", Row: " + row.toString());

            
//            aux.setNome(vet1.elementAt(0));
//            aux.setCIT(Integer.parseInt(vet1.elementAt(1)));
//            aux.setGAO(Integer.parseInt(vet1.elementAt(2)));
//            aux.setEST(Integer.parseInt(vet1.elementAt(3)));
//
//            System.out.println("" + aux.getNome() + "" + row.toString());
            
            fase.add(aux);
            //vet2.add(vet1);
            //System.out.println(Arrays.toString(row));
        }
        
        
        return fase;
    }
    
    private static Vector<Nivel> parserNiveis(String publico, Vector<Vector<Integer>> parametros) throws FileNotFoundException, IOException{
        Vector<Nivel> nivel = new Vector<Nivel>();
        Nivel aux;
         
        CSVReader reader = new CSVReader(new FileReader(publico + "/niveis.csv"),';','"',1);
                List<String[]> vet = reader.readAll();
                
//        CSVReader reader1 = new CSVReader(new FileReader(publico + "/niveis-2.csv"),';','"',1);
//                List<String[]> vet2 = reader.readAll();
        
        Vector<String> vet1, vet3;
        //Vector<Vector<String>> vet2 = new Vector<Vector<String>>();
        
        
        for(String[] row : vet){
            aux = new Nivel();
            vet1 = new Vector<String>(Arrays.asList(row));
            
                if("G".equals(vet1.elementAt(0))){
                    linhaAtual++;
                   // System.out.print(vet1.elementAt(0) + " ");
                   // System.out.println("linha: " + linhaAtual);
                }
                if("M".equals(vet1.elementAt(0))){
                    linhaAtual++;
                   // System.out.print(vet1.elementAt(0) + " ");
                   // System.out.println("linha: " + linhaAtual);
                }
                if("P".equals(vet1.elementAt(0))){
                    linhaAtual++;
                   // System.out.print(vet1.elementAt(0) + " ");
                   // System.out.println("linha: " + linhaAtual);
                }
                if (linhaAtual == vet.size()){
                    aux.setNumeroLinha(vet.size());
                    linhaAtual = 0;
                }else{
                    aux.setNumeroLinha(linhaAtual);
                }
            
            //if ()
            //linhaAtual++;

           // System.out.println("vet: " + vet.size());
            
           // aux.setNumero(Integer.parseInt(vet1.elementAt(0)));
            
            aux.setNumero(linhaAtual);

            
            //TAI
            if("G".equals(vet1.elementAt(0))){
                aux.setTAI(parametros.elementAt(0).elementAt(1));
            }else if("M".equals(vet1.elementAt(0))){
                aux.setTAI(parametros.elementAt(1).elementAt(1));
            }else if("P".equals(vet1.elementAt(0))){
                aux.setTAI(parametros.elementAt(2).elementAt(1));
            }
            
            //TEI
            if("G".equals(vet1.elementAt(1))){
                aux.setTEI(parametros.elementAt(0).elementAt(2));
            }else if("M".equals(vet1.elementAt(1))){
                aux.setTEI(parametros.elementAt(1).elementAt(2));
            }else if("P".equals(vet1.elementAt(1))){
                aux.setTEI(parametros.elementAt(2).elementAt(2));
            }            
            
            //LAD
            if("E".equals(vet1.elementAt(2))){
                aux.setLAD(1);
            }else if("D".equals(vet1.elementAt(2))){
                aux.setLAD(2);
            }else if("A".equals(vet1.elementAt(2))){
                aux.setLAD(3);
            }           
            
            //TIO
            if("G".equals(vet1.elementAt(3))){
                aux.setTIO(parametros.elementAt(0).elementAt(3));
            }else if("M".equals(vet1.elementAt(3))){
                aux.setTIO(parametros.elementAt(1).elementAt(3));
            }else if("P".equals(vet1.elementAt(3))){
                aux.setTIO(parametros.elementAt(2).elementAt(3));
            }
            
            //QIS
            if("G".equals(vet1.elementAt(4))){
                aux.setQIS(parametros.elementAt(0).elementAt(0));
            }else if("M".equals(vet1.elementAt(4))){
                aux.setQIS(parametros.elementAt(1).elementAt(0));
            }else if("P".equals(vet1.elementAt(4))){
                aux.setQIS(parametros.elementAt(2).elementAt(0));
            }
            
            //QIO
            if("1".equals(vet1.elementAt(5))){
                aux.setQIO(1);
            }else if("3".equals(vet1.elementAt(5))){
                aux.setQIO(3);
            }else if("4".equals(vet1.elementAt(5))){
                aux.setQIO(4);
            }else if("5".equals(vet1.elementAt(5))){
                aux.setQIO(5);
            }
            
            //AIO
            //aux.setAIO(vet1.elementAt(7));
            
            //OTI
            if("0".equals(vet1.elementAt(6))){
                aux.setOTI(0);
            }else if("1".equals(vet1.elementAt(6))){
                aux.setOTI(1);
            }else if("2".equals(vet1.elementAt(6))){
                aux.setOTI(2);
            }
            
            //TEO
            if("G".equals(vet1.elementAt(7))){
                aux.setTEO(parametros.elementAt(0).elementAt(4));
            }else if("M".equals(vet1.elementAt(7))){
                aux.setTEO(parametros.elementAt(1).elementAt(4));
            }else if("P".equals(vet1.elementAt(7))){
                aux.setTEO(parametros.elementAt(2).elementAt(4));
            }            
            //System.out.println(aux.getNumero()+" "+aux.getTAS()+" "+aux.getTES()+" "+aux.getLAD()+" "+aux.getTAR());;
            
            //primeiroICC
            aux.setPrimeiroICC(vet1.elementAt(8));
            
            //segundoICC
            aux.setSegundoICC(vet1.elementAt(9));
            
            //terceiroICC
            aux.setTerceiroICC(vet1.elementAt(10));
            
            nivel.add(aux);
        }
        
//        for(String[] row : vet2){
//            aux = new Nivel();
//            vet3 = new Vector<String>(Arrays.asList(row));
//            //System.out.println(vet1);
//
//            aux.setNumero(Integer.parseInt(vet3.elementAt(0)));
//            
//            //TAI
//            if("GG".equals(vet3.elementAt(1))){
//                aux.setTAI(parametros.elementAt(0).elementAt(1));
//            }else if("G".equals(vet3.elementAt(1))){
//                aux.setTAI(parametros.elementAt(1).elementAt(1));
//            }else if("M".equals(vet3.elementAt(1))){
//                aux.setTAI(parametros.elementAt(2).elementAt(1));
//            }else if("P".equals(vet3.elementAt(1))){
//                aux.setTAI(parametros.elementAt(3).elementAt(1));
//            }
//            /*
//            if("G".equals(vet3.elementAt(1))){
//                aux.setTAS(parametros.elementAt(0).elementAt(1));
//            }else if("M".equals(vet3.elementAt(1))){
//                aux.setTAS(parametros.elementAt(1).elementAt(1));
//            }else if("P".equals(vet3.elementAt(1))){
//                aux.setTAS(parametros.elementAt(2).elementAt(1));
//            }
//            */
//            //TEI
//            if("GG".equals(vet3.elementAt(2))){
//                aux.setTEI(parametros.elementAt(0).elementAt(2));
//            }else if("G".equals(vet3.elementAt(2))){
//                aux.setTEI(parametros.elementAt(1).elementAt(2));
//            }else if("M".equals(vet3.elementAt(2))){
//                aux.setTEI(parametros.elementAt(2).elementAt(2));
//            }else if("P".equals(vet3.elementAt(2))){
//                aux.setTEI(parametros.elementAt(3).elementAt(2));
//            }            
//            
//            /*            
//            if("G".equals(vet3.elementAt(2))){
//                aux.setTES(parametros.elementAt(0).elementAt(2));
//            }else if("M".equals(vet3.elementAt(2))){
//                aux.setTES(parametros.elementAt(1).elementAt(2));
//            }else if("P".equals(vet3.elementAt(2))){
//                aux.setTES(parametros.elementAt(2).elementAt(2));
//            }            
//            */
//            
//            //LAD
//            if("E".equals(vet3.elementAt(3))){
//                aux.setLAD(1);
//            }else if("D".equals(vet3.elementAt(3))){
//                aux.setLAD(2);
//            }else if("A".equals(vet3.elementAt(3))){
//                aux.setLAD(3);
//            }           
//            
//            //TIO
//            if("GG".equals(vet3.elementAt(4))){
//                aux.setTIO(parametros.elementAt(0).elementAt(4));
//            }else if("G".equals(vet3.elementAt(4))){
//                aux.setTIO(parametros.elementAt(1).elementAt(4));
//            }else if("M".equals(vet3.elementAt(4))){
//                aux.setTIO(parametros.elementAt(2).elementAt(4));
//            }else if("P".equals(vet3.elementAt(4))){
//                aux.setTIO(parametros.elementAt(3).elementAt(4));
//            }
//            /*
//            if("G".equals(vet3.elementAt(4))){
//                aux.setTAR(parametros.elementAt(0).elementAt(3));
//            }else if("M".equals(vet3.elementAt(4))){
//                aux.setTAR(parametros.elementAt(1).elementAt(3));
//            }else if("P".equals(vet3.elementAt(4))){
//                aux.setTAR(parametros.elementAt(2).elementAt(3));
//            }
//            */
//            //QIS
//            if("GG".equals(vet3.elementAt(5))){
//                aux.setQIS(parametros.elementAt(0).elementAt(5));
//            }else if("G".equals(vet3.elementAt(5))){
//                aux.setQIS(parametros.elementAt(1).elementAt(5));
//            }else if("M".equals(vet3.elementAt(5))){
//                aux.setQIS(parametros.elementAt(2).elementAt(5));
//            }else if("P".equals(vet3.elementAt(5))){
//                aux.setQIS(parametros.elementAt(3).elementAt(5));
//                
//            //QIO
//            if("1".equals(vet3.elementAt(6))){
//                aux.setQIO(1);
//            }else if("3".equals(vet3.elementAt(6))){
//                aux.setQIO(3);
//            }else if("4".equals(vet3.elementAt(6))){
//                aux.setQIO(4);
//            }else if("5".equals(vet3.elementAt(6))){
//                aux.setQIO(5);
//            }
//            
//            //AIO
//            //aux.setAIO(vet3.elementAt(7));
//            
//            //OTI
//            if("0".equals(vet3.elementAt(7))){
//                aux.setOTI(0);
//            }else if("1".equals(vet3.elementAt(7))){
//                aux.setOTI(1);
//            }else if("2".equals(vet3.elementAt(7)))
//                aux.setOTI(2);
//            }
//            //TEO
//            if("GG".equals(vet3.elementAt(8))){
//                aux.setTEO(parametros.elementAt(0).elementAt(4));
//            }else if("G".equals(vet3.elementAt(8))){
//                aux.setTEO(parametros.elementAt(1).elementAt(4));
//            }else if("M".equals(vet3.elementAt(8))){
//                aux.setTEO(parametros.elementAt(2).elementAt(4));
//            }else if("P".equals(vet3.elementAt(8))){
//                aux.setTEO(parametros.elementAt(3).elementAt(4));
//            }            
//            //System.out.println(aux.getNumero()+" "+aux.getTAS()+" "+aux.getTES()+" "+aux.getLAD()+" "+aux.getTAR());;
//            
//            //ICC
//            aux.setICC(vet3.elementAt(9));
//            
//            nivel.add(aux);
//        }
        
        return nivel;
    }
    
    //os nomes estao trocados - era pra ser parserPublico, ja que o arquivo parametros.csv é usado só em outro momento... EDIT: nem é usado xD
    //Manolo fez assim, entao assim ficou... xD
    public static Vector<Vector<Integer>> parserParametros(String publico) throws FileNotFoundException, IOException{
        Vector<Vector<Integer>> matrizInteiros = new Vector<Vector<Integer>>();
        Vector<Integer> listaInteiros;
        
        CSVReader reader = new CSVReader(new FileReader(publico + "/publico.csv"),';','"',2);
                List<String[]> vet = reader.readAll();
        
        Vector<String> vet1;
        Vector<Vector<String>> vet2 = new Vector<Vector<String>>();
        
        for(String[] row : vet){
            vet1 = new Vector<String>(Arrays.asList(row));
            vet1.removeElementAt(0);
            
            vet2.add(vet1);
            //System.out.println(Arrays.toString(row));
        }
        
        for(int a=0;a<vet2.size();a++){
            //System.out.println(vet2.elementAt(a).size());
            listaInteiros = new Vector<Integer>();
                for(int b=0;b<vet2.elementAt(a).size();b++){
                    //System.out.print("!"+vet2.elementAt(a).elementAt(b)+"!");
                    listaInteiros.add(Integer.parseInt(vet2.elementAt(a).elementAt(b)));
                    //System.out.print(Integer.parseInt(vet2.elementAt(a).elementAt(b))+" ");
                }
               // System.out.println("");
                //System.out.println("");
                matrizInteiros.add(listaInteiros);
            }
        /*for(int a=0;a<matrizInteiros.size();a++){
            for(int b=0;b<matrizInteiros.elementAt(a).size();b++){
                System.out.print(matrizInteiros.elementAt(a).elementAt(b)+" ");
            }
            System.out.println("");
        }
        */
        return matrizInteiros;
    }
}
