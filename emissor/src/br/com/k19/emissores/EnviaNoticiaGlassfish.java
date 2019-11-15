package br.com.k19.emissores;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class EnviaNoticiaGlassfish{
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		
		props.setProperty("java.naming.factory.initial",
				"com.sun.enterprise.naming.SerialInitContextFactory");
		
		props.setProperty("java.naming.factory.url.pkgs",
				"com.sun.enterprise.naming");
		
		props.setProperty("java.naming.factory.state",
				"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
		
		InitialContext ic = new InitialContext(props);
		
		// Fábrica de conexões JMS
		ConnectionFactory factory = (ConnectionFactory) ic
				.lookup("jms/__defaultConnectionFactory");
		
		// Tópico
		Topic topic = (Topic) ic.lookup("jms/noticias");
		
		// contexto
		JMSContext context = factory.createContext();
		
		// produtor de mensagens
		JMSProducer producer = context.createProducer();
		
		// mensagem
		String mensagem = "Grazi Massafera foi ao shopping de chinelo";
		
		// enviando
		producer.send(topic, mensagem);
		
		System.out.println("MENSAGEM ENVIADA: " + mensagem);
		
		//fechando
		context.close();
	}
}
