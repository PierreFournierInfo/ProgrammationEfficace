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


public class Main{

	private static int m;//somme des ai
	private static int k;
	private static int kInit;
	private static int n;
	private static int nInit;

	
	private static ArrayList<Integer> mosaique = new ArrayList<Integer>(); 
	private static int[] proportions;//f
	private static int[] quantite;//s
	private static double[] proportionsDouble;
	private static double[] proportionsActuelleDouble;




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
			kInit=Integer.parseInt(result[1]);

            // 2nd line
            line = bufferedReader.readLine();
            result = line.split(" ");

            for( int i = 0; i<m; i++ ){
            	proportions[i] = Integer.parseInt(result[i]);
            	n = n + proportions[i];
            }
			nInit=n;

            // 3rd line
            if ( k != 0 ){
                line = bufferedReader.readLine();
                result = line.split(" ");

                for( int i = 0; i<result.length; i++ ){
                	mosaique.add(Integer.parseInt(result[i]));
                	quantite[(Integer.parseInt(result[i]))-1] += 1;
                }
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
		/* 
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
		*/

		int solution=ajoutTuile();
		if(solution==-1){
			//printMosaique();

			System.out.println("forever"); 
		}else{
			System.out.println(solution);
		}
	}

	//si le nombre de carreaux du modèle i
    //utilisés à un moment donné, et n = s1 + ... + sm. Alors la frise
    //est équilibrée si, à tout moment et pour chaque i, si est
    //strictement compris entre n·fi − 1 et n·fi + 1.

    public static boolean estEquilibre(int[]tab){
		//System.out.println("APPEL A EQUILIBRE_______________________________");
        for(int i=0;i<tab.length;i++){
			//System.out.println("-----------i : "+i);
			//System.out.println("proportions");
			//printTab(proportions);
			//System.out.println("\n");
			float fi=(float)proportions[i];
			//System.out.println("fi: "+fi);
			float card=(float)(nInit);
			//System.out.println("card:" + card);
			float frequence=fi/card;
			//System.out.println("frequence: "+ frequence);
			float frequenceAttendue=frequence*(k+1);


			float si=tab[i];
			
			//System.out.println("si : "+ (si));
			//System.out.println("(frequenceAttendue-1) : "+ (frequenceAttendue -1));
			//System.out.println("(frequenceAttendue+1) : "+ (frequenceAttendue +1));

			
			
            if(si<= frequenceAttendue-1 || si>= frequenceAttendue +1){
				//System.out.println("C'est Faux à l'indice :"+i);


				

                return false;
            }
        }
        return true;
    }

	private static double freqActuelle(int i){
        return quantite[i]/n;
    }

    public static int estTropBasse(){
        double min = 99999; 
        int sol = 0;
        for(int i = 0; i < m; i++){

			
			float fi=(float)proportions[i];
			float card=(float)(nInit);
			float frequence=fi/card;
			float frequenceAttendue=frequence*(k+1);


			float si=quantite[i];
			float diff=si-frequenceAttendue;

			if( diff < min){
                min = diff;
				
                sol = i;
            } 
        }
        return sol;
    }

	public static int ajoutTuile(){
		int limite =n-kInit;
		for(int i=0; i<limite;i++){
			int ajout=estTropBasse();
			//System.out.println("ajout: "+ajout);
			int []copie=copie(quantite);
			copie[ajout]=copie[ajout]+1;
			//System.out.println("copie");

			//printTab(copie);
			//System.out.println("\n");

			if(!estEquilibre(copie)){	
				//System.out.println("ici");			
				return k-kInit;
			}else{
				//System.out.println("là");			
				
				//System.out.println("k"+k);
				quantite[ajout]=quantite[ajout]+1;
				mosaique.add(ajout+1);
				k++;
				n++;
			}
		}
		return -1;
	}



	public static int[] copie(int[]tab){
        int taille =tab.length;
        int []copie=new int[taille];
        for(int i=0;i<taille;i++){
            copie[i]=tab[i];
        }
        return copie;
    }

    
}