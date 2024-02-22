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

	public static void parse(String file){

		try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            nbrSousChaines = Integer.parseInt(line);
            tabSousChaines = new String[nbrSousChaines];

            for ( int i = 0; i < nbrSousChaines; i++ ){

            }

            line = bufferedReader.readLine();
            while ( line != null ){


            	line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		parse(args[0]);

		for ( String s : tabSousChaines ) {
			System.out.print(s + " ");
		}
		System.out.println("\n" + chaine);


	}
}