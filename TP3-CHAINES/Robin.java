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
                if(chaine.length() - i  >= tabSousChaines[j].length()){
                    if(estPresent(tabSousChaines[j], i)){
                        motif[j].add(i);
                        break;
                    }
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

    public static int abs(int v){
        if(v < 0) return -v;
        return v;
    }

    public static int distancev2(int min, int max, int mot, int motindex){
        int t = tabSousChaines[motindex].length() + mot ;
        if(mot < max && min < mot){
            return abs(max - min);
        }
        if(mot > max)
            return abs(t - max);
        else
            return abs(max - mot);
    }

    public static void solution(){
        String sol = "";
        int pos = 0;
        int l = chaine.length();
        int max = 0;
        int min = 0;
        for(int i = 0; i < motif[0].size(); i++){
            int dist = 0;
            max = motif[0].get(i);
            min = 0;
            for(int j = 1; j < motif.length; j++){ 
                int mot = motif[j].get(0);
                if(j == 1){
                    if(mot > max){
                        min = max;
                        max = mot;
                    }else{
                        min = mot;
                    }
                }else{
                    if(mot > max)max = mot;
                    if(mot < min)min = mot;
                }
                dist = abs(max - min);  
                for(int y = 1; y < motif[j].size(); y++){
                    mot = motif[j].get(y);
                    if(j == 1){
                        if(abs(mot - min) < dist)max = mot;
                        continue;
                    }
                    if(dist > distancev2(min, max, mot, j)){
                        dist = distancev2(min, max, mot, j);
                        if(mot < min){
                            max = min;
                            min = mot;
                        }
                        if(mot > max){
                            min = max;
                            max = mot;
                        }
                    }
                }
            }
            if(l > dist)l = dist;
        }
        sol = sousChaineMin(min, max);
        l = sol.length();
        System.out.println("(" + sol + ", " + l + ", " + min + ")");
    }

   public static String sousChaineMin(int min, int max){
        String sol = "";
        for(int i = min; i < max; i++){
            sol = sol + chaine.charAt(i);
        }
        String fin = "";
        for(int i = 0; i < tabSousChaines.length; i++){
            if(estPresent(tabSousChaines[i], max)){
                fin = tabSousChaines[i];
            }
        }
        sol = sol + fin; 
        return sol;
    }

	public static void main(String[] args) {
		parse(args[0]);

		for ( String s : tabSousChaines ) {
			System.out.print(s + " ");
		}
		System.out.println("\n" + chaine);

        parcours();
        afficheMotif();
        solution();
	}


    
}
