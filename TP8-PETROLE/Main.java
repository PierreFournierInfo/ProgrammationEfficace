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

public class Main {
    private static int nbrGisements;
    private static ArrayList<Gisement> listGisements = new ArrayList<>();

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            String[] result;
            Gisement gis;

            //Ligne 1 nbr gisements
            ligne = bufferedReader.readLine();
            nbrGisements = Integer.parseInt(ligne);

            //Liste gisements
            while((ligne = bufferedReader.readLine()) != null){
                result = ligne.split(" ");
                gis = new Gisement(Integer.parseInt(result[0]),Integer.parseInt(result[1]),Integer.parseInt(result[2]));
                listGisements.add(gis);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int solution(){
        return 0;
    }

    public static void printList(){
        for ( Gisement g : listGisements ){
            System.out.println("x1: " + g.x1 + " x2: " + g.x2 + " y: " + g.y);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        parse(args[0]);

        printList();

        System.out.println(solution());
	}  
}
