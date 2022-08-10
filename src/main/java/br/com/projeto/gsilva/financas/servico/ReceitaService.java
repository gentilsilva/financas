package br.com.projeto.gsilva.financas.servico;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.gsilva.financas.modelo.Receita;
import br.com.projeto.gsilva.financas.modelo.dto.ReceitaDto;
import br.com.projeto.gsilva.financas.modelo.dto.ResumoDto;
import br.com.projeto.gsilva.financas.modelo.form.ReceitaForm;
import br.com.projeto.gsilva.financas.modelo.form.ReceitaUpdateForm;
import br.com.projeto.gsilva.financas.repositorio.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	ReceitaRepository receitaRepository;	
	
	public ResponseEntity<?> createReceita(ReceitaForm form, UriComponentsBuilder uriBuilder) {
		if(form.isRepeatable(receitaRepository)) {
			return ResponseEntity.badRequest().body("Receita duplicada");
		}		
		Receita receita = form.convertToReceita();		
		ReceitaDto receitaDto = new ReceitaDto();
		receitaRepository.save(receita);
		
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(receitaDto.convertToDto(receita));
	}
	
	public List<ReceitaDto> readReceitaList(String descricao) {
		if(descricao == null) {
			List<Receita> receitas = receitaRepository.findAll();
			return ReceitaDto.convertToListDto(receitas);			
		}
		List<Receita> receitas = receitaRepository.findByDescricao(descricao);
		return ReceitaDto.convertToListDto(receitas);
	}
	
	public ResponseEntity<ReceitaDto> readReceitaById(Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);
		ReceitaDto receitaDto = new ReceitaDto();
		if(receita.isPresent()) {
			return ResponseEntity.ok(receitaDto.convertToDto(receita.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public List<ReceitaDto> readReceitaListByYearAndMonth(Integer ano, Integer mes) {
		List<Receita> receita = receitaRepository.findAllByDataYearAndDataMonth(ano, mes);
		return ReceitaDto.convertToListDto(receita);
	}
	
// Teste de receitaTotal
	public ResumoDto readReceitaTotalValorByDataYearAndDataMonth(Integer ano, Integer mes) {
		ResumoDto resumoDto = new ResumoDto();
		List<Receita> receita = receitaRepository.findAllByDataYearAndDataMonth(ano, mes);
		return resumoDto.convertToDto(receita);
	}
	
	public ResponseEntity<?> updateReceitaById(Long id, ReceitaUpdateForm form) {
		Optional<Receita> optional = receitaRepository.findById(id);
//		if(form.isRepeatable(receitaRepository)) {
//			return ResponseEntity.badRequest().body("Receita duplicada");
//		}
		if(optional.isPresent()) {
			Receita receita = form.update(id, receitaRepository);
			return ResponseEntity.ok(new ReceitaDto(receita));			
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> deleteReceita(Long id) {
		Optional<Receita> receita = receitaRepository.findById(id);
		if(receita.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
