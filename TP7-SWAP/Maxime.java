import java.util.ArrayList;

public class Maxime {
    public static ArrayList<Disque> separe(ArrayList<Disque> d){
        ArrayList<Disque> t = new ArrayList<>();
        for (Disque disque : d) {
            if(disque.avant < disque.apres){
                if(t.isEmpty()){
                    t.add(disque);
                }
                else{
                    for (Disque disque2 : t) {
                        if(disque.avant < disque2.avant){
                            t.add((t.indexOf(disque2)),disque);
                            break;
                        }
                    }
                }
                d.remove(disque);
            }
        }
        return t;
    }
}
