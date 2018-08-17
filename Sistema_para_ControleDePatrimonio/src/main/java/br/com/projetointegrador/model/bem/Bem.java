package br.com.projetointegrador.model.bem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import br.com.projetointegrador.model.tipoDeBem.TipoDeBem;

public class Bem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Date dtAquisicao;
	private double valorAquisicao;
	private Integer vidaUtil;
	private Integer valorResidual;
	private double valorContabil;
	private String status;
	private double valorDeVenda;
	private Date dtVenda;
	private String turno;
	private TipoDeBem tipo = new TipoDeBem();
	private String usado;
	private int tempoDeUso;
	private double valorDepreciado;

	public Bem() {
		LocalDate hoje = LocalDate.now();
		String hj = hoje.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dtAquisicao = df.parse(hj);
			this.dtVenda = df.parse(hj);
		} catch (ParseException ex) {
		}
		this.valorResidual = 10;
		this.tempoDeUso = 0;
		this.valorDeVenda = 0;
		this.usado = "NOVO";
	}

	public int getTempoDeUso() {
		return tempoDeUso;
	}

	public void setTempoDeUso(int tempoDeUso) {
		this.tempoDeUso = tempoDeUso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtAquisicao() {
		return dtAquisicao;
	}

	public void setDtAquisicao(Date dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}

	public double getValorAquisicao() {
		return valorAquisicao;
	}

	public void setValorAquisicao(double valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}

	public Integer getVidaUtil() {
		return vidaUtil;
	}

	public void setVidaUtil(Integer vidaUtil) {
		this.vidaUtil = vidaUtil;
	}

	public Integer getValorResidual() {
		return valorResidual;
	}

	public void setValorResidual(Integer valorResidual) {
		this.valorResidual = valorResidual;
	}

	public double getValorContabil() {
		return valorContabil;
	}

	public void setValorContabil(double valorContabil) {
		this.valorContabil = valorContabil;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public TipoDeBem getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeBem tipo) {
		this.tipo = tipo;
	}

	public String getUsado() {
		return usado;
	}

	public void setUsado(String usado) {
		this.usado = usado;
	}

	public double getValorDepreciado() {
		return valorDepreciado;
	}

	public void setValorDepreciado(double valorDepreciado) {
		this.valorDepreciado = valorDepreciado;
	}

}
