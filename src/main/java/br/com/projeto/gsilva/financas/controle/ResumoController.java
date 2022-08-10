package br.com.projeto.gsilva.financas.controle;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.gsilva.financas.modelo.dto.ResumoDto;
import br.com.projeto.gsilva.financas.servico.ReceitaService;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
	
	@Autowired
	ReceitaService receitaService;

	@GetMapping("/{ano}/{mes}")
	@Transactional
	public ResumoDto readReceitaTotalByDataYearAndDataMonth(@PathVariable("ano") Integer ano,
			@PathVariable("mes") Integer mes) {
		return receitaService.readReceitaTotalValorByDataYearAndDataMonth(ano, mes);
	}
	
}
