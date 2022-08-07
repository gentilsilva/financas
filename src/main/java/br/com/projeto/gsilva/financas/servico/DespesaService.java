package br.com.projeto.gsilva.financas.servico;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.gsilva.financas.modelo.Despesa;
import br.com.projeto.gsilva.financas.modelo.dto.DespesaDto;
import br.com.projeto.gsilva.financas.modelo.form.DespesaForm;
import br.com.projeto.gsilva.financas.modelo.form.DespesaUpdateForm;
import br.com.projeto.gsilva.financas.repositorio.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	DespesaRepository despesaRepository;

	public ResponseEntity<?> createDespesa(DespesaForm form, UriComponentsBuilder uriBuilder) {
		if(form.isRepeatable(despesaRepository)) {
			return ResponseEntity.badRequest().body("Despesa duplicada");
		}
		Despesa despesa = form.convertToDespesa();
		DespesaDto despesaDto = new DespesaDto();
		despesaRepository.save(despesa);
		
		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();
		return ResponseEntity.created(uri).body(despesaDto.convertToDto(despesa));
	}

	public List<DespesaDto> readDespesaList() {
		List<Despesa> despesa = despesaRepository.findAll();
		return DespesaDto.convertToListDto(despesa);
	}

	public ResponseEntity<DespesaDto> readDespesaById(Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		DespesaDto despesaDto = new DespesaDto();
		if(despesa.isPresent()) {
			return ResponseEntity.ok(despesaDto.convertToDto(despesa.get()));
		}		
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> updateDespesaById(Long id, @Valid DespesaUpdateForm form) {
		Optional<Despesa> optional = despesaRepository.findById(id);
//		if(form.isRepeatable(despesaRepository)) {
//		return ResponseEntity.badRequest().body("Despesa duplicada");
//	}
		if(optional.isPresent()) {
			Despesa despesa = form.update(id, despesaRepository);
			return ResponseEntity.ok(new DespesaDto(despesa));
		}
		return ResponseEntity.badRequest().build();
	}

	public ResponseEntity<?> deleteDespesa(Long id) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		if(despesa.isPresent()) {
			despesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}	
	
}
