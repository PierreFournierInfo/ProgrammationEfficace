public class Maxime{
    
    public static int distancev2(int min, int max, int mot, int motindex){
        int t = tabSousChaines[motindex].length() + mot;
        if(mot < max && min < mot){
            if(t - max < 0)
                return 0;
            else
                return t - max;
        }
        if(mot > max)
            return t - max;
        else
            return min - mot;
    }

    public static int[] find(List<Integer>[] motif){
        int[] possibiliter = new int[4];
        for (int k = 0; k<motif[0].size(); k++) {
            int min = motif[0].get(k);
            int minindex = 0;
            int max = motif[0].get(k);
            int maxindex = 0;
            for (int i = 1; i < motif.length ; i++) {
                int dist = distancev2(min, max, motif[i].get(0));
                int distindex = 0;
                for(int j = 1; j < motif[i].size() && dist != 0; j++){
                    int t = distancev2(min,max,motif[i].get(j));
                    if(t<dist){
                        dist = t;
                        distindex = j;
                    }
                    else{
                        j = motif[i].size();
                    }
                }
                if(dist != 0){
                    if(dist < min){
                        min = distindex;
                        minindex = i;
                    }
                    else{
                        max = distindex;
                        maxindex = i;
                    }
                }
            }
            if(k == 0 || (max - min + tabSousChaines[maxindex].length()) < (possibiliter[2] - possibiliter[1] + tabSousChaines[possibiliter[3]].length())){
                possibiliter[0] = min;
                possibiliter[1] = minindex;
                possibiliter[2] = max;
                possibiliter[3] = maxindex;
            }
        }
        return possibiliter;
    }
}
