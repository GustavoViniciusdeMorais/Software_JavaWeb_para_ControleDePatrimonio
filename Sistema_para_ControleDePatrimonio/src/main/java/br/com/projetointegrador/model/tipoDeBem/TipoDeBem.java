package br.com.projetointegrador.model.tipoDeBem;

import java.io.Serializable;

public class TipoDeBem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String categoria;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
