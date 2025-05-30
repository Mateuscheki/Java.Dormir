package equipe.lbgm.clinica_medica.controller;

import equipe.lbgm.clinica_medica.model.Paciente;
import equipe.lbgm.clinica_medica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteService.listarTodos());
        model.addAttribute("paciente", new Paciente());
        return "pacientes";
    }

    @PostMapping
    public String salvar(@ModelAttribute Paciente paciente) {
        pacienteService.salvar(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.buscarPorId(id).orElse(new Paciente()));
        model.addAttribute("pacientes", pacienteService.listarTodos());
        return "pacientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return "redirect:/pacientes";
    }
}