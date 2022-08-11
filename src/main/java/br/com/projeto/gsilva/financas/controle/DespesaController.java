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

import br.com.projeto.gsilva.financas.modelo.dto.DespesaDto;
import br.com.projeto.gsilva.financas.modelo.form.DespesaForm;
import br.com.projeto.gsilva.financas.modelo.form.DespesaUpdateForm;
import br.com.projeto.gsilva.financas.servico.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	@Autowired
	DespesaService despesaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createDespesa(@RequestBody @Valid DespesaForm form, UriComponentsBuilder uriBuilder) {
		return despesaService.createDespesa(form, uriBuilder);
	}
	
	@GetMapping
	@Transactional
	public List<DespesaDto> readDespesaList(String descricao) {
		return despesaService.readDespesaList(descricao);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> readDespesaById(@PathVariable("id") Long id) {
		return despesaService.readDespesaById(id);
	}
	
	@GetMapping("/{ano}/{mes}")
	@Transactional
	public List<DespesaDto> readDespesaListByYearAndMonth(@PathVariable("ano") Integer ano,
			@PathVariable("mes") Integer mes) {
		return despesaService.readDespesaListByDataYearAndDataMonth(ano, mes);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateDespesa(@PathVariable("id") Long id, @RequestBody @Valid DespesaUpdateForm form) {
		return despesaService.updateDespesaById(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteDespesa(@PathVariable("id") Long id) {
		return despesaService.deleteDespesa(id);
	}
	
}
