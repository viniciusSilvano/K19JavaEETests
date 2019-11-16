package br.com.k19.testes;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.k19.sessionbeans.Carrinho;
import br.com.k19.sessionbeans.CarrinhoBean;

public class TesteCicloDeVidaSFSB {
	public static void main(String[] args) throws Exception{
		final Hashtable jndiProperties = new Hashtable();
		/*props.put(Context.INITIAL_CONTEXT_FACTORY, 
				"java:jboss/DefaultJMSConnectionFactory");
		props.put(Context.PROVIDER_URL, 
				"remote://127.0.0.1:4447");
		props.put(Context.SECURITY_PRINCIPAL, "k19");
		props.put(Context.SECURITY_CREDENTIALS, "1234");
		*/
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context ic = new InitialContext(jndiProperties);
		
		Carrinho[] carrinhos = new Carrinho[6];
		
		for (int i = 0; i < carrinhos.length; i++) {
			carrinhos[i] = (Carrinho)
					ic.lookup("ejb:carrinhoWeb/carrinhoWeb/" + CarrinhoBean.class.getSimpleName() + "!" + Carrinho.class.getName());
			carrinhos[i].adiciona("Chaveiro - K19");
			carrinhos[i].adiciona("Caneta - K19");
			Thread.sleep(1000);
		}
		
		carrinhos[0].adiciona("Borracha - K19");
		
		Thread.sleep(5000);
		
		carrinhos[0].finalizaCompra();
	}
}
