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
package br.uefs.ecomp.bfs.controller;

import br.uefs.ecomp.bfs.model.Foliao;
import br.uefs.ecomp.bfs.model.Transporte;
import br.uefs.ecomp.bfs.model.Bloco;
import br.uefs.ecomp.bfs.util.ListaEncadeada;
import br.uefs.ecomp.bfs.util.Iterador; // importei

public class ControllerBFS {
    
    public ListaEncadeada blocos = new ListaEncadeada();
    public ListaEncadeada transportes = new ListaEncadeada();
    public ListaEncadeada folioes = new ListaEncadeada();
    /**************************************************************
     A lista de blocos é o banco de dados do sistema e as listas 
    transpostes e folioes são para reduzir a complexidade do código
    , sendo que a lista de folioes são para todos os folioes 
    cadastrados ou não no transporte.
    ***************************************************************/
    private int blocoId = 1; 
    private int transporteId = 1;
    //variaveis para gerar automaticamente o id//

    //O metodo cria o bloco e verifica se ja existe esse bloco no sistema, senão
    //existir o metodo adiciona na lista e retorna o bloco que acabou de ser cadastrado;
    public Bloco cadastrarBloco(String nome, String local, int saida){
        Bloco bloco = new Bloco(nome, local, saida);
        bloco.setId(this.blocoId);
        this.blocoId++;
        if(this.blocos.estaVazia()){
            this.blocos.insereInicio(bloco);
            return bloco;
        }
        Iterador itDoBloco = this.blocos.iterador();
        while(itDoBloco.temProximo()){ 
            Bloco blocoCompara = (Bloco) itDoBloco.proximo();
            if(blocoCompara.equals(bloco))
                return null;
        }
        this.blocos.insereInicio(bloco);
        return bloco;
    }

    //O metodo Percorre a lista de blocos até achar o bloco desejado, caso o id fornecido 
    //seja menor do que está sendo gerado;
    public Bloco obterBloco(int id){
        if(id >= this.blocoId)
            return null;
        Iterador it = this.blocos.iterador();
        while(it.temProximo()){
            Bloco procura = (Bloco) it.proximo();
            if(procura.getId()==id)
                return procura;
        }
        return null;
    }
    
    //O metodo percorre a lista blocos adiciona os blocos do local que foi 
    //fornecido pelo parametro em outro lista e ao fim ele retorna o iterador da lista criada;
    public Iterador listarBlocos(String local){ // Foram retirados: int dataInicio e int dataFim;
        ListaEncadeada blocosNesseLocal = new ListaEncadeada();
        Iterador itDoBloco =  this.blocos.iterador();
        while(itDoBloco.temProximo()){
            Bloco blocoCompara = (Bloco) itDoBloco.proximo();
            if(blocoCompara.getLocal().equals(local))
                blocosNesseLocal.insereInicio(blocoCompara);
        }
        return blocosNesseLocal.estaVazia()? null : blocosNesseLocal.iterador(); // if e else em uma unica linha;
    }
    
    //O metodo adiciona o transporte na lista transportes e no bloco que 
    //ele se refere, mas antes ele verifica se o objeto recibido já existe no sistema;
    public Transporte cadastrarTransporte(String nome, String tipo, double valor, int capacidade, int saida, 
                                          String localSaida, int retorno, String localRetorno, int chegada, 
                                          String localChegada, Bloco bloco){
        Transporte transporte = new Transporte(nome, tipo, valor, capacidade, saida, localSaida, retorno,
                                               localRetorno, chegada, localChegada, bloco);
        transporte.setId(this.transporteId);
        this.transporteId++;
        
        Iterador itTransporte = this.transportes.iterador();
        while(itTransporte.temProximo()){
            Transporte transporteCompara = (Transporte) itTransporte.proximo();
            if(transporteCompara.equals(transporte))
                return null;
        }       
        this.transportes.insereFinal(transporte);
        
        Iterador it = this.blocos.iterador();
        while(it.temProximo()){
            Bloco blocoCompara = (Bloco) it.proximo();
            if(blocoCompara.equals(bloco)) {
               blocoCompara.addTransporte(transporte);
            }
        }
        return transporte;
    }
    
