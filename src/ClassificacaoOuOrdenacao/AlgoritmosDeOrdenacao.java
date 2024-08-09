package ClassificacaoOuOrdenacao;

public class AlgoritmosDeOrdenacao {

    private int vetor[] = {25, 57, 48, 37, 12, 92, 33};

    public void setVetor(int vetor[]){
        this.vetor = vetor;
    }
    public int[] getVetor(){
        return vetor;
    }

    public void bubbleSort(){
        int it;
        int pos;
        int temp;

        for(it=0; it<vetor.length-1;it++){
            for(pos=0;pos<vetor.length-it-1;pos++){
                if(vetor[pos]>vetor[pos+1]){
                    temp=vetor[pos];
                    vetor[pos]=vetor[pos+1];
                    vetor[pos+1]=temp;
                }
            }
        }
    }

    public void selectionSort(){
        int it, temp;
        int posMenorInicial;
        int posMenor;
        int i;

        for(it=0; it<vetor.length-1;it++){
            posMenorInicial=it;
            posMenor=it+1;

            for (i=posMenorInicial+1;i< vetor.length;i++){
                if(vetor[i]<vetor[posMenor]){
                    posMenor=i;
                }
            }

            if(vetor[posMenor]<vetor[posMenorInicial]){
                temp = vetor[posMenorInicial];
                vetor[posMenorInicial]=vetor[posMenor];
                vetor[posMenor] = temp;
            }
        }
    }

    public void insertionSort(){
        int carta, it, j;

        for(it=1;it<vetor.length;it++){
            carta = vetor[it];
            for(j=it-1;(j>=0)&&vetor[j]<carta;j--){
                vetor[j+1]=vetor[j];
            }
            vetor[j+1]=carta;
        }
    }

    public int partition(int inicio, int fim) {
        int ref, up, down, temp;

        ref = vetor[inicio];
        down = inicio;
        up = fim;

        while (down < up) {
            while (vetor[down]<= ref && down <fim){
                down++;
            }
            while (vetor[up]<ref){
                up--;
            }
            if(down<up){
                temp = vetor[down];
                vetor[down]=vetor[up];
                vetor[up]=temp;
            }
        }

        vetor[inicio]=vetor[up];
        vetor[up]=ref;

        return up;
    }

    public void quickSort(int inicio, int fim){
        int pivo;

        if(inicio>fim){
            return;
        }
        pivo=partition(inicio,fim);
        quickSort(inicio,pivo-1);
        quickSort(pivo+1,fim);
    }
}
