import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Zoe{
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

    public static ArrayList<Disque> separePhase1(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        for (int i=0; i<d.size(); i++) {
            if(d.get(i).avant < d.get(i).apres){
                t.add(d.get(i));
            }
        }
        Collections.sort(t, new Comparator<Disque>() {
            @Override
            public int compare(Disque d1, Disque d2){
                return Integer.compare(d1.avant,d2.avant);
            }
        });
        return t;
    }

    public static void phase1(){
        ArrayList<Disque> l = separePhase1(listDisque);
        int n = l.size();//récupérer la taille de la liste triée de maxime
        for(int i=0; i<n; i++){
            if(i==0){
                achete+=l.get(i).avant;
                dispo=l.get(i).apres;
            }else{
                if(dispo<l.get(i).avant){
                    achete+=l.get(i).avant-dispo;
                    dispo+=l.get(i).apres;
                }else{
                    dispo+=(l.get(i).apres-l.get(i).avant);
                }
            }
        }
    }

    public static ArrayList<Disque> separePhase2(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        for (int i=0; i<d.size(); i++) {
            if(d.get(i).avant > d.get(i).apres){
                t.add(d.get(i));
            }
        }
        //printList(t);
        Collections.sort(t, new Comparator<Disque>() {
            @Override
            public int compare(Disque d1, Disque d2){
                return Integer.compare(d1.avant,d2.avant);
            }
        });
        //printList(t);
        return t;
    }
    public static void phase3( ArrayList<Disque> l){
        int n = l.size();//récupérer la taille de la liste triée de maxime
        for(int i=0; i<n; i++){
            if(dispo<l.get(i).avant){
                achete+=l.get(i).avant-dispo;
                dispo+=l.get(i).apres;
            }else{
                dispo+=(l.get(i).apres-l.get(i).avant);
            }
        }
    }
    public static void phase2( ArrayList<Disque> l){
        int n = l.size();//récupérer la taille de la liste triée de maxime
        for(int i=n-1; i>=0; i--){
            if(dispo<l.get(i).avant){
                achete+=l.get(i).avant-dispo;
                dispo+=l.get(i).apres;
            }else{
                dispo+=(l.get(i).apres-l.get(i).avant);
            }
        }
    }

    public static ArrayList<Disque> separePhase3(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        for (int i=0; i<d.size(); i++) {
            if(d.get(i).avant == d.get(i).apres){
                t.add(d.get(i));
            }
        }
        //printList(t);
        Collections.sort(t, new Comparator<Disque>() {
            @Override
            public int compare(Disque d1, Disque d2){
                return Integer.compare(d1.avant,d2.avant);
            }
        });
        //printList(t);
        return t;
    }

    public static void printList(ArrayList<Disque> l){
        for ( Disque d : l ){
            System.out.println(d.avant + " " + d.apres);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        parse(args[0]);

        //printList(listDisque);
        
        phase1();

        //System.out.println(achete);
        //System.out.println(dispo);

        ArrayList<Disque> l=separePhase3(listDisque);
        
        phase2(separePhase3(listDisque));


        phase2(separePhase2(listDisque));

        System.out.println(achete);
        //System.out.println(dispo);
        
	}   
    

}