package br.com.projeto.gsilva.financas.modelo.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.projeto.gsilva.financas.modelo.Despesa;
import br.com.projeto.gsilva.financas.modelo.Receita;

public class ResumoDto {

	private BigDecimal totalReceita;
	private BigDecimal totalDespesa;
	private BigDecimal saldoFinal;
	
	public ResumoDto() {}
	
	public ResumoDto(Receita receita, Despesa despesa) {
		this.totalReceita = receita.getValor();
		this.totalDespesa = receita.getValor();
	}

	public BigDecimal getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(BigDecimal totalReceita) {
		this.totalReceita = totalReceita;
	}

	public BigDecimal getTotalDespesa() {
		return totalDespesa;
	}
	
	public void setTotalDespesa(BigDecimal totalDespesa) {
		this.totalDespesa = totalDespesa;
	}
	
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public ResumoDto convertValorReceitaToDto(List<Receita> receita, List<Despesa> despesa) {
		ResumoDto resumoDto = new ResumoDto();
		BigDecimal valorReceita = receita.stream().map(Receita::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal valorDespesa = despesa.stream().map(Despesa::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
		resumoDto.setTotalReceita(valorReceita);
		resumoDto.setTotalDespesa(valorDespesa);
		resumoDto.setSaldoFinal(valorReceita.subtract(valorDespesa));
		return resumoDto;
	}

}
