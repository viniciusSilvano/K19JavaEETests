package br.com.k19.emissores;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;

public class EnviaNovoPedidoGlassfish {
	public static void main(String[] args) throws Exception {
		// serviço de nomes - JNDI
		Properties props = new Properties();
		
		props.setProperty("java.naming.factory.initial"
				, "com.sun.enterprise.naming.SerialInitContextFactory");
		
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
		
		//contexto JMS
		JMSContext context = factory.createContext();
		
		//produtor de mensagens
		JMSProducer producer = context.createProducer();
		
		//mensagem
		String mensagem = "Pedido" + System.currentTimeMillis();
		
		//enviando
		producer.send(queue, mensagem);
		
		System.out.println("MENSAGEM ENVIADA: " + mensagem);
		
		//fechando
		context.close();
	}
}
