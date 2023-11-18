package br.unipar.demomvc.repository;

import br.unipar.demomvc.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    @Query
    public List<Disciplina> findAllByOrderByIdAsc();
    @Query
    public List<Disciplina> findAllByNomeContainingIgnoreCaseOrderByIdAsc(String nome);

}
