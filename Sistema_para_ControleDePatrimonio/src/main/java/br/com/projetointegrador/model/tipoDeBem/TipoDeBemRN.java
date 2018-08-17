package br.com.projetointegrador.model.tipoDeBem;

import java.util.List;

public class TipoDeBemRN {

	public void apagar(TipoDeBem tipo) {
		new TipoDeBemDAO().delete(tipo);
	}

	public List<TipoDeBem> listarTodos() {
		return new TipoDeBemDAO().selectAll();
	}

	public void salvar(TipoDeBem tipo) {
		if (tipo.getCodigo() == null) {
			new TipoDeBemDAO().insert(tipo);
		} else {
			new TipoDeBemDAO().update(tipo);
		}
	}

}
