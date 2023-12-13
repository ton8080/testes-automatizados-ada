package br.com.ada.IFome.controller;

import br.com.ada.IFome.entity.Entregador;
import br.com.ada.IFome.service.EntregadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregadores")
public class IFomeController {

    @Autowired
    private EntregadorService entregadorService;

    @PostMapping("/salvar")
    public ResponseEntity<Entregador> salvarEntregador(@RequestBody Entregador entregador) {
        Entregador entregadorSalvo = entregadorService.salvar(entregador);
        return new ResponseEntity<>(entregadorSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> removerEntregador(@PathVariable Long id) {
        entregadorService.remover(id);
        return new ResponseEntity<>("Entregador removido com sucesso.", HttpStatus.OK);
    }
}
