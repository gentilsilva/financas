package br.com.projeto.gsilva.financas.controle;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.gsilva.financas.modelo.dto.ReceitaDto;
import br.com.projeto.gsilva.financas.modelo.form.ReceitaForm;
import br.com.projeto.gsilva.financas.modelo.form.ReceitaUpdateForm;
import br.com.projeto.gsilva.financas.servico.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	ReceitaService receitaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createReceita(@RequestBody @Valid ReceitaForm form, UriComponentsBuilder uriBuilder) {
		return receitaService.createReceita(form, uriBuilder);
	}
	
	@GetMapping
	@Transactional
	public List<ReceitaDto> readReceitaList(String descricao) {
		return receitaService.readReceitaList(descricao);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> readReceitaById(@PathVariable("id") Long id) {
		return receitaService.readReceitaById(id);
	}
	
	@GetMapping("/{ano}/{mes}")
	@Transactional
	public List<ReceitaDto> readReceitaListByYearAndMonth(@PathVariable("ano") Integer ano,
			@PathVariable("mes") Integer mes) {
		return receitaService.readReceitaListByYearAndMonth(ano, mes);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateReceita(@PathVariable("id") Long id, @RequestBody @Valid ReceitaUpdateForm form) {
		return receitaService.updateReceitaById(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteReceita(@PathVariable("id") Long id) {
		return receitaService.deleteReceita(id);
	}
	
}
