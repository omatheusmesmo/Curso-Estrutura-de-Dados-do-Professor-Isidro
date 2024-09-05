import Arvore.AVL.ArvoreAVL;
import Arvore.Binária.Elemento;

import java.util.Scanner;
public class AppArvore{
    public static void main(String args[]){
        Scanner teclado = new Scanner(System.in);

        ArvoreAVL arvore = new ArvoreAVL(new Elemento(teclado.nextInt()));
        arvore.calcularBalanceamento();
        arvore = arvore.verificaBalanceamento();
        //System.out.println(arvore.print(0));

        while(true){
            arvore = arvore.inserir(new Elemento(teclado.nextInt()));
            arvore.calcularBalanceamento();
            arvore = arvore.verificaBalanceamento();
            //System.out.println(arvore.print(0));
        }

    }
}