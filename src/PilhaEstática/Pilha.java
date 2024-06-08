package PilhaEstática;

public class Pilha {

    private int valores[];
    private int topo;

    //Construtor: pilha com 10 elementos
    public Pilha(){
        valores = new int[10];
        topo = -1;
    }

    //empilhar
    public void push(int elemento){
        topo = topo+1;
        valores[topo]=elemento;
    }

    // está vazia?
    public boolean isEmpty() {
        return (topo == -1);
    }
     //está cheia?
    public boolean isFull(){
        return(topo==9);
    }

    //desempilhar
    public int pop(){
        int elem=valores[topo];
        topo--;
        return elem;
    }
}
