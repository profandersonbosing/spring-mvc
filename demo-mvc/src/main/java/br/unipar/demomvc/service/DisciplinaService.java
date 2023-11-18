package br.unipar.demomvc.service;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public void insert(Disciplina disciplina) {
        disciplinaRepository.saveAndFlush(disciplina);
    }

    public void edit (Disciplina disciplina) {
        disciplinaRepository.saveAndFlush(disciplina);
    }

    public List<Disciplina> listAll() {
        return disciplinaRepository.findAllByOrderByIdAsc();
    }

    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id).get();
    }

    public void delete(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public List<Disciplina> listByFilter(String nome) {
        if (nome.isEmpty()) {
            return disciplinaRepository.findAll();
        } else {
            return disciplinaRepository.findAllByNomeContainingIgnoreCaseOrderByIdAsc(nome);
        }
    }
}
