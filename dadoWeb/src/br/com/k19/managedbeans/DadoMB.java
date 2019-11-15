package br.com.k19.managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.k19.sessionbeans.LancadorDeDado;
import br.com.k19.sessionbeans.LancadorDeDadoBean;

@ManagedBean
public class DadoMB {
	@EJB
	private LancadorDeDado lancadorDeDadoBean;
	
	private int resultado;
	
	public void lancaDado() {
		this.resultado = this.lancadorDeDadoBean.lanca();
	}
	
	public int getResultado() {
		return resultado;
	}
	
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
}
