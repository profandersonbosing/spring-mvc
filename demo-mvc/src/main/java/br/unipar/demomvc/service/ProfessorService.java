package br.unipar.demomvc.service;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.domain.Professor;
import br.unipar.demomvc.repository.DisciplinaRepository;
import br.unipar.demomvc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    public void insert(Professor professor) {
        professorRepository.saveAndFlush(professor);
    }

    public void edit (Professor professor) {
        professorRepository.saveAndFlush(professor);
    }

    public List<Professor> listAll() {
        return professorRepository.findAllByOrderByIdAsc();
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).get();
    }

    public void delete(Long id) {
        professorRepository.deleteById(id);
    }

    public void deleteDisciplina(Long idProfessor, Long idDisciplina) {
        Professor professor = findById(idProfessor);
        Disciplina disciplina = disciplinaService.findById(idDisciplina);
        professor.getDisciplinas().remove(disciplina);

        edit(professor);
    }

    public List<Professor> listByFilter(String nome) {
        if (nome.isEmpty()) {
            return professorRepository.findAll();
        } else {
            return professorRepository.findAllByNomeContainingIgnoreCaseOrderByIdAsc(nome);
        }
    }

    public List<String> validate(Professor professor) {

        List<String> msg = new ArrayList<>();

        //TODO Implementer demais validações

        return msg;

    }

    public List<String> insertDisciplinas(Long idProfessor, Long idDisciplina) {

        List<String> validacoes = new ArrayList<>();

        Professor professor = findById(idProfessor);

        if (professor.getDisciplinas().stream().filter(
                disciplina -> disciplina.getId().equals(idDisciplina)
        ).count() != 0) {
            validacoes.add("Disciplina já Ministrada Pelo Professor");
        } else {

            if (idDisciplina == 0) {
                validacoes.add("Selecione a Disciplina que o Professor irá Ministrar");
                return validacoes;
            }

            Disciplina disciplina = disciplinaService.findById(idDisciplina);

            professor.getDisciplinas().add(disciplina);
            edit(professor);

        }

        return validacoes;

    }

}
