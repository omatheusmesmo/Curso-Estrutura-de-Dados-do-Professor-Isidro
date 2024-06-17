package ListasEncadeadas;

public class Lista {

    private Caixinha inicio;

    public Lista(){
        this.inicio = null;
    }

    public void inserir(int elemento){
        Caixinha nova = new Caixinha();
        nova.setElemento(elemento);
        nova.setProximo(null);

        if(inicio == null) {
            inicio = nova;
        }else{
            Caixinha aux;
            aux = inicio;
            while (aux.getProximo()!=null){
                aux.getProximo();
            }
            aux.setProximo(nova);
        }
    }

    public int retirar(){
        if(inicio != null){
            Caixinha aux = inicio;
            int elemento = aux.getElemento();
            inicio = aux.getProximo();
            return elemento;
        }else {
            throw new RuntimeException("Lista vazia!");
        }
    }

    public void listar(){
        if (inicio == null){
            System.out.println("Nada a exibir - a lista está vazia!");
        }
        else{
            Caixinha aux = inicio;
            while (aux !=null){
                System.out.println("Elemento visto"+ aux.getElemento());
                aux = aux.getProximo();
            }
        }
    }

    public boolean isEmpty(){
        return this.inicio == null;
    }
}
