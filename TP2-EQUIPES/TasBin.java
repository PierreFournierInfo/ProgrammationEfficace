
public class TasBin{
    protected int taille;
    protected int [] tas;
    protected int [] dist;
    protected int pointeur;

    public TasBin(int taille, int[] dist){
        this.taille=taille;
        this.pointeur=0;
        this.dist = dist;
        this.tas=new int[taille];
    }

    //renvoie la position du parent
    private int getP(int i){
        return (i-1)/2;
    }

    //renvoie la position du fils gauche
    private int getL(int i){
        return 2*i+1;
    }

    //renvoie la position du fils droit
    private int getR(int i){
        return 2*i+2;
    }

    public boolean empty(){
        return pointeur < 1;
    }

    //insérer une valeur
    public void insert(int value){
        if(pointeur>=taille){
            return;
        }

        tas[pointeur]=value;
        int c=pointeur;

        while(dist[c]<dist[getP(c)]){
            inverse(c,getP(c));
            c=getP(c);
        }
        pointeur+=1;
    }

    //retire le minimum
    public int remove(){
        //System.out.println("poniteur : " + pointeur);
        int p=tas[0];
        
        tas[0]=tas[--pointeur];
        descend(0);
        return p;
    }

    //modifie la distance du sommet s par rapport à l'arbitre
    public void decreasekey(int s, int value){
        dist[s] = value;
        for(int i = 0; i < tas.length; i++){
            if(tas[i] == s)remonte(i);
        }
    }

    //fait remonte une valeur tant qu'elle est plus petite que sont parent 
    private void remonte(int i){
        if(i > 0){
            int pere = (i%2 == 0) ? i-2 : i-1;
            if(dist[tas[pere]] > dist[tas[i]]){
                inverse(pere, i);
                remonte(pere);
            }
        }
    }


    //inverse 2 valeurs
    private void inverse(int x, int y) {
		int tmp;
		tmp = tas[x];
		tas[x] = tas[y];
		tas[y] = tmp;
	}

    //fait descendre une valeur vers les feuilles
    private void descend(int index) {
        int minIndex = index;
        int lChild = getL(index);
        int rChild =getR(index);

        if (lChild < taille && dist[lChild] < dist[minIndex]) {
            minIndex = lChild;
        }

        if (rChild < taille && dist[rChild] < dist[minIndex]) {
            minIndex = rChild;
        }

        if (index != minIndex) {
            inverse(index, minIndex);
            descend(minIndex);
        }
    }



    //affiche le tas(pour debug)
    public void afficheTas() {
		for (int i = 0; i < (pointeur / 2); i++) {
			System.out.print("Parent : " + tas[i]);
			if (getL(i) < pointeur)
				System.out.print(" Left : " + tas[getL(i)]);
			if (getR(i) < pointeur)
				System.out.print(" Right :" + tas[getR(i)]);
			System.out.println();
		}
	}

}