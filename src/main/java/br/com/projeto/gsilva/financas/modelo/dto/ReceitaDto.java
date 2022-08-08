package br.com.projeto.gsilva.financas.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.projeto.gsilva.financas.modelo.Receita;

public class ReceitaDto {

	private Long id;
	private String descricao;
	private BigDecimal valor;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	public ReceitaDto() {}
	
	public ReceitaDto(Receita receita) {
		this.id = receita.getId();
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
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

	public static List<ReceitaDto> convertToListDto(List<Receita> receitas) {
		return receitas.stream().map(ReceitaDto::new).collect(Collectors.toList());
	}	
	
	public ReceitaDto convertToDto(Receita receita) {
		ReceitaDto receitaDto = new ReceitaDto();
		receitaDto.setId(receita.getId());
		receitaDto.setDescricao(receita.getDescricao());
		receitaDto.setValor(receita.getValor());
		receitaDto.setData(receita.getData());
		return receitaDto;
	}
	
}
