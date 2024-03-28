import java.util.ArrayList;

public class Zoe{
    private static int achete=0;
    private static int dispo=0;
    
    public static void phase1(){
        ArrayList<Disque> l =fonctionMaxime();
        int n=1;//récupérer la taille de la liste triée de maxime
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
}