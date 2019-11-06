package br.com.k19.receptores;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RecebePedidoGlassfish {
	public static void main(String[] args) throws Exception {
		//serviço de nomes - JNDI
		Properties props = new Properties();
		
		props.setProperty("java.naming.factory.initial",
				"com.sun.enterprise.naming.SerialInitContextFactory");
		
		props.setProperty("java.naming.factory.url.pkgs",
				"com.sun.enterprise.naming");
		
		props.setProperty("java.naming.factory.state",
				"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		
		InitialContext ic = new InitialContext(props);
		
		//fábrica de conexões JMS
		ConnectionFactory factory = 
				(ConnectionFactory) ic.lookup("jms/__defaultConnectionFactory");
		
		//fila
		Queue queue = (Queue) ic.lookup("jms/pedidos");
		
		//contexto
		JMSContext context = factory.createContext();
		
		//consumidor de mensagens
		JMSConsumer consumer = context.createConsumer(queue);
		
		//consumindo a mensagem
		String mensagem = consumer.receiveBody(String.class);
		
		System.out.println("MENSAGEM RECEBIDA: " + mensagem);
		
		//fechando
		consumer.close();
		context.close();
	}
}
