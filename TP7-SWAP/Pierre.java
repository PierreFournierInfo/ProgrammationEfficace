import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Pierre {
    private static int nbrDisque;
    private static ArrayList<Disque> listDisque = new ArrayList<>();
    private static int dispo;
    private static int achete;


    public static int solution(){
        Collections.sort(listDisque, (d1, d2) -> Integer.compare(d1.avant, d2.avant));
        
        for(int i=0;i<nbrDisque;i++){
            if (listDisque.get(i).apres>listDisque.get(i).avant){
                if(listDisque.get(i).avant>dispo){
                    achete+=listDisque.get(i).avant-dispo;
                }
                dispo+=listDisque.get(i).apres;
            }
        }
        //System.out.println("--------------SOLUTION-------------------"+achete);
        return achete;
    }






	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            Disque disk;
            String[] result;

            //Ligne 1 nbr disque
            ligne = bufferedReader.readLine();
            nbrDisque = Integer.parseInt(ligne);

            //Liste disque
            while((ligne = bufferedReader.readLine()) != null){
                result = ligne.split(" ");
                disk = new Disque(Integer.parseInt(result[0]),Integer.parseInt(result[1]));
                listDisque.add(disk);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printList(){
        for ( Disque d : listDisque ){
            System.out.println(d.avant + " " + d.apres);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        parse(args[0]);
        solution();

        //printList();
	}  

    
    
}
