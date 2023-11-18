package br.unipar.demomvc.controller;

import br.unipar.demomvc.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ModelAndView listaDisciplinas(){
        ModelAndView modelAndView = new ModelAndView("disciplina/listar");
        modelAndView.addObject("disciplinas", disciplinaService.listAll());
        return modelAndView;
    }

}
