package br.com.projetointegrador.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.model.SelectItem;

public class BemAuxiliar implements Serializable {

	private static final long serialVersionUID = 1L;

	// private ArrayList<Integer> arrayVidaUtil = new ArrayList<Integer>();

	private Integer vidaUtil;

	private ArrayList<String> listaTurnos = new ArrayList<String>();

	public ArrayList<String> getListaTurnos() {
		return listaTurnos;
	}

	private ArrayList<String> listaStatus = new ArrayList<String>();

	public ArrayList<String> getLlistaStatus() {
		return listaStatus;
	}

	public BemAuxiliar() {
		listaTurnos.add("1 turno de 8h");
		listaTurnos.add("2 turnos de 8h");
		listaTurnos.add("3 turnos de 8h");
		listaTurnos.add("4 turnos de 8h");
		listaTurnos.add("5 turnos de 8h");
		listaStatus.add("Ativo");
		listaStatus.add("Inativo");
	}

	public enum Uso {
		NOVO, USADO
	};

	private Uso uso = Uso.NOVO;

	public Uso getUso() {
		return uso;
	}

	public void setUso(Uso uso) {
		this.uso = uso;
	}

	public Map<String, Uso> getUsoItems() {
		return usoItems;
	}

	private static Map<String, Uso> usoItems;
	static {
		usoItems = new LinkedHashMap<String, Uso>();
		usoItems.put("Bem novo", Uso.NOVO);// label, value
		usoItems.put("Bem usado", Uso.USADO);// label, value
	};

	public Collection<SelectItem> getYearItems() {
		return anosDeVidaUtil;
	}

	public Integer getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Integer vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	private static Collection<SelectItem> anosDeVidaUtil;
	static {
		anosDeVidaUtil = new ArrayList<SelectItem>();
		// the first item is a "no selection" item
		//anosDeVidaUtil.add(new SelectItem(null, "Vida util", "", false, false,
			//	true));
		for (int i = 1; i < 61; ++i)
			anosDeVidaUtil.add(new SelectItem(i));
	}

}