    //O metodo Percorre a lista de transportes até achar o bloco desejado, caso o id fornecido 
    //seja menor do que está sendo gerado;
    public Transporte obterTransporte(int id) {
        if(id >= this.transporteId)
            return null;
        Iterador itDoTransporte = this.transportes.iterador();
        while(itDoTransporte.temProximo()){
            Transporte transporteProcura = (Transporte) itDoTransporte.proximo();
            if(transporteProcura.getId()==id)
                return transporteProcura;
        }
        return null;
    }
    
    //O metodo abaixo percorre a lista blocos até achar o bloco fornecido por parametro,
    //se achar retornar o iterador da lista de transporte do bloco, senão retorna nulo;
    public Iterador listarTransportes(Bloco bloco) {
        Iterador itDoBloco = this.blocos.iterador();
        while(itDoBloco.temProximo()){
            Bloco blocoCompara = (Bloco) itDoBloco.proximo();
            if(blocoCompara.equals(bloco)) {
               return blocoCompara.getIteradorTransportes();
            }
        }
        return null;
    }
    
    //O metodo cria o objeto e verifica se ja existe esse objeto no sistema, senão 
    //existir o metodo adiciona na lista e retorna o objeto que acabou de ser cadastrado;
    public Foliao cadastrarFoliao(String cpf, String rg, String nome, int idade) {
        Foliao foliao = new Foliao(cpf, rg, nome, idade);
        if(this.folioes.estaVazia()){
            this.folioes.insereInicio(foliao);
            return foliao;
        }
        Iterador itDoFoliao = this.folioes.iterador();
        while(itDoFoliao.temProximo()){
            Foliao foliaoCompara = (Foliao) itDoFoliao.proximo();
            if(foliaoCompara.equals(foliao)){
                return null;
            }
        }
        this.folioes.insereInicio(foliao);
	return foliao;
    }
    
    //O metodo Percorre a lista de folioes até achar o foliao desejado;
    public Foliao obterFoliao(String cpf) {
        Iterador itDoFoliao = this.folioes.iterador();
        while(itDoFoliao.temProximo()){
            Foliao foliaoProcura = (Foliao) itDoFoliao.proximo();
            if(cpf.equals(foliaoProcura.getCpf()))
                return foliaoProcura;
        }
        return null;
    }
    
    //O metodo registra o foliao no transporte, se o registro for efetuado o 
    //foliao recebe o transporte, senão é retornado null;
    //só é registrado se o folião não estiver em nenhum transporte;
    //o folião só recebe o transporte quando ele ja for inserido no transporte;
    //também é verificado se os objetos recebidos no parametro estão no banco de dados do programa;
    public boolean registrarFoliaoEmTransporte(Foliao foliao, Transporte transporte) {
        if(foliao.getTransporte() != null)
            return false;
        boolean flag = false;
        Iterador itTransporte = this.transportes.iterador();
        while(itTransporte.temProximo()){
            Transporte transporteCompara = (Transporte) itTransporte.proximo();
            if(transporteCompara.equals(transporte))
                flag = transporte.setFoliaoNoTransporte(foliao);
        }
        if(flag==true){
            Iterador itDoFoliao = this.folioes.iterador();
            while(itDoFoliao.temProximo()){
                Foliao foliaoProcura = (Foliao) itDoFoliao.proximo();
                if(foliao.equals(foliaoProcura))
                    foliaoProcura.setTransporte(transporte);
            }
            return flag;
        }
        return false;
    }
    
    //O metodo Percorre a lista de transportes até achar o transporte no qual o 
    //foliao faz parte e retorna o iterador dos folioes;
    public Iterador listarFolioes(Transporte transporte) {
	Iterador itDoTransporte = this.transportes.iterador();
        while(itDoTransporte.temProximo()){
            Transporte transporteProcura = (Transporte) itDoTransporte.proximo();
            if(transporteProcura.equals(transporte))
                return transporteProcura.getIteradorFoliao();
        }
        return null;
    }
}
