package br.unipar.demomvc.controller;

import br.unipar.demomvc.domain.Disciplina;
import br.unipar.demomvc.domain.Professor;
import br.unipar.demomvc.repository.ProfessorRepository;
import br.unipar.demomvc.service.DisciplinaService;
import br.unipar.demomvc.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ModelAndView listaProfessor(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("professor/listar");

        if (model.containsAttribute("professores"))
            modelAndView.addObject("professores", model.getAttribute("professores"));
        else {
            modelAndView.addObject("professores", professorService.listAll());
        }

        return modelAndView;
    }

    @GetMapping(path = "/filtrar")
    public String filtrarProfessores(@RequestParam("nome") String nome, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("professores",
                professorService.listByFilter(nome));
        return "redirect:/professor";
    }

    @GetMapping(path = "/criar")
    public ModelAndView retornaNovoProfessor(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("professor/inserir");

        if (model.containsAttribute("professor")) {
            modelAndView.addObject("professor", model.getAttribute("professor"));
            modelAndView.addObject("msg", model.getAttribute("msg"));

        } else {
            modelAndView.addObject("professor", new Disciplina());
            modelAndView.addObject("msg", new ArrayList<String>());
        }

        return modelAndView;
    }

    @PostMapping
    public String salvarProfessor(@Valid Professor professor,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        List<String> msg = new ArrayList<>();
        msg.addAll(professorService.validate(professor));

        if (bindingResult.hasErrors() || !msg.isEmpty()) {
            redirectAttributes.addFlashAttribute("professor", professor);

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(
                        ((FieldError) objectError).getField() + " " +
                                objectError.getDefaultMessage()); // vem das anotações @NotEmpty e outras
            }

            redirectAttributes.addFlashAttribute("msg", msg);

            return "redirect:/professor/criar";
        }

        professorService.insert(professor);

        return "redirect:/professor";
    }

    @GetMapping(path = "/editar/{id}")
    public ModelAndView editarProfessor(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("professor/inserir");
        modelAndView.addObject("professor", professorService.findById(id));
        return modelAndView;
    }

    @GetMapping(path = "/disciplinas/editar/{id}")
    public ModelAndView editarDisciplinasProfessor(@PathVariable("id") Long id, ModelMap model){
        ModelAndView modelAndView = new ModelAndView("professor/disciplinasprofessor/listar");
        modelAndView.addObject("professor", professorService.findById(id));
        modelAndView.addObject("disciplinaadd", new Disciplina());
        modelAndView.addObject("disciplinas", disciplinaService.listAll());

        if (model.containsAttribute("msg"))
            modelAndView.addObject("msg", model.getAttribute("msg"));

        return modelAndView;
    }

    @PostMapping(path = "/disciplinas/editar/{id}/adicionar/")
    public String salvarDisciplinasProfessor(@PathVariable("id") Long id,
                                             @RequestParam("idDisciplina") Long idDisciplina,
                                             RedirectAttributes redirectAttributes){
        List<String> validacoes = professorService.insertDisciplinas(id, idDisciplina);

        if (!validacoes.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", validacoes);
        }

        return "redirect:/professor/disciplinas/editar/"+id;
    }

    @GetMapping(path = "disciplinas/editar/{id}/remover/{idDisciplina}")
    public String removerDisciplina(@PathVariable("id") Long id,
                                    @PathVariable("idDisciplina") Long idDisciplina){
        professorService.deleteDisciplina(id, idDisciplina);
        return "redirect:/professor/disciplinas/editar/"+id;
    }

    @GetMapping(path = "/remover/{id}")
    public String removerProfessor(@PathVariable("id") Long id){
        professorService.delete(id);
        return "redirect:/professor";
    }

}
