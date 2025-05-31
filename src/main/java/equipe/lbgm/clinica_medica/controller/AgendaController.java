package equipe.lbgm.clinica_medica.controller;

import equipe.lbgm.clinica_medica.model.Agenda;
import equipe.lbgm.clinica_medica.service.AgendaService;
import equipe.lbgm.clinica_medica.service.MedicoService;
import equipe.lbgm.clinica_medica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendaService.listarTodos());
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda";
    }

    @PostMapping
    public String salvar(@ModelAttribute Agenda agenda) {
        agendaService.salvar(agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("agenda", agendaService.buscarPorId(id).orElse(new Agenda()));
        model.addAttribute("agendamentos", agendaService.listarTodos());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "agenda";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        agendaService.deletar(id);
        return "redirect:/agenda";
    }
}