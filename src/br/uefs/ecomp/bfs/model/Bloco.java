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
package br.uefs.ecomp.bfs.model;

import br.uefs.ecomp.bfs.util.Iterador;
import br.uefs.ecomp.bfs.util.ListaEncadeada;
import java.util.Objects;

public class Bloco {
    private int id;
    private String nome;
    private String local;
    private int saida;
    private ListaEncadeada transportes = new ListaEncadeada();
    
    public Bloco(String nome, String local, int saida) {
        this.nome = nome;
        this.local = local;
        this.saida = saida;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getSaida() {
        return saida;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

    public ListaEncadeada getTransportes() {
        return transportes;
    }

    public void setTransportes(ListaEncadeada transportes) {
        this.transportes = transportes;
    }
    //Foi deixado todos os getters e setters caso Odal Odece use futuramente;

    
    //O metodo retora o iterador da lista encadeado da classe, este metodo foi
    //criado afim de reduzir em numero de linhas, porque com ele não é preciso um
    //getTransportes e nem criar o iterador do transportes dentro do medoto da 
    //classe controller;
    public Iterador getIteradorTransportes() {
        return this.transportes.iterador();
    }

    //este método foi feito afim de reduzir o codigo;
    public void addTransporte(Transporte transporte){
        this.transportes.insereFinal(transporte);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bloco other = (Bloco) obj;
        if (this.saida != other.saida) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        return true;
    }

}
