package equipe.lbgm.clinica_medica.controller;

import equipe.lbgm.clinica_medica.model.Paciente;
import equipe.lbgm.clinica_medica.repository.AgendaRepository;
import equipe.lbgm.clinica_medica.repository.PacienteRepository;
import equipe.lbgm.clinica_medica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendaRepository agendaRepository;

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
    public String deletarPaciente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Paciente não encontrado.");
            return "redirect:/pacientes";
        }

        Paciente paciente = pacienteOptional.get();
        boolean possuiAgendamentos = agendaRepository.existsByPaciente(paciente);

        if (possuiAgendamentos) {
            redirectAttributes.addFlashAttribute("erro", "Não é possível excluir. Este paciente possui agendamentos cadastrados.");
        } else {
            pacienteRepository.delete(paciente);
            redirectAttributes.addFlashAttribute("sucesso", "Paciente excluído com sucesso.");
        }

        return "redirect:/pacientes";
    }
}
