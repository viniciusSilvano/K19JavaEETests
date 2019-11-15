package br.com.k19.testes;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.k19.sessionbeans.LancadorDeDado;

public class TesteDeAcesso {
	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		
		LancadorDeDado lancadorDeDado = (LancadorDeDado) ic
				.lookup("java:global/dadoWeb/LancadorDeDadoBean");
		System.out.println(lancadorDeDado.lanca());
	}
}
