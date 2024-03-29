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

public class Maxime {
    private static int nbrDisque;
    private static ArrayList<Disque> listDisque = new ArrayList<>();
    private static int dispo = 0;
    private static int achete = 0;

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

    public static ArrayList<Disque> separePhase1(ArrayList<Disque> d) {
        ArrayList<Disque> t = new ArrayList<>();
        boolean added = false;
        for (Disque disque : d) {
            added = false;
            if (disque.avant < disque.apres) {
                for (Disque disque2 : t) {
                    if (disque.avant <= disque2.avant) {
                        added = true;
                        t.add((t.indexOf(disque2)), disque);
                        break;
                    }
                }
                if (!added)
                    t.add(disque);
            }
        }
        for (Disque disque : t) {
            d.remove(disque);
        }
        return t;
    }

    public static void phase1(){
        ArrayList<Disque> l = separePhase1(listDisque);
        for (Disque disque : l) {
            if(dispo<disque.avant){
                achete += disque.avant-dispo;
                dispo = disque.apres;
            }
            else{
                dispo+=disque.apres-disque.avant;
            }
        }
    }

    public static ArrayList<Disque> separePhase2(ArrayList<Disque> d) {
        ArrayList<Disque> t = new ArrayList<>();
        for (Disque disque : d) {
            if (disque.avant == disque.apres) {
                t.add(disque);
            }
        }
        for (Disque disque : t) {
            d.remove(disque);
        }
        return t;
    }

    public static void phase2( ArrayList<Disque> l){
        for (Disque disque : l) {
            if(dispo < disque.avant){
                achete+=disque.avant-dispo;
                dispo=disque.apres;
            }
        }
    }

    
    public static ArrayList<Disque> triPhase3B(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        boolean added = false;
        for (Disque disque : d) {
            added = false;
            for (Disque disque2 : t) {
                if (disque.apres >= disque2.apres) {
                    added = true;
                    t.add(t.indexOf(disque2), disque);
                    break;
                }
            }
            if (!added)
                t.add(disque);
        }
        return t;
    }


    public static ArrayList<Disque> triPhase3D(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        boolean added = false;
        for (Disque disque : d) {
            added = false;
            for (Disque disque2 : t) {
                if (disque.apres-disque.avant >= disque2.apres-disque2.avant) {
                    added = true;
                    t.add(t.indexOf(disque2), disque);
                    break;
                }
            }
            if (!added)
                t.add(disque);
        }
        return t;
    }

    public static void phase3( ArrayList<Disque> l){
        for (Disque disque : l) {
            if(dispo < disque.avant){
                achete+=disque.avant-dispo;
                dispo=disque.apres;
            }
            else{
                dispo += disque.apres-disque.avant;
            }
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

        
        phase1();


        ArrayList<Disque> l=separePhase2(listDisque);
        
        phase2(l);

        ArrayList<Disque> l2=triPhase3B(listDisque);

        phase3(l2);

        System.out.println(achete);
	}  
}
