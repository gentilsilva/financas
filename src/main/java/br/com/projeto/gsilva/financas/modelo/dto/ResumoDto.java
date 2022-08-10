package br.com.projeto.gsilva.financas.modelo.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.projeto.gsilva.financas.modelo.Receita;

public class ResumoDto {

	private BigDecimal totalReceita = BigDecimal.ZERO;
	
	public ResumoDto() {}
	
	public ResumoDto(Receita receita) {
		this.totalReceita = receita.getValor();
	}

	public BigDecimal getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(BigDecimal totalReceita) {
		this.totalReceita = totalReceita;
	}
	
	public ResumoDto convertToDto(List<Receita> receita) {
		ResumoDto resumoDto = new ResumoDto();
		for (Receita valor : receita) {
			resumoDto.setTotalReceita(totalReceita.add(valor.getValor()));
		}
		return resumoDto;
	}
	
}
