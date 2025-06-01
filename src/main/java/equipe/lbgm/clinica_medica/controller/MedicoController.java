package equipe.lbgm.clinica_medica.controller;

import equipe.lbgm.clinica_medica.model.Medico;
import equipe.lbgm.clinica_medica.repository.AgendaRepository;
import equipe.lbgm.clinica_medica.repository.MedicoRepository;
import equipe.lbgm.clinica_medica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("medico", new Medico());
        return "medicos";
    }

    @PostMapping
    public String salvar(@ModelAttribute Medico medico) {
        medicoService.salvar(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("medico", medicoService.buscarPorId(id).orElse(new Medico()));
        model.addAttribute("medicos", medicoService.listarTodos());
        return "medicos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMedico(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Medico> medicoOptional = medicoRepository.findById(id);
        if (medicoOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Médico não encontrado.");
            return "redirect:/medicos";
        }

        Medico medico = medicoOptional.get();
        boolean possuiAgendamentos = agendaRepository.existsByMedico(medico);

        if (possuiAgendamentos) {
            redirectAttributes.addFlashAttribute("erro", "Não é possível excluir. Este médico possui agendamentos cadastrados.");
        } else {
            medicoRepository.delete(medico);
            redirectAttributes.addFlashAttribute("sucesso", "Médico excluído com sucesso.");
        }

        return "redirect:/medicos";
    }
}
