package Arvore.AVL;

import Arvore.Binária.Elemento;

public class ArvoreAVL {
    private Elemento ele;
    private ArvoreAVL dir;
    private ArvoreAVL esq;
    private int bal;

    public ArvoreAVL() {
        this.ele = null;
        this.esq = null;
        this.dir = null;
        this.bal = 0;
    }

    public ArvoreAVL(Elemento elem) {
        this.ele = elem;
        this.dir = null;
        this.esq = null;
        this.bal = 0;
        // System.out.println("Criei a arvore com o elemento " + elem.getValor());
    }

    public int calcularAltura() {
        if (this.esq == null && this.dir == null) { // não tenho nenhum filho
            return 1;
        } else if (this.esq != null && this.dir == null) {
            return 1 + this.esq.calcularAltura();
        } else if (this.esq == null && this.dir != null) {
            return 1 + this.dir.calcularAltura();
        } else {
            return 1 + Math.max(this.esq.calcularAltura(), this.dir.calcularAltura());
        }
    }

    public void calcularBalanceamento() {
        if (this.dir == null && this.esq == null) {
            this.bal = 0;
        } else if (this.esq == null && this.dir != null) {
            this.bal = this.dir.calcularAltura() - 0;
        } else if (this.esq != null && this.dir == null) {
            this.bal = 0 - this.esq.calcularAltura();
        } else {
            this.bal = this.dir.calcularAltura() - this.esq.calcularAltura();
        }
        if (this.dir != null) this.dir.calcularBalanceamento();
        if (this.esq != null) this.esq.calcularBalanceamento();
    }

    public ArvoreAVL verificaBalanceamento() {
        if (this.bal >= 2 || this.bal <= -2) {
            if (this.bal >= 2) {
                if (this.dir.getBalanceamento() >= 0) { // Linha alterada: `>=` para `>=`
                    System.out.println("Rotacao Simples Direita");
                    return rotacaoSimplesDireita();
                } else {
                    System.out.println("Rotacao Dupla Direita");
                    return rotacaoDuplaDireita();
                }
            } else { // bal <= -2
                if (this.esq.getBalanceamento() <= 0) { // Linha alterada: `<=` para `<=`
                    System.out.println("Rotacao Simples Esquerda");
                    return rotacaoSimplesEsquerda();
                } else {
                    System.out.println("Rotacao Dupla Esquerda");
                    return rotacaoDuplaEsquerda();
                }
            }
        }
        // Recalcular o balanceamento após as rotações
        if (this.esq != null) this.esq = this.esq.verificaBalanceamento();
        if (this.dir != null) this.dir = this.dir.verificaBalanceamento();
        calcularBalanceamento(); // Linha adicionada: Atualizar o balanceamento da árvore atual
        return this;
    }

    public ArvoreAVL rotacaoSimplesDireita() {
        ArvoreAVL filhoDir;
        ArvoreAVL filhoDoFilho = null;

        filhoDir = this.getDireita();
        if (this.dir != null) {
            if (this.dir.getEsquerda() != null) {
                filhoDoFilho = filhoDir.getEsquerda();
            }
        }
        filhoDir.setEsquerda(this);
        this.setDireita(filhoDoFilho);

        // Atualizar o balanceamento das subárvores
        this.calcularBalanceamento();
        filhoDir.calcularBalanceamento();

        return filhoDir;
    }

    public ArvoreAVL rotacaoSimplesEsquerda() {
        ArvoreAVL filhoEsq;
        ArvoreAVL filhoDoFilho = null;

        filhoEsq = this.getEsquerda();
        if (this.esq != null) {
            if (this.esq.getDireita() != null) {
                filhoDoFilho = filhoEsq.getDireita();
            }
        }
        filhoEsq.setDireita(this);
        this.setEsquerda(filhoDoFilho);

        // Atualizar o balanceamento das subárvores
        this.calcularBalanceamento();
        filhoEsq.calcularBalanceamento();

        return filhoEsq;
    }

    public ArvoreAVL rotacaoDuplaDireita() {
        ArvoreAVL filhoDir = this.getDireita();
        ArvoreAVL filhoDoFilho = filhoDir.getEsquerda();
        ArvoreAVL noInserido = filhoDoFilho.getDireita();

        // parte 1: alinhar os caras
        filhoDir.setEsquerda(noInserido);
        filhoDoFilho.setDireita(filhoDir);
        this.setDireita(filhoDoFilho);

        // parte 2: tornar o filho à direita a nova raiz
        ArvoreAVL novoFilhoDireita = this.getDireita();
        this.setDireita(null); // Linha alterada: Corrigir `arvore.setDireita(null);` para `this.setDireita(null);`
        novoFilhoDireita.setEsquerda(this);

        // Atualizar o balanceamento das subárvores
        this.calcularBalanceamento();
        filhoDir.calcularBalanceamento();
        filhoDoFilho.calcularBalanceamento();

        return filhoDoFilho;
    }

