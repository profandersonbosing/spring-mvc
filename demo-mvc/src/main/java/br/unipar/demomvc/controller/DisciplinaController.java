package br.unipar.demomvc.controller;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ModelAndView listaDisciplinas(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("disciplina/listar");

        if (model.containsAttribute("disciplinas"))
            modelAndView.addObject("disciplinas", model.getAttribute("disciplinas"));
        else {
            modelAndView.addObject("disciplinas", disciplinaService.listAll());
        }

        return modelAndView;
    }

    @GetMapping(path = "/filtrar")
    public String filtrarDisciplinas(@RequestParam("nome") String nome, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("disciplinas", disciplinaService.listByFilter(nome));
        return "redirect:/disciplina";
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarDisciplina(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("disciplina/inserir");
        modelAndView.addObject("disciplina", disciplinaService.findById(id));
        return modelAndView;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerDisciplina(@PathVariable("id") Long id){
        disciplinaService.delete(id);
        return "redirect:/disciplina";
    }

    @PostMapping
    public String salvarDisciplina(Disciplina disciplina){
        disciplinaService.insert(disciplina);
        return "redirect:/disciplina";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovaDisciplina(){
        ModelAndView modelAndView = new ModelAndView("disciplina/inserir");
        modelAndView.addObject("disciplina", new Disciplina());
        return modelAndView;
    }


}
