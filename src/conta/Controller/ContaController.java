package conta.Controller;

import java.util.ArrayList;

import conta.Model.Conta;
import conta.Repository.ContaRepository;

public class ContaController implements ContaRepository {

    private ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;
    
    //busca conta por numero da conta
    @Override
    public void procurarPorNumero(int numero) {
        var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.visualizar();
		}else {
			System.out.println("\nA Conta número: " + numero + " não foi encontrada!");
		}
    }

    //Método Listar todas as Contas
    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
			conta.visualizar();
		}        
    }

    //Método Cadastrar no Conta
    @Override
    public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA Conta número: " + conta.getNumero() + " foi criada com sucesso!");
    }

    //Atualizar dados da Conta
    @Override
    public void atualizar(Conta conta) {
        var buscaConta = buscarNaCollection(conta.getNumero());
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
		}else {
			System.out.println("\nA Conta numero: " + conta.getNumero() + " não foi encontrada!");
		}
    }

    //Apagar Conta
    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);	
		if (conta != null) {
			if(listaContas.remove(conta) == true)
				System.out.println("\nA Conta numero: " + numero + " foi deletada com sucesso!");
		}else {
			System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");
		}
    }
    
   //método para sacar valores
    @Override
    public void sacar(int numero, float valor) {
        var conta = buscarNaCollection(numero);
        if (conta != null) {
        	if(conta.sacar(valor) == true) {
        		System.out.println("\nO saque da conta número: " + numero + 
        				" foi efetuado com sucesso");
        	}
        }else {
        	System.out.println("\nA conta número: " + numero + 
        			" não foi encontrada!");
        }
    }

    //método para fazer depósito
    @Override
    public void depositar(int numero, float valor) {
        var conta = buscarNaCollection(numero);
        if(conta != null) {
        	conta.depositar(valor);
        	System.out.println("\nO depósito da conta número: " + numero + 
        			"foi efetuado com sucesso!");
        }else {
        	System.out.println("\nA conta número: " + numero + 
        			" não foi encontrada ou a conta destino não é uma conta corrente!");
        }
        
    }

    //método para realizar transferência
    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        var contaOrigem = buscarNaCollection(numeroOrigem);
        var contaDestino = buscarNaCollection(numeroDestino);
        if(contaOrigem != null && contaDestino != null) {
        	contaDestino.depositar(valor);
        	System.out.println("\nA transferência foi efetuada com sucesso!");
        }else {
        	System.out.println("\nA conta de origem e/ou destino não foram encontradas!");
        }
        
    }

    
    //métodos de apoio
	
	//Método para gerar automaticamente o número da Conta
	public int gerarNumero() {
		return ++ numero;
	}

	//Método para buscar a Conta na Collection
	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

	//Método para retornar o Tipo da Conta
	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}
    
}