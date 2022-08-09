package br.com.projeto.gsilva.financas.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.gsilva.financas.modelo.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	public Optional<Despesa> findByDescricaoAndDataBetween(String descricao, LocalDate dateIn, LocalDate dateOff);

	public List<Despesa> findByDescricao(String descricao);

	public List<Despesa> findAllByData(String data);

//	public List<Despesa> findAllByDataYear(LocalDate data);

}
