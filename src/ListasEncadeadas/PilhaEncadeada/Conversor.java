package ListasEncadeadas.PilhaEncadeada;

public class Conversor {
    public static void main(String args[]){
        int numero = Integer.parseInt(args[0]);

        int resto;

        IPilha p;
        try {
            p = (IPilha)Class.forName(args[1]).newInstance();

            while (numero !=0){
                resto = numero %2;
                p.push(resto);
                numero = numero/2;
            }
            while (!p.isEmpty()){
                resto = p.pop();
                System.out.println(resto);
            }
            System.out.println("\nFim do Programa");
        }
        catch (Exception ex){
            System.out.println("Deu muito ruim...");
        }
    }
}

// (172)10 = (10101100)2

/*
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