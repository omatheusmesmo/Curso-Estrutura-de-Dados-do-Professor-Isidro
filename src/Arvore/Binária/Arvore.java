package Arvore.Binária;

public class Arvore {
    private Elemento ele;
    private Arvore dir;
    private Arvore esq;

    public Arvore(){
        this.ele = null;
        this.esq = null;
        this.dir = null;
    }

    public Arvore(Elemento elem){
        this.ele = elem;
        this.dir = null;
        this.esq = null;
        //System.out.println("Criei a arvore com o elemento"+elem.getValor());
    }

    //remocao do no da arvore
    public Arvore remover(Elemento elem){
        //achei o primeiro o elemento
        if(this.ele.getValor()==elem.getValor()){
            //caso mais simples - o elemento está em um nó folha
            if(this.dir==null && this.esq == null){
                return null;
            }
            else{
                //caso 2 - eu tenho filhos à esquerda, porem não à direita
                if (this.esq != null && this.dir == null){
                    return this.esq;
                }
                //caso 3 - eu tenho filhos à direita, porém não tenho à esquerda
                else if(this.dir != null && this.esq==null){
                    return this.dir;
                }
                // caso 4 - tenho filhos dos dois lados (esquerda e direita)
                else {
                    //maior dentre os menores
                    Arvore aux = this.esq;
                    while(aux.dir !=null){
                        aux = aux.dir;
                    }
                    //trocar os elementos da árvore
                    this.ele = aux.getElemento(); //o nó atual recebe o elemento do aux
                    //o maior dentre os menores
                    aux.setElemento(elem); //insiro no nó folha o elemento a ser eliminado
                    this.esq = esq.remover(elem);
                }
            }
        }

        else if(elem.getValor()<this.ele.getValor()){
            //delegar a responsabilidade a sub-arvore da esquerda
            this.esq = this.esq.remover(elem);
        }else if(elem.getValor()>this.ele.getValor()){
            //delegar a resp. a sub-arvore da direita
            this.dir = this.dir.remover(elem);
        }
        return this;
    }

    //metodos de controle

    public boolean isEmpty(){
        return (this.ele == null);
    }

    public void imprimirPreOrdem(){
        if(!isEmpty()){
            System.out.print(this.ele.getValor()+" ");
            if(this.esq != null) {
                this.esq.imprimirPreOrdem();
            }
            if(this.dir != null){
                this.dir.imprimirPreOrdem();
            }
        }
    }

    public void imprimirInOrdem(){
        if(!isEmpty()){
            if(this.esq != null){
                this.esq.imprimirInOrdem();
            }
            System.out.print(this.ele.getValor() + " ");
            if(this.dir != null){
                this.imprimirInOrdem();
            }
        }
    }

    public void imprimirPosOrdem(){
        if(!isEmpty()){
            if (this.dir!=null){
                this.dir.imprimirPosOrdem();
            }
            if(this.esq != null){
                this.esq.imprimirPosOrdem();
            }
            System.out.print(this.ele.getValor()+ " ");
        }
    }

    public void inserir(Elemento novo){
        if (isEmpty()){
            this.ele = novo;
        }
        else{
            Arvore novaArvore = new Arvore(novo);
            if(novo.getValor()<this.ele.getValor()){
                //vou inserir na descendencia esquerda
                if(this.esq == null){//sou só um nó na folha
                    this.esq = novaArvore;
                    //System.out.print("Inseri o elemento "+novo.getValor() + "à esquerda de "+ this.ele.getValor());
                }else {
                    this.esq.inserir(novo);//repassei a resp pra sub-arvore esquerda
                }
            } else if (novo.getValor()>this.ele.getValor()) {//vou inserir na direita
                if(this.dir==null){
                    this.dir = novaArvore;
                    //System.out.println("Inseri o elemento "+novo.getValor() + "à direita de "+ this.ele.getValor());
                }
                else{
                    this.dir.inserir(novo);
                }

            }
        }
    }

    public boolean busca(int valor){
        if(isEmpty()){
            return false;
        }
        if (this.ele.getValor() == valor){
            return true;
        }
        else{
            if(valor<this.ele.getValor()){
                if (this.esq == null){
                    return false;
                }
                else {
                    return this.esq.busca(valor);//repasse a responsabilidade para a sub arvore
                }
            } else if (valor >this.ele.getValor()) {
                if(this.dir==null){
                    return false;
                }
                else{
                    return this.dir.busca(valor);
                }
            }
            return false;
        }
    }

    //get e sets
    public void setElemento(Elemento ele){
        this.ele = ele;
    }
    public void setDir(Arvore dir){
        this.dir = dir;
    }
    public void setEsq(Arvore esq){
        this.esq = esq;
    }

    public Arvore getDireita(){
        return this.dir;
    }
    public Arvore getEsquerda(){
        return this.esq;
    }
    public Elemento getElemento(){
        return this.ele;
    }
}
