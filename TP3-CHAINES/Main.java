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
            	tabSousChaines[i] = "";

            	while ( !line.contains("#") ){
            		tabSousChaines[i] = tabSousChaines[i] + line;
            		line = bufferedReader.readLine();
            	}

            	tabSousChaines[i] = tabSousChaines[i] + line.replace("#", "");
            }

            line = bufferedReader.readLine();

            while ( !line.contains("#") ){
            	chaine = chaine + line;
            	line = bufferedReader.readLine();
            }

            chaine = line.replace("#", "");

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	public static boolean isDoable(){
		for ( String sousChaine : tabSousChaines ){
			if ( !chaine.contains(sousChaine) ){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		FILENAME=args[0];
		
		parse(args[0]);

		for ( String s : tabSousChaines ) {
			System.out.print(s + " ");
		}
		System.out.println("\n" + chaine);

		System.out.println("Doable: " + isDoable());

		try{
			String nomFichier =  FILENAME+".out.txt"; // Nom du fichier de sortie
            FileWriter fileWriter = new FileWriter(nomFichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

           printWriter.println("reponse");

            printWriter.close(); // Ferme le PrintWriter pour libérer les ressources
		}catch(IOException e) {
            e.printStackTrace();
        }
	}
}