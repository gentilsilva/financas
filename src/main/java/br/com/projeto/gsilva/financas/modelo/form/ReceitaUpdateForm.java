package br.com.projeto.gsilva.financas.modelo.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import br.com.projeto.gsilva.financas.modelo.Receita;
import br.com.projeto.gsilva.financas.repositorio.ReceitaRepository;

public class ReceitaUpdateForm {

	private String descricao;
	private BigDecimal valor;
	private String data;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ReceitaUpdateForm() {}
	
	public ReceitaUpdateForm(String descricao, BigDecimal valor, String data) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public boolean isRepeatable(ReceitaRepository receitaRepository) {
		LocalDate dateIn = LocalDate.parse(data, formatter).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate dateOff = LocalDate.parse(data, formatter).with(TemporalAdjusters.lastDayOfMonth());
		return receitaRepository.findByDescricaoAndDataBetween(descricao, dateIn, dateOff).isPresent();
	}
	
	public Receita update(Long id, ReceitaRepository receitaRepository) {
		Receita receita = receitaRepository.getReferenceById(id);
		LocalDate date = LocalDate.parse(data, formatter);
		receita.setDescricao(this.descricao);
		receita.setValor(this.valor);
		receita.setData(date);
		return receita;
	}
	
}
