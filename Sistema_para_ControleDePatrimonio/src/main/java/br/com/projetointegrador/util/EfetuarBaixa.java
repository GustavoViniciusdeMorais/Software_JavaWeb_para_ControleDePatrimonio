package br.com.projetointegrador.util;

import java.io.Serializable;

import br.com.projetointegrador.model.bem.Bem;

public class EfetuarBaixa implements Serializable {

	private static final long serialVersionUID = 1L;

	// CalcularDepreciacao objCalDep = new CalcularDepreciacao();
	/*
	 * double taxa = objCalDep.calculaTaxa(b); double vr =
	 * objCalDep.calculaValorResidual(b); double da = objCalDep.calculaDA(b);
	 * double vc = objCalDep.calculaValorContabil(b); vv = b.getValorDeVenda();
	 */
	public double ganhoPerda(Bem b) {
		double gp = 0, vv = 0;
		double vc = b.getValorContabil();
		vv = b.getValorDeVenda();
		gp = vv - vc;
		System.out.println("vv: "+vv);
		System.out.println("vc: "+vc);
		System.out.println("gp: "+gp);
		return gp;
	}

}
