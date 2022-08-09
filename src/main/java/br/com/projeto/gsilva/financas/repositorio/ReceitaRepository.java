package br.com.projeto.gsilva.financas.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.gsilva.financas.modelo.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	public Optional<Receita> findByDescricaoAndDataBetween(String descricao, LocalDate dataIn, LocalDate dataOff);

	public List<Receita> findByDescricao(String descricao);

}
