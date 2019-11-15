package br.com.k19.testes;

import java.util.Map;
import java.util.concurrent.Future;

import javax.naming.InitialContext;

import br.com.k19.sessionbeans.LancadorDeDado;

public class TesteAsymchronous {
	public static void main(String[] args) throws Exception{
		InitialContext ic = new InitialContext();
		
		LancadorDeDado lancadorDeDado = (LancadorDeDado) ic
				.lookup("java:global/dadoWeb/LancadorDeDadoBean");
		Future<Map<Integer, Integer>> future = lancadorDeDado.calculaFrequencia();
		
		System.out.println("Aguardando");
		
		while(!future.isDone()) {
			System.out.println("*");
			Thread.sleep(1000);
		}
		System.out.println("\n" + future.get());
	}
}
