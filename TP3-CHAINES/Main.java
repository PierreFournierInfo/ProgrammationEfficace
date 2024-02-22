import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main{

	private static String[] tabSousChaines;
	private static String chaine;
	private static int nbrSousChaines;
	private static String FILENAME;

	

	public static void parse(String file){

		try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            nbrSousChaines = Integer.parseInt(line);
            tabSousChaines = new String[nbrSousChaines];

            for ( int i = 0; i < nbrSousChaines; i++ ){
            	line = bufferedReader.readLine();

            	tabSousChaines[i] = line.replace("#", "");
            }

            line = bufferedReader.readLine();
            chaine = line.replace("#", "");

			

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		FILENAME=args[0];
		
		parse(args[0]);

		for ( String s : tabSousChaines ) {
			System.out.print(s + " ");
		}
		System.out.println("\n" + chaine);

		try{
			String nomFichier =  FILENAME+".out.txt"; // Nom du fichier de sortie
            //System.out.println("\n Nombre de recettes possibles "+nombreRecettes);
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Reponse"); // Écrit le paramètre dans le fichier
            printWriter.close(); // Ferme le PrintWriter pour libérer les ressources
		}catch(IOException e) {
            e.printStackTrace();
        }
	}
}