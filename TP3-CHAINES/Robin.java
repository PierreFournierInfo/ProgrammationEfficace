import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Robin {
    private static String[] tabSousChaines;
	private static String chaine;
	private static int nbrSousChaines;
    private static ArrayList<Integer>[] motif; 

	public static void parse(String file){

		try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            nbrSousChaines = Integer.parseInt(line);
            tabSousChaines = new String[nbrSousChaines];
            motif = new ArrayList[nbrSousChaines];

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

    public static boolean estPresent(String sousC, int i){
        for(int j=0; j<sousC.length(); j++){
            if(sousC.charAt(j)!=chaine.charAt(i+j)){
                return false;
            }
        }
        return true;
    }

    public static void parcours(){
        for(int i = 0; i < tabSousChaines.length; i++){
            motif[i] = new ArrayList<>();
        }
        for(int i = 0; i < chaine.length(); i++){
            for(int j = 0; j < tabSousChaines.length; j++){
                if(estPresent(tabSousChaines[j], i)){
                    motif[j].add(i);
                    break;
                }
            }
        }
    }

    public static void afficheMotif(){
        for(int i = 0; i < motif.length; i++){
            for(Integer s : motif[i]){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

	public static void main(String[] args) {
		parse(args[0]);

		for ( String s : tabSousChaines ) {
			System.out.print(s + " ");
		}
		System.out.println("\n" + chaine);

        parcours();
        afficheMotif();
	}


    
}
