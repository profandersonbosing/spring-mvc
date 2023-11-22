package br.unipar.demomvc.controller;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.repository.DisciplinaRepository;
import br.unipar.demomvc.service.DisciplinaService;
import br.unipar.demomvc.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ModelAndView retornaHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("totalProf", professorService.listAll().size());
        modelAndView.addObject("totalDisciplinas", disciplinaService.listAll().size());

        return modelAndView;
    }

}
