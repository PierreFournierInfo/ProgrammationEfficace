import java.util.ArrayList;

public class Pierre {
    private static int achete;
    private static int dispo;
    private static int n;
    private static int[]a;
    private static int[]b;

    public static void main(String[]args){

    }

    public static int solution(){
        for(int i=0;i<a.length;i++){
            if (b[i]>a[i]){
                if(a[i]>dispo){
                    achete+=a[i];
                }
                dispo+=b[i];
            }
        }
        return achete;
    }
}
