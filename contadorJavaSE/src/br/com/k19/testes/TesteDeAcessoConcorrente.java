package br.com.k19.testes;

import java.util.Iterator;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.k19.sessionbeans.Contador;

public class TesteDeAcessoConcorrente {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, 
				"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		props.put(Context.SECURITY_PRINCIPAL, "k19Singleton");
		props.put(Context.SECURITY_CREDENTIALS, "singleton123#");
		
		//FIXME teste meu
		System.out.println(Context.STATE_FACTORIES);
		
		InitialContext ic = new InitialContext(props);
		
		final Contador contador = (Contador) ic
				.lookup("chatWeb/ContadorBean!br.com.k19.sessionbeans.Contador");
		
		final Thread[] threads = new Thread[20];
		
		System.out.println("Contador = " + contador.getValor());
		
		System.out.println("Incrementando " + threads.length * threads.length
				+ "vezes");
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(
					() -> {for (int j = 0; j < threads.length; j++) {
						contador.incrementa();
					}}
			);
			threads[i].start();
		}
		
		for(Thread thread : threads) {
			thread.join();
		}
		
		System.out.println("Contador = " + contador.getValor());
	}

}
