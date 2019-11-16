package br.com.k19.sessionbeans;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@Remote(Carrinho.class)
public class CarrinhoBean implements Carrinho {
	private Set<String> produtos = new HashSet<String>();
	private static int contadorTotal;
	private static int contadorAtivos;
	private int id;
	
	@Remove
	public void finalizaCompra() {
		System.out.println("Finalizando a compra");
	}
	
	@PostConstruct
	public void inicializando() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorTotal++;
			CarrinhoBean.contadorAtivos++;
			this.id = CarrinhoBean.contadorTotal;
			
			System.out.println("PostConstruct.");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos );
		}
	}
	
	@PrePassivate
	public void passivando() {
		synchronized(CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;
			
			System.out.println("PrePassivate");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos" + CarrinhoBean.contadorAtivos);
		}
	}
	
	@PostActivate
	public void ativando() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos++;
			
			System.out.println("PostActivate");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);
		}
	}
	
	@PreDestroy
	public void destruindo() {
		synchronized (CarrinhoBean.class) {
			CarrinhoBean.contadorAtivos--;
			CarrinhoBean.contadorTotal--;
			
			System.out.println("PostActivate");
			System.out.println("ID: " + this.id);
			System.out.println("Contador Total: " + CarrinhoBean.contadorTotal);
			System.out.println("Contador Ativos: " + CarrinhoBean.contadorAtivos);
		}
	}
			
	public void adiciona(String produto) {
		this.produtos.add(produto);
	}
	
	public void remove(String produto) {
		this.produtos.remove(produto);
	}
	
	public Set<String> getProdutos(){
		return produtos;
	}
}
