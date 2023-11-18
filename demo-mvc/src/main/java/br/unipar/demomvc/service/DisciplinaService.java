package br.unipar.demomvc.service;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<String> validate(Disciplina disciplina) {

        List<String> msg = new ArrayList<>();

        if (disciplina.getNome().contains("=")) { //Exemplo de validação customizada
            msg.add("Nome não deve conter caracteres que não são alfanumericos");
        }
        //TODO Implementer demais validações

        return msg;

    }
}
