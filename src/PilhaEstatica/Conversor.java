package PilhaEstatica;

public class Conversor {
    public static void main(String args[]){
        int numero = Integer.parseInt(args[0]);
        Pilha p = new Pilha();

        int resto;

        //fase 1 - empilhamento de restos
        while (numero !=0){
            resto = numero %2; //armazena o resto da divisão
            p.push(resto); //armazena na pilha
            numero = numero/2;
        }

        //fase 2 - exibição dos restos
        while (!p.isEmpty()){
            resto = p.pop();
            System.out.print(resto);
        }
        System.out.print("\nFim do Programa.");

    }
}
// (172)10 = (10101100)2
/*
Um exemplo das divisoes suscessivas
172 / 2
0 86 / 2
0 43 / 2
1 21 / 2
1 10 / 2
0 5 / 2
1 2 / 2
0 1 / 2
1 0
*/
