package br.com.projeto.gsilva.financas.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.projeto.gsilva.financas.modelo.Despesa;

public class DespesaDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	private String categoria;
	
	public DespesaDto() {}
	
	public DespesaDto(Despesa despesa) {
		this.id = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria().toString();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public static List<DespesaDto> convertToListDto(List<Despesa> despesa) {
		return despesa.stream().map(DespesaDto::new).collect(Collectors.toList());
	}

	public DespesaDto convertToDto(Despesa despesa) {
		DespesaDto despesaDto = new DespesaDto();
		despesaDto.setId(despesa.getId());
		despesaDto.setDescricao(despesa.getDescricao());
		despesaDto.setValor(despesa.getValor());
		despesaDto.setData(despesa.getData());
		despesaDto.setCategoria(despesa.getCategoria().toString());
		return despesaDto;
	}	
	
}
