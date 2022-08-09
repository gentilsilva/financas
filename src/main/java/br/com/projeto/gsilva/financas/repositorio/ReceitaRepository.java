package br.com.projeto.gsilva.financas.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.gsilva.financas.modelo.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	public Optional<Receita> findByDescricaoAndDataBetween(String descricao, LocalDate dataIn, LocalDate dataOff);

	public List<Receita> findByDescricao(String descricao);

	@Query("SELECT r FROM Receita r WHERE YEAR(r.data) = ?1 AND MONTH(r.data) = ?2")
	public List<Receita> findAllByDataYearAndDataMonth(Integer ano, Integer mes);

}
