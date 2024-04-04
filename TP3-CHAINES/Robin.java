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

    public static int distance(int min, int max, int mot, int motindex){
        int t = tabSousChaines[motindex].length() + mot -1;
        if(mot < max && min < mot){
            return abs(max - min);
        }
        if(mot > max)
            return abs(t - min);
        else
            return abs(max - mot);
    }

    public static void solution(){
        String sol = "";
        int l = chaine.length();
        int deb = 0;
        int fin = 0;
        for(int i = 0; i < motif[0].size(); i++){
            int a = motif[0].get(i);
            int b = 0;
            int dist = chaine.length();
            for(int j = 0; j < motif[1].size(); j++){ 
                int mot = motif[1].get(j);
                if(abs(a-mot) < dist){
                    b = mot;     
                    dist = abs(a-b);
                }
            }
           
            if(a > b){
                int tmp = a;
                a = b;
                b = tmp;
            }
           
            for(int j = 2; j < motif.length; j++){
                for(Integer pos : motif[j]){
                    dist = distance(a, b, pos, j);
                    if(dist < l){
                        //System.out.println("a: " + a + " b: " + b + " pos: " + pos + " dist: " + dist);
                        l = dist;
                        if(pos > b){
                            fin = pos + tabSousChaines[j].length() - 1;
                            deb = a;
                        }
                        else {
                            fin = b;
                            deb = pos;
                        }
                        //System.out.println("deb: " + deb + " fin: " + fin);
                    }
                }
            }

        }
        sol = sousChaineMin(deb, fin);
        l = sol.length();
        System.out.println("(" + sol + ", " + l + ", " + deb + ")");
    }

   public static String sousChaineMin(int min, int max){
        String sol = "";
        for(int i = min; i <= max; i++){
            sol = sol + chaine.charAt(i);
        }
        String fin = "";
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
