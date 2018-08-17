package br.com.projetointegrador.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;

import br.com.projetointegrador.model.tipoDeBem.*;
import br.com.projetointegrador.model.bem.Bem;
import br.com.projetointegrador.model.bem.BemRN;
import br.com.projetointegrador.util.BemAuxiliar;
import br.com.projetointegrador.util.CalcularDepreciacao;
import br.com.projetointegrador.util.Validacoes;

@ManagedBean(name = "MBBem")
@ApplicationScoped
public class BemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private double gp;
	Validacoes objValidar = new Validacoes();
	CalcularDepreciacao objData = new CalcularDepreciacao();
	private String dataAtual;
	private String status;
	private Integer tipo;
	List<Bem> listaPorTipo = null;

	public BemBean() {
		this.gp = 0;
		this.dataAtual = objData.pegaDataAtual();
	}

	public List<TipoDeBem> getListagem() {
		return new TipoDeBemRN().listarTodos();
	}

	private BemAuxiliar aux = new BemAuxiliar();
	private Bem objB = new Bem();

	public Bem getObjB() {
		return objB;
	}

	public String actionNovo() {
		objB = new Bem();
		return "formulario_bem";
	}

	public String actionGravar() {
		new BemRN().salvar(objB);
		return "lista_bens?faces-redirect=true";
	}

	public List<Bem> getListadeBem() {
		return new BemRN().listarTodos();
	}

	public String actionBuscarPorTipo() {
		listaPorTipo = new ArrayList<Bem>();
		listaPorTipo = new BemRN().listarPorTipo(status, tipo);
		return "";
	}

	public String actionUpdate() {
		boolean x = new Validacoes().validarDataBaixa(objB);
		if (x) {
			new BemRN().atualizarBaixaNoBem(objB);
			this.gp = new BemRN().actionBaixa(objB);
		} else {
		}
		return "";
	}

	public String actionDepreciar() {
		objB = new BemRN().depreciarBem(objB);
		return "depreciacao";
	}

	public String actionEfetuarBaixa() {
		this.gp = 0;
		objB = new BemRN().depreciarBem(objB);
		return "form_efetuar_baixa?faces-redirect=true";
	}

	public String recalcularDepreciacao() {
		objB = new BemRN().recalcularDepreciacao(objB);
		return "";
	}

	public void setObjB(Bem objB) {
		this.objB = objB;
	}

	public BemAuxiliar getAux() {
		return aux;
	}

	public void setAux(BemAuxiliar aux) {
		this.aux = aux;
	}

	public double getGp() {
		return gp;
	}

	public void setGp(double gp) {
		this.gp = gp;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<Bem> getListaPorTipo() {
		return listaPorTipo;
	}

}
