package br.com.projeto.gsilva.financas.modelo.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import br.com.projeto.gsilva.financas.modelo.Despesa;
import br.com.projeto.gsilva.financas.repositorio.DespesaRepository;

public class DespesaUpdateForm {

	private String descricao;
	private BigDecimal valor;
	private String data;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public DespesaUpdateForm() {}
	
	public DespesaUpdateForm(String descricao, BigDecimal valor, String data) {
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
	
	public boolean isRepeatable(DespesaRepository despesaRepository) {
		LocalDate dateIn = LocalDate.parse(data, formatter).with(TemporalAdjusters.firstDayOfMonth());
		LocalDate dateOff = LocalDate.parse(data, formatter).with(TemporalAdjusters.lastDayOfMonth());
		return despesaRepository.findByDescricaoAndDataBetween(descricao, dateIn, dateOff).isPresent();
	}
	
	public Despesa update(Long id, DespesaRepository despesaRepository) {
		Despesa despesa = despesaRepository.getReferenceById(id);
		LocalDate date = LocalDate.parse(data, formatter);
		despesa.setDescricao(this.descricao);
		despesa.setValor(this.valor);
		despesa.setData(date);
		return despesa;
	}
	
}
