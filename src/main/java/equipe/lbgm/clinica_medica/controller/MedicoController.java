package equipe.lbgm.clinica_medica.controller;

import equipe.lbgm.clinica_medica.model.Medico;
import equipe.lbgm.clinica_medica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

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
    public String deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return "redirect:/medicos";
    }
}