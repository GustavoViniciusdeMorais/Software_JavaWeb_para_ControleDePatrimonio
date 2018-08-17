package br.com.projetointegrador.model.bem;

import java.util.List;

import org.junit.Test;

import br.com.projetointegrador.model.tipoDeBem.TipoDeBem;
import br.com.projetointegrador.model.tipoDeBem.TipoDeBemDAO;
import br.com.projetointegrador.util.CalcularDepreciacao;
import br.com.projetointegrador.util.EfetuarBaixa;
import br.com.projetointegrador.util.RecalcularDepreciacao;

public class BemRN {

	public List<Bem> listarTodos() {
		return new BemDao().selectAll();
	}

	public List<Bem> listarPorTipo(String status, Integer tipo) {
		return new BemDao().selectByTipo(status, tipo);
	}

	public void atualizarBaixaNoBem(Bem objB) {
		if (objB.getStatus().toLowerCase().compareTo("inativo") == 0) {
		} else {
			new BemDao().updateBaixarOBem(objB);
		}
	}

	public void salvar(Bem objB) {
		boolean x = this.verificaSeTerreno(objB);
		//Se for terreno set 0 para vida util
		if(x){
			objB.setVidaUtil(0);
		}else{
		}
		if (objB.getId() == null) {
			new BemDao().insert(objB);
		} else {
			if (objB.getStatus().toLowerCase().compareTo("inativo") == 0) {
			} else {
				new BemDao().update(objB);
				// new ClasseExemploDAO().update(objB);
			}
		}
	}

	public Bem depreciarBem(Bem objB) {
		Bem b = new CalcularDepreciacao().algoritmoDepreciacao(objB);
		b.setVidaUtil(objB.getVidaUtil());
		b.setDtAquisicao(objB.getDtAquisicao());
		return b;
	}

	public double actionBaixa(Bem objB) {
		if (objB.getStatus().toLowerCase().compareTo("inativo") == 0) {
			return 0;
		} else {
			return new EfetuarBaixa().ganhoPerda(objB);
		}
	}

	public Bem recalcularDepreciacao(Bem objB) {
		Bem b = new RecalcularDepreciacao().algoritmoDepreciacao(objB);
		return b;
	}

	public boolean verificaSeTerreno(Bem objB) {
		List<TipoDeBem> listaTipos = null;
		listaTipos = new TipoDeBemDAO().selectAll();
		Long num = 0L;
		boolean x = false;
		for (TipoDeBem tipo : listaTipos) {
			// System.out.println("tipo: " + tipo.getCodigo() + " "+
			// tipo.getCategoria());
			if (tipo.getCategoria().toLowerCase().compareTo("terreno") == 0) {
				num = tipo.getCodigo();
			}
		}
		// System.out.println("codigo do terreno fora for: " + num);
		if (objB.getTipo().getCodigo() == num) {
			x = true;
			// System.out.println("o bem é um terreno");
		}
		return x;
	}

}
