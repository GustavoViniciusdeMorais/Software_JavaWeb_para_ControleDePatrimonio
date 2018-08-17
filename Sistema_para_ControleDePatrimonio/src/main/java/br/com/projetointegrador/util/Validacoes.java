package br.com.projetointegrador.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.projetointegrador.model.bem.Bem;

public class Validacoes implements Serializable {

	private static final long serialVersionUID = 1L;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public boolean validarDataBaixa(Bem objB) {
		boolean x = false;
		if (objB.getDtAquisicao().getTime() < objB.getDtVenda().getTime()) {
			x = true;
		} else {
			x = false;
		}
		System.out.println("dtaq: " + objB.getDtAquisicao());
		System.out.println("dtvend: " + objB.getDtVenda());
		return x;
	}

	public boolean validarNumDoub(String valor) {
		boolean valido = true;
		if (!valor.matches("\\d{0,9}\\.\\d{0,9}") && !valor.matches("\\d{0,9}")) {
			valido = false;
		}
		if (valido) {
			valido = true;
		} else {
		}
		return valido;
	}

	public boolean validarNumInt(String valor) {
		boolean valido = true;
		if (!valor.matches("\\d{0,9}")) {
			valido = false;
		}
		if (valido) {
			// JOptionPane.showMessageDialog(null, " Dados salvos !!! ");
			valido = true;
		} else {
		}
		return valido;
	}

	//@Test
	public boolean validaData(String data) {
		String padrao = ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");
		String texto = "02/02/2010";
		boolean x = false;
		if (texto.matches(padrao) == false) {
			System.out.println("invalido " + texto);
			x = false;
		} else {
			System.out.println("valido " + texto);
			x = true;
		}
		return x;
	}

}
