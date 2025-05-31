package equipe.lbgm.clinica_medica.api;

import equipe.lbgm.clinica_medica.model.Agenda;
import equipe.lbgm.clinica_medica.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaApiController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<Agenda> listarTodos() {
        return agendaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> buscarPorId(@PathVariable Long id) {
        return agendaService.buscarPorId(id)
                .map(agenda -> ResponseEntity.ok().body(agenda))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Agenda criar(@RequestBody Agenda agenda) {
        return agendaService.salvar(agenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> atualizar(@PathVariable Long id, @RequestBody Agenda agenda) {
        return agendaService.buscarPorId(id)
                .map(agendaExistente -> {
                    agenda.setId(id);
                    return ResponseEntity.ok(agendaService.salvar(agenda));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return agendaService.buscarPorId(id)
                .map(agenda -> {
                    agendaService.deletar(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}