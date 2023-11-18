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
        return disciplinaRepository.findAll();
    }

    public Disciplina findById(Long id) {
        return disciplinaRepository.findById(id).get();
    }

}
