/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import conteudo.ConjuntoImagem;
import conteudo.Imagem;
import conteudo.Som;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ParserTables {
    
    public static Vector<ConjuntoImagem> parserTabelas() throws IOException{
        // Para compor os 'Conjuntos de Imagens', primeiro precisamos de todos os 'Sons', depois de todos os 'Conjuntos de Sons',
	// depois todas as 'Imagens' e finalmente obtemos todos os 'Conjuntos de Imagens'.

	// 'vetores de vetores' que guardam todos os arquivos CSV's
	Vector< Vector<String> > vetSons;
	//Vector< Vector<String> > vetConjSons;
	Vector< Vector<String> > vetImagens;
        Vector< Vector<String> > vetConjImagens; // objeto principal
        
	vetSons = ParserCSV.ReadCSVFile("Tables/tabela_sons.csv",1);
	//vetConjSons = ParserCSV.ReadCSVFile("Tables/tabela_conjunto_sons.csv");
	vetImagens = ParserCSV.ReadCSVFile("Tables/tabela_imagens.csv",1);
	vetConjImagens = ParserCSV.ReadCSVFile("Tables/tabela_conjunto_imagens.csv",1);
        
        Vector<Som> sons;
        sons = parserSom(vetSons);
        
        //Vector<ConjuntoSom> conjSom;
        //conjSom = parserConjSom(vetConjSons, sons);
        
        Vector<Imagem> imagens;
        imagens = parserImage(vetImagens, sons);
        
        Vector<ConjuntoImagem> conjImagens;
        conjImagens = parserConjuntoImagem(vetConjImagens, imagens);
        
        /*
        for(int i=0; i<sons.size();i++){
            //Vector<String>aux = vet2.elementAt(i)
                System.out.println(sons.elementAt(i).getId()+ " "+sons.elementAt(i).getDescricao()+" "+sons.elementAt(i).getSom());
            }
            
        */
        
        return conjImagens;
    }
    
    private static Vector<Som> parserSom(Vector< Vector<String> > vet){
        Som som;
        Vector<Som> sons = new Vector<Som>();
        
        for(int i=0; i<vet.size();i++){
            // Primeiro vamos montar um objSom
            // Cada som possui um ID, uma descrição e um arquivo de som associado
            som = new Som();

            som.setId(Integer.parseInt(vet.elementAt(i).elementAt(0)));
            //System.out.println(vet.elementAt(i).elementAt(0));

            // Adicionamos a descricao
            som.setDescricao(vet.elementAt(i).elementAt(1));

            // Adicionamos a string do som
            String strSom = "Resources/sounds/" + vet.elementAt(i).elementAt(2);
            
            som.setSom(strSom);
            
            // Cada linha é um registro de som
            // Agora que montamos uma linha (registros) temos que inserir no vetor de sons
            sons.add(som);
        }
        return sons;
    }

    private static Vector<Imagem> parserImage(Vector<Vector<String>> vet, Vector<Som> sons) {
        Imagem objImagem;
        Vector<Imagem> imagens = new Vector<Imagem>();
        
        for(int i=0; i < vet.size(); i++){
            // Primeiro vamos montar um objImagem
            // Cada Imagem possui um ID, uma descrição, um png e um som associado
            objImagem = new Imagem();

            // Adicionamos o ID
            objImagem.setId(Integer.parseInt(vet.elementAt(i).elementAt(0)));

            // Adicionamos a descricao
            objImagem.setDescricao(vet.elementAt(i).elementAt(1));

            // Adicionamos o cv::mat
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //gambiarra
            
            //System.out.println("Resources/images/" + vet.elementAt(i).elementAt(2));
            
            //ATENCAO!
            //o parametro 1 em imread abaixo faz com que todas as imagens lidas sejam imagens com 3 canais - RGB
            //significa que imagens PNG com o canal alfa terão o mesmo ignorado
            //imagens com um grau de opacidade na transparencia serão afetadas!
            Mat imgTemp = Imgcodecs.imread("Resources/images/" + vet.elementAt(i).elementAt(2),1);
            objImagem.setImg(imgTemp);

            // Guardamos a string (local do arquivo)
            objImagem.setImgStr("Resources/images/" + vet.elementAt(i).elementAt(2));

            // Conjunto de sons associado é na verdade o ID do conjunto de sons
            int idSomAssociado = Integer.parseInt(vet.elementAt(i).elementAt(3));
            for(int j=0; j < sons.size(); j++)
                    if( sons.elementAt(j).getId()== idSomAssociado)
                            objImagem.setSom(sons.elementAt(j));

            // Finalmente adicionamos a imagem no vetor de imagens
            imagens.add(objImagem);
	}
	return imagens;
    }

    private static Vector<ConjuntoImagem> parserConjuntoImagem(Vector<Vector<String>> vet, Vector<Imagem> imagens) {
        ConjuntoImagem objConjuntoImagem;
        Vector<ConjuntoImagem> conjImagem = new Vector<ConjuntoImagem>();
        
        for(int i=0; i< vet.size();i++){
            // Primeiro vamos montar um objConjuntoImagem
            // Cada ConjuntoImagem possui um ID, uma descrição, uma lista de ids de imagens
            objConjuntoImagem = new ConjuntoImagem();
            // Adicionamos o ID
            objConjuntoImagem.setId(Integer.parseInt(vet.elementAt(i).elementAt(0)));
            // Adicionamos a descricao
            objConjuntoImagem.setDescricao(vet.elementAt(i).elementAt(1));
            // Agora temos varias strings contendo uma lista de ids de imagens
            // Precisamos quebrar essas strings em um vector
            Vector<Vector<String>> idsImagensMatrix = new Vector<Vector<String>>();
            Vector<String> aux;
                        
            for(int j=2;j<vet.elementAt(i).size();j++){
                String[] partes = vet.elementAt(i).elementAt(j).split(" ");
                aux = new Vector<String>(Arrays.asList(partes));
                for(int h=0;h<aux.size();h++){
                    if(aux.elementAt(h)==""){
                        aux.removeElementAt(h);
                        h=0;
                    }                    
                }
                idsImagensMatrix.add(aux);
            }

            // Agora que temos o vetor de inteiros
            // Precisamos associar cada inteiro com o id da imagem
            // Depois montamos um vetor de imagens            
            Vector<Imagem> aux1;
            Vector<Vector<Imagem>> aux2 = new Vector<Vector<Imagem>>();
            
            for(int a=0;a<idsImagensMatrix.size();a++){
                aux1 = new Vector<Imagem>();
                for(int b=0;b<idsImagensMatrix.elementAt(a).size();b++){                    
                    for(int c=0;c<imagens.size();c++){
                        if((Integer.parseInt(idsImagensMatrix.elementAt(a).elementAt(b)))==(imagens.elementAt(c).getId())){
                            aux1.add(imagens.elementAt(c));
                            aux1.lastElement().setGrupo(a);
                        }
                    }                    
                }
                aux2.add(aux1);
            }
            objConjuntoImagem.setImagens(aux2);
            conjImagem.add(objConjuntoImagem);
        }
        //System.out.println(conjImagem.size());
        return conjImagem;
    }
}