    public ArvoreAVL rotacaoDuplaEsquerda() {
        ArvoreAVL filhoEsq = this.getEsquerda();
        ArvoreAVL filhoDoFilho = filhoEsq.getDireita();
        ArvoreAVL noInserido = filhoDoFilho.getEsquerda();

        // parte 1: alinhar os caras
        filhoEsq.setDireita(noInserido);
        filhoDoFilho.setEsquerda(filhoEsq);
        this.setEsquerda(filhoDoFilho);

        // parte 2: tornar o filho à esquerda a nova raiz
        ArvoreAVL novoFilhoEsquerda = this.getEsquerda();
        this.setEsquerda(null); // Linha alterada: Corrigir `arvore.setEsquerda(null);` para `this.setEsquerda(null);`
        novoFilhoEsquerda.setDireita(this);

        // Atualizar o balanceamento das subárvores
        this.calcularBalanceamento();
        filhoEsq.calcularBalanceamento();
        filhoDoFilho.calcularBalanceamento();

        return filhoDoFilho;
    }

    public boolean isEmpty() {
        return (this.ele == null);
    }

    public void imprimirPreOrdem() {
        if (!isEmpty()) {
            System.out.print(this.ele.getValor() + " ");
            if (this.esq != null) {
                this.esq.imprimirPreOrdem();
            }
            if (this.dir != null) {
                this.dir.imprimirPreOrdem();
            }
        }
    }

    public void imprimirInOrdem() {
        if (!isEmpty()) {
            if (this.esq != null) {
                this.esq.imprimirInOrdem();
            }
            System.out.print(this.ele.getValor() + " ");
            if (this.dir != null) {
                this.dir.imprimirInOrdem();
            }
        }
    }

    public void imprimirPosOrdem() {
        if (!isEmpty()) {
            if (this.dir != null) {
                this.dir.imprimirPosOrdem();
            }
            if (this.esq != null) {
                this.esq.imprimirPosOrdem();
            }
            System.out.print(this.ele.getValor() + " ");
        }
    }

    public ArvoreAVL inserir(Elemento novo) {
        if (isEmpty()) {
            this.ele = novo;
        } else {
            if (novo.getValor() < this.ele.getValor()) {
                if (this.esq == null) {
                    this.esq = new ArvoreAVL(novo);
                } else {
                    this.esq = this.esq.inserir(novo);
                }
            } else if (novo.getValor() > this.ele.getValor()) {
                if (this.dir == null) {
                    this.dir = new ArvoreAVL(novo);
                } else {
                    this.dir = this.dir.inserir(novo);
                }
            }
        }
        calcularBalanceamento(); // Linha adicionada: Atualizar o balanceamento da árvore atual
        return verificaBalanceamento(); // Linha adicionada: Ajustar o balanceamento
    }

    public ArvoreAVL remover(int elemento) {
        if (this.ele.getValor() == elemento) {
            if (this.esq == null && this.dir == null) {
                return null;
            }
            if (this.esq == null) {
                return this.dir;
            } else if (this.dir == null) {
                return this.esq;
            } else {
                ArvoreAVL substituto = this.dir;
                while (substituto.esq != null) {
                    substituto = substituto.esq;
                }
                this.setElemento(substituto.getElemento());
                this.dir = this.dir.remover(substituto.getElemento().getValor());
            }
        } else if (elemento < this.ele.getValor()) {
            if (this.esq != null) {
                this.esq = this.esq.remover(elemento);
            }
        } else {
            if (this.dir != null) {
                this.dir = this.dir.remover(elemento);
            }
        }
        calcularBalanceamento(); // Linha adicionada: Atualizar o balanceamento da árvore atual
        return verificaBalanceamento(); // Linha adicionada: Ajustar o balanceamento
    }

    public boolean busca(int valor) {
        if (isEmpty()) {
            return false;
        }
        if (this.ele.getValor() == valor) {
            return true;
        } else {
            if (valor < this.ele.getValor()) {
                if (this.esq == null) {
                    return false;
                } else {
                    return this.esq.busca(valor);
                }
            } else if (valor > this.ele.getValor()) {
                if (this.dir == null) {
                    return false;
                } else {
                    return this.dir.busca(valor);
                }
            }
        }
        return false;
    }

    // gets e sets
    public void setElemento(Elemento ele) {
        this.ele = ele;
    }

    public void setDireita(ArvoreAVL dir) { // Linha alterada: Tipo de parâmetro corrigido para `ArvoreAVL`
        this.dir = dir;
    }

    public void setEsquerda(ArvoreAVL esq) { // Linha alterada: Tipo de parâmetro corrigido para `ArvoreAVL`
        this.esq = esq;
    }

    public int getBalanceamento() {
        return this.bal;
    }

    public void setBalanceamento(int bal) {
        this.bal = bal;
    }

    public ArvoreAVL getDireita() {
        return this.dir;
    }

    public ArvoreAVL getEsquerda() {
        return this.esq;
    }

    public Elemento getElemento() {
        return this.ele;
    }
}
