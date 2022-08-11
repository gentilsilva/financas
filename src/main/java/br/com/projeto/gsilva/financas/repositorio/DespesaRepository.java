package br.com.projeto.gsilva.financas.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.gsilva.financas.modelo.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

	public Optional<Despesa> findByDescricaoAndDataBetween(String descricao, LocalDate dateIn, LocalDate dateOff);

	public List<Despesa> findByDescricao(String descricao);

	public List<Despesa> findAllByData(String data);

	@Query("SELECT d FROM Despesa d WHERE YEAR(d.data) = ?1 AND MONTH(d.data) = ?2")
	public List<Despesa> findAllByDataYearAndDataMonth(Integer ano, Integer mes);

}
