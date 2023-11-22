package br.unipar.demomvc.repository;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query
    public List<Professor> findAllByOrderByIdAsc();
    @Query
    public List<Professor> findAllByNomeContainingIgnoreCaseOrderByIdAsc(String nome);


}
