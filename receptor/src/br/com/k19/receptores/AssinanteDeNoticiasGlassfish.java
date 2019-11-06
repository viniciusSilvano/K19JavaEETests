package br.com.k19.receptores;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class AssinanteDeNoticiasGlassfish {
	public static void main(String[] args) throws Exception{
		//Serviço de nomes - JNDI
		Properties props = new Properties();
		
		props.setProperty("java.naming.factory.initial",
				"com.sun.enterprise.naming.SerialInitContextFactory");
		
		props.setProperty("java.naming.factory.url.pkgs",
				"com.sun.enterprise.naming");
		
		props.setProperty("java.naming.factory.state",
				"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		
		InitialContext ic = new InitialContext(props);
		
		//fábrica de conexões de JMS
		ConnectionFactory factory =
				(ConnectionFactory) ic.lookup("jms/__defaultConnectionFactory");
		
		//tópico
		Topic topic = (Topic) ic.lookup("jms/noticias");
		
		//contexto
		JMSContext context = factory.createContext();
		
		//consumidor de mensagens
		JMSConsumer consumer = context.createConsumer(topic);
		
		//consumindo a mensagem
		String mensagem = consumer.receiveBody(String.class);
		
		System.out.println("MENSAGEM RECEBIDA: " + mensagem);
		
		//fechando
		context.close();
	}
}
