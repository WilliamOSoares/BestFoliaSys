//Best Folia Sys//
/*******************************************************************************
Autor: William Oliveira Soares
Componente Curricular: MI Programação
Concluido em: 27/04/2018
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
***************************************************************************************/
package br.uefs.ecomp.bfs.util;

//Todo codigo dessa classe e das classes internas foi feito com ajuda do slide 
//e da aula do discente da UEFS João Batista Rocha Junior;
//fonte: sites.ecomp.uefs.br/joao/
public class ListaEncadeada implements ILista{
    
    private Celula primeira;
    private Celula ultima;
    private int total = 0;
    // Foi utilizado uma referencia para o final da lista e uma varialvel
    // para contar quantas células a na lista, no intuito de reduzir a
    // complexidade dos métodos da lista;
    
    private class Celula {
        private Object conteudo;
        private Celula proximo;
    
        public Celula(Object conteudo){
            this.conteudo = conteudo;
        }
        public Celula getProximo(){
            return proximo;
        }
        public void setProximo(Celula proximo){
            this.proximo = proximo;
        }
        public Object getConteudo(){
            return conteudo;
        }
        public void setConteudo(Object conteudo){
            this.conteudo = conteudo;
        }
    }

    @Override
    public boolean estaVazia() {
        return this.total==0;        
    }

    @Override
    public int tamanho() {
        return total;
    }
    
    @Override
    public void insereInicio(Object o) {
        Celula aux = new Celula(o);
        if(this.estaVazia()){
            this.primeira = aux;
            this.ultima = this.primeira;
        } else{
            aux.setProximo(this.primeira);
            this.primeira = aux;
        }
        this.total++;
    }

    @Override
    public void insereFinal(Object o) {
        if(this.estaVazia()){
            this.insereInicio(o);
        } else{
            Celula aux = new Celula(o);
            this.ultima.setProximo(aux);
            this.ultima = aux;
            this.total++;
        }
    }

    @Override
    public Object removeInicio() {
        Celula aux = this.primeira;
        if(this.estaVazia()){
            return null;
        } else{
            this.primeira = this.primeira.getProximo();
            this.total--;        
        }
        if(this.estaVazia()){
            this.ultima = this.primeira;
        }
        return aux.conteudo;
    }

    @Override
    public Object removeUltimo() {
        Celula removido = this.ultima;
        if(this.estaVazia()){
            return null;
        } else if(this.total==1){
            removeInicio();
        } else{
            Celula aux = this.primeira;
            boolean flag = true;
            while(flag){
                if(aux.getProximo() == this.ultima){
                    this.ultima = aux;
                    this.ultima.setProximo(null);
                    flag = false;
                } else{
                    aux = aux.getProximo();
                }
            }    
            this.total--;       
        }
        return removido.conteudo;
    }

    @Override
    public Object recupera(int index) {
        if (index >= 0 && index< total){
            Celula n = primeira;
            for (int i = 0; i < index; i++) {
                n = n.getProximo();
            }
            return n.getConteudo();
        }
        return null;
    }
    
    private class Iterator implements Iterador{
        private int cursor;
        
        public Iterator() {
            this.cursor = 0;
        }
        @Override
        public boolean temProximo(){
            return cursor < total;
        }
        @Override
        public Object proximo(){
            Object aux = recupera(cursor);
            cursor++;
            return aux;
        }
    }
    
    @Override
    public Iterador iterador() {
        return new Iterator();
    }

}
