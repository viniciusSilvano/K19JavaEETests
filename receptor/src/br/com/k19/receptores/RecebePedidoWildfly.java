package br.com.k19.receptores;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RecebePedidoWildfly {
	public static void main(String[] args) throws Exception {
		//serviço de nomes - JNDI
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY
				,"org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		props.put(Context.SECURITY_PRINCIPAL, "k19");
		props.put(Context.SECURITY_CREDENTIALS, "1234");
		
		InitialContext ic = new InitialContext(props);
		
		// Fábrica de conexões JMS
		ConnectionFactory factory = (ConnectionFactory) 
				ic.lookup("jms/RemoteConnectionFactory");
		
		// fila
		Queue queue = (Queue) ic.lookup("jms/queue/pedidos");
		
		// contexto JMS
		JMSContext context = factory.createContext("k19","1234");
		
		//consumidor de mensagens
		JMSConsumer consumer = context.createConsumer(queue);
		
		//consumindo a mensagem
		String mensagem = consumer.receiveBody(String.class);
		
		System.out.println("MENSAGEM RECEBIDA: " + mensagem);
		
		//fechada
		consumer.close();
		context.close();
	}
}
