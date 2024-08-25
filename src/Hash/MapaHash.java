package Hash;

public class MapaHash {
    private Registro valores[];

    public MapaHash(){
        valores=new Registro[100];
    }

    public void put(Registro registro){
        int posicao = registro.hashCode();
        if(valores[posicao]==null){
            valores[posicao]=registro;
            //System.out.println("Posicao vazia! Novo registro.");
        }else {
            //System.out.println("Ops... colisão!");
            Registro reg = valores[posicao];

            //tratar alimento
            if (reg.getKey() == registro.getKey()) {
                reg.setValue(reg.getValue());
                //System.out.println("Primeiro registro existente, atualizando.");
                return;
            }

            while (reg.getProximo() != null) {
                if (reg.getKey() == registro.getKey()) {
                    reg.setValue(registro.getValue());
                    //System.out.println("Tá no meio da lista, já existente, atualizando.");
                    return;
                }
                reg = reg.getProximo();
            }

            // se ele é o último da lista
            //if (reg.getProximo() == null){
            if (reg.getKey() == registro.getKey()) {
                reg.setValue(registro.getValue());
                //System.out.println(“É o ultimo! Registro Existente – atualizando…”);

                return;
            }
            reg.setProximo(registro); // coloquei o novo registro na última posicao
            //System.out.println(“Novo Registro!”);
        }
    }

    public Registro get(int key){
        Registro r = new Registro();
        r.setKey(key);
        int posicao = r.hashCode();

        Registro resultado = valores[posicao];
        if (resultado != null && resultado.getKey()==key){
            return resultado;
        }else{
            while (resultado !=null){
                resultado = resultado.getProximo();
                if (resultado != null && resultado.getKey()==key){
                    return resultado;
                }
            }
        }
        return null;
    }
}
