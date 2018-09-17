/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Mayco, Matheus, Henrique
 */
public class ParserCSV {

    /**
     *
     * @param arquivo
     * @param pularLinhas
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Vector<Vector<String>> ReadCSVFile(String arquivo, int pularLinhas) throws FileNotFoundException, IOException{
        CSVReader reader = new CSVReader(new FileReader(arquivo),';','"',pularLinhas);
        //String [] nextLine;
        //while((nextLine = reader.readNext())!= null){
        //    System.out.println(nextLine[0]+nextLine[1]);
        
        List<String[]> vet = reader.readAll();
        
        Vector<String> vet1;
        Vector<Vector<String>> vet2 = new Vector<Vector<String>>();
        
        for(String[] row : vet){
            vet1 = new Vector<String>(Arrays.asList(row));
            vet2.add(vet1);
            //System.out.println(Arrays.toString(row));
        }
        
        return vet2;
                
    }
}
