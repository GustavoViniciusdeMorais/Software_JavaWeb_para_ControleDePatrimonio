package br.com.projetointegrador.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.Serializable;

import org.junit.Test;

import br.com.projetointegrador.model.bem.Bem;

public class RecalcularDepreciacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private double vc;
	private double vr;
	private double valDepreciado;
	private int periodoDeUtilizacao;
	private int taxaDoBem;
	private String dataAutal;
	private Date data = new Date();
	private Date dataB = new Date();
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	// nome do objeto bem e -> b
	public double getVc() {
		return vc;
	}

	public double getVr() {
		return vr;
	}

	public int getTaxaDoBem() {
		return taxaDoBem;
	}

	public double getValDepreciado() {
		return valDepreciado;
	}

	public int getPeriodoDeUtilizacao() {
		return periodoDeUtilizacao;
	}

	public String getDataAutal() {
		return dataAutal;
	}

	public Bem algoritmoDepreciacao(Bem b) {
		Bem novo = null;
		boolean x = this.validarDa(b);
		if (b.getTipo().getCategoria().toLowerCase().compareTo("terreno") == 0) {
			System.out.println("tipo do bem: " + b.getTipo());
			this.vc = this.calculaValorContabil(b);
			this.vr = this.calculaValorResidual(b);
			this.taxaDoBem = b.getValorResidual();
			this.valDepreciado = this.calculaValorDepreciado(b);
			this.periodoDeUtilizacao = this.calculaPeriodo(b);
			this.dataAutal = this.pegaDataAtual();
			novo = new Bem();
			novo.setId(b.getId());
			novo.setNome(b.getNome());
			novo.setValorContabil(b.getValorAquisicao());
			novo.setValorResidual(b.getValorResidual());
			novo.setValorDepreciado(0);
			novo.setTempoDeUso(b.getTempoDeUso());// novo.setTempoDeUso(this.periodoDeUtilizacao);
			novo.setDtAquisicao(b.getDtAquisicao());
			novo.setStatus(b.getStatus());
			novo.setVidaUtil(b.getVidaUtil());
			novo.setValorAquisicao(b.getValorAquisicao());
			novo.setDtVenda(b.getDtVenda());
			novo.setTurno(b.getTurno());
			novo.setTipo(b.getTipo());
		} else {
			if (x) {
				this.vc = this.calculaValorContabil(b);
				this.vr = this.calculaValorResidual(b);
				this.taxaDoBem = b.getValorResidual();
				this.valDepreciado = this.calculaValorDepreciado(b);
				this.periodoDeUtilizacao = this.calculaPeriodo(b);
				this.dataAutal = this.pegaDataAtual();
				novo = new Bem();
				novo.setId(b.getId());
				novo.setNome(b.getNome());
				novo.setValorContabil(this.vc);
				novo.setValorResidual(b.getValorResidual());
				novo.setValorDepreciado(this.valDepreciado);
				novo.setTempoDeUso(b.getTempoDeUso());// novo.setTempoDeUso(this.periodoDeUtilizacao);
				novo.setDtAquisicao(b.getDtAquisicao());
				novo.setStatus(b.getStatus());
				novo.setVidaUtil(b.getVidaUtil());
				novo.setValorAquisicao(b.getValorAquisicao());
				novo.setDtVenda(b.getDtVenda());
				novo.setTurno(b.getTurno());
				novo.setTipo(b.getTipo());
			} else {
				this.vc = b.getValorContabil();
				this.valDepreciado = b.getValorDepreciado();
				novo = new Bem();
				novo.setId(b.getId());
				novo.setNome(b.getNome());
				novo.setValorContabil(this.vc);
				novo.setValorResidual(b.getValorResidual());
				novo.setValorDepreciado(this.valDepreciado);
				novo.setTempoDeUso(b.getTempoDeUso());// novo.setTempoDeUso(this.periodoDeUtilizacao);
				novo.setDtAquisicao(b.getDtAquisicao());
				novo.setStatus(b.getStatus());
				novo.setVidaUtil(b.getVidaUtil());
				novo.setValorAquisicao(b.getValorAquisicao());
				novo.setDtVenda(b.getDtVenda());
				novo.setTurno(b.getTurno());
				novo.setTipo(b.getTipo());
			}
		}
		return novo;
	}

	public double calculaTaxa(Bem b) {
		int vidaUtil = b.getVidaUtil();
		double turno = 1 + this.calcularTurnos(b);
		double taxa = 0;
		if (b.getTempoDeUso() > 0) {
			int tempoDeUso = b.getTempoDeUso();
			int restante = 0, metade = 0;
			if (tempoDeUso > 0) {
				restante = vidaUtil - tempoDeUso;
				System.out.println("restante: " + restante);
			} else {
				metade = vidaUtil / 2;
			}
			if (metade > restante) {
				taxa = metade;
			} else {
				taxa = restante;
			}
			taxa = ((100.0 / taxa) * turno) / 100;
			// System.out.println("valor da taxa: " + taxa);
		} else {
			taxa = vidaUtil;
			taxa = ((100.0 / taxa) * turno) / 100;
			// System.out.println("valor da taxa: " + taxa);
		}
		System.out.println("taxa vida util: " + taxa);
		return taxa;
	}

	public double calculaValorResidual(Bem b) {
		double valorAquisicao = b.getValorAquisicao();
		double taxa = b.getValorResidual() / 100.0;// this.calculaTaxa(b);
		double valorResidual = valorAquisicao - (valorAquisicao * taxa);
		System.out.println("calculaValorResidual: b.getValorResidual(): "
				+ taxa);
		// System.out.println("vr: " + valorAquisicao * taxa);
		System.out.println("valorResidual: " + valorResidual);
		System.out.println("valorAquisicao: " + valorAquisicao);
		return valorResidual;
	}

	public int calculaPeriodo(Bem b) {
		int meses = this.calcularMeses(b);
		int mesesInvalidos = this.calcularMesesInvalidos(b);
		int periodo = meses - mesesInvalidos;// - mesesInvalidos;
		System.out.println("periodo: " + periodo);
		return periodo;
	}

	public double calculaDA(Bem b) {
		double valResidual = this.calculaValorResidual(b);
		double taxa = this.calculaTaxa(b);
		int periodo = this.calculaPeriodo(b);
		System.out.println("da formula:  periodo:" + periodo + " tx:" + taxa
				+ " vr: " + valResidual);
		double da = ((valResidual * taxa) / 12) * periodo;
		System.out.println("DA: " + da);
		return da;
	}

	public double calculaValorContabil(Bem b) {
		double da = this.calculaDA(b);
		double valorContabil = b.getValorAquisicao() - da;
		System.out.println("valorContabil: ----- " + valorContabil);
		System.out.println("valor aquisi: ---- " + b.getValorAquisicao());
		return valorContabil;
	}

	public double calculaValorDepreciado(Bem b) {
		double valorDepreciado = this.calculaDA(b);
		System.out.println("valorDepreciado: " + valorDepreciado);
		return valorDepreciado;
	}

	// @Test
	public int calcularMeses(Bem b) {
		int qtdMeses = 0;
		System.out.println("obj b vei com data: "
				+ b.getDtAquisicao().toString());
		String dataAquisicao = b.getDtAquisicao().toString();
		try {
			dataB = df.parse(b.getDtVenda().toString());
			data = df.parse(b.getDtAquisicao().toString());
		} catch (ParseException ex) {

		}
		long diferencaMeses = (b.getDtVenda().getTime() - b.getDtAquisicao()
				.getTime()) / (1000 * 60 * 60 * 24) / 30;
		qtdMeses = (int) diferencaMeses;
		System.out.println("calcularMeses: data da venda: "
				+ b.getDtVenda().toString());
		System.out.println("calcularMeses: data da aqui: "
				+ b.getDtAquisicao().toString());
		return qtdMeses;
	}

	// @Test
	public int calcularMesesInvalidos(Bem b) {
		// resolver periodo, ate 15 na compra conta, apos 15 na venda conta
		GregorianCalendar dataGregoria = new GregorianCalendar();

		int contaMesesInvalidos = 0;
		// b = new BemDao().selectById(4L);

		if (b.getDtAquisicao() != null) {
			dataGregoria.setTime(b.getDtAquisicao());
			// pega dia aquisicao
			int diaDeAquisicao = dataGregoria.get(Calendar.DAY_OF_MONTH);
			if (diaDeAquisicao > 15) {
				contaMesesInvalidos++;
			}
		}
		if (b.getDtVenda() != null) {
			dataGregoria.setTime(b.getDtVenda());
			// pega dia venda
			int diaDeVenda = dataGregoria.get(Calendar.DAY_OF_MONTH);
			if (diaDeVenda <= 15) {
				contaMesesInvalidos++;
			}
		} else {
			LocalDate hoje = LocalDate.now();
			String hj = hoje.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			try {
				b.setDtVenda(df.parse(hj));
			} catch (ParseException ex) {
			}
			dataGregoria.setTime(b.getDtVenda());
			int diaDeVenda = dataGregoria.get(Calendar.DAY_OF_MONTH);
			if (diaDeVenda < 15) {
				contaMesesInvalidos++;
			}
		}
		System.out.println("meses invalidos: " + contaMesesInvalidos);
		return contaMesesInvalidos;
	}

	// @Test
	public String pegaDataAtual() {
		LocalDate hoje = LocalDate.now();
		String hj = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		// System.out.println(hj);
		return hj;
	}

	// @Test
	public double calcularTurnos(Bem b) {
		double turno = 0;

		System.out.println(b.getTurno().charAt(0));
		char t = b.getTurno().charAt(0);
		if (t == '1') {
			System.out.println("0");
			turno = 0;
		} else {
			if (t == '2') {
				System.out.println("0.5");
				turno = 0.5;
			} else {
				if (t == '3') {
					System.out.println("1.0");
					turno = 1.0;
				} else {
					if (t == '4') {
						System.out.println("1.5");
						turno = 1.5;
					} else {
						System.out.println("2.0");
						turno = 2.0;
					}
				}
			}
		}
		return turno;
	}

	public boolean validarDa(Bem b) {
		double da = this.calculaDA(b);
		double vr = this.calculaValorResidual(b);
		// b.getValorAquisicao()- (b.getValorAquisicao() * (b.getValorResidual()
		// / 100.0));
		boolean x = false;
		if (da > vr) {
			x = false;
			System.out.println("da: " + da + " é MAIOR que vr: " + vr);
		} else {
			x = true;
			System.out.println("da: " + da + " é MENOR que valAqui: " + vr);
		}
		return x;
	}

}
