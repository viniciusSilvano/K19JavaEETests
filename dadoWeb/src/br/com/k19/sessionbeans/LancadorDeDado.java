package br.com.k19.sessionbeans;

import java.util.Map;
import java.util.concurrent.Future;

public interface LancadorDeDado {
	int lanca();
	String recuperarTeste();
	Future<Map<Integer,Integer>> calculaFrequencia();
}
