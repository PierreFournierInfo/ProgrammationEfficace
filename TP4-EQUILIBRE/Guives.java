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


public class Guives{

	private static int m;
	private static int k;
	private static int n;
	private static ArrayList<Integer> mosaique = new ArrayList<Integer>(); 
	private static int[] proportions;
	private static int[] quantite;

	@SuppressWarnings("unchecked")
    public static void parse(String file){
    	try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // 1st line
            String line = bufferedReader.readLine();
            String[] result = line.split(" ");

            m = Integer.parseInt(result[0]);
            proportions = new int[m];
            quantite = new int[m];
            k = Integer.parseInt(result[1]);

            // 2nd line
            line = bufferedReader.readLine();
            result = line.split(" ");

            for( int i = 0; i<m; i++ ){
            	proportions[i] = Integer.parseInt(result[i]);
            	n = n + proportions[i];
            }

            // 3rd line
            line = bufferedReader.readLine();
            result = line.split(" ");

            for( int i = 0; i<result.length; i++ ){
            	mosaique.add(Integer.parseInt(result[i]));
            	quantite[(Integer.parseInt(result[i]))-1] += 1;
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTab(int[] tab){
    	for ( int i : tab ){
    		System.out.print(" " + i);
    	}
    }

    public static void printMosaique(){
    	System.out.print("Mosaique: ");
    	for ( Integer i : mosaique ){
    		System.out.print(" " + i);
    	}
    }

	public static void main(String[] args) {
		parse(args[0]);

		System.out.println("m: " + m);
		System.out.println("k: " + k);
		System.out.println("n: " + n);
		System.out.print("Proportions: ");
		printTab(proportions);
		System.out.println();
		System.out.print("Quantite deja pose: ");
		printTab(quantite);
		System.out.println();
		printMosaique();
		System.out.println();
	}
}