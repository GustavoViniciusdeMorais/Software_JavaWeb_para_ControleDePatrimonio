package br.com.projetointegrador.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.projetointegrador.model.tipoDeBem.TipoDeBem;
import br.com.projetointegrador.model.tipoDeBem.TipoDeBemRN;

@ManagedBean(name = "MBTipo")
@SessionScoped
public class TipoDeBemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	TipoDeBem tipo = new TipoDeBem();

	public String actionNovo() {
		tipo = new TipoDeBem();
		return "formulario_tipoDeBem";
	}

	public String actionGravar() {
		new TipoDeBemRN().salvar(tipo);
		return "lista_TipoDeBem.xhtml?faces-redirect=true";
	}

	public List<TipoDeBem> getListagem() {
		return new TipoDeBemRN().listarTodos();
	}
	
	public String actionApagar() {
		new TipoDeBemRN().apagar(tipo);
		return "lista_TipoDeBem.xhtml?faces-redirect=true";
	}
	
	public TipoDeBem getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeBem tipo) {
		this.tipo = tipo;
	}

}
