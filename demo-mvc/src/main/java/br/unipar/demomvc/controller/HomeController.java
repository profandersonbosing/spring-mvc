package br.unipar.demomvc.controller;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.repository.DisciplinaRepository;
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
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ModelAndView retornaHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("totalProf", 0);
        modelAndView.addObject("totalDisciplinas", disciplinaRepository.findAllByOrderByIdAsc().size());

        return modelAndView;
    }

}
