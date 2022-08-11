package br.com.projeto.gsilva.financas.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.gsilva.financas.modelo.Despesa;
import br.com.projeto.gsilva.financas.modelo.Receita;
import br.com.projeto.gsilva.financas.modelo.dto.ResumoDto;
import br.com.projeto.gsilva.financas.repositorio.DespesaRepository;
import br.com.projeto.gsilva.financas.repositorio.ReceitaRepository;

@Service
public class ResumoService {

	@Autowired
	ReceitaRepository receitaRepository;	
	
	@Autowired
	DespesaRepository despesaRepository;
	
	public ResumoDto readTotalValorByDataYearAndDataMonth(Integer ano, Integer mes) {
		ResumoDto resumoDto = new ResumoDto();
		List<Receita> receita = receitaRepository.findAllByDataYearAndDataMonth(ano, mes);
		List<Despesa> despesa = despesaRepository.findAllByDataYearAndDataMonth(ano, mes);
		return resumoDto.convertValorReceitaToDto(receita, despesa);
	}
	
}
