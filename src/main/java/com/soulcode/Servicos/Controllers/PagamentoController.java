package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Pagamento;
import com.soulcode.Servicos.Services.PagementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class PagamentoController {

    @Autowired
    PagementoService pagementoService;

    @GetMapping("/pagamentos")
    public List<Pagamento> mostrarTodosPagamentos(){
        List<Pagamento> pagamentos = pagementoService.mostrarTodosPagamentos();
        return pagamentos;
    }

    @GetMapping("/pagamentos/{idPagamento}")
    public ResponseEntity<Pagamento> mostrarPagamentoPeloId(@PathVariable Integer idPagamento){
        Pagamento pagamento = pagementoService.mostrarPagamentoPeloId(idPagamento);
        return ResponseEntity.ok().body(pagamento);
    }

    @GetMapping("/pagamentosPeloStatus")
    public List<Pagamento> mostrarPagamentosPeloStatus(@RequestParam("status") String status){
        List<Pagamento> pagamentos = pagementoService.mostrarPagamentosPeloStatus(status);
        return pagamentos;
    }

    @GetMapping("/pagamentosChamadosComCliente")
    public List<List> orcamentoComServicoCliente(){
        List<List> pagamentos = pagementoService.orcamentoComServicoCliente();
        return pagamentos;
    }

    @PostMapping("/pagamentos/{idChamado}")
    public ResponseEntity<Pagamento> cadastrarPagamento(@PathVariable Integer idChamado,
                                                        @RequestBody Pagamento pagamento){
        pagamento = pagementoService.cadastrarPagamento(pagamento,idChamado);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pagamento.getIdPagamento()).toUri();
        return ResponseEntity.created(novaUri).build();
    }

    @PutMapping("/pagamentos/{idPagamento}")
    public ResponseEntity<Pagamento> editarPagamento(@PathVariable Integer idPagamento,
                                                     @RequestBody Pagamento pagamento){
        pagamento.setIdPagamento(idPagamento);
        pagementoService.editarPagamento(pagamento);
        return ResponseEntity.ok().body(pagamento);
    }

    @PutMapping("/pagamentosAlteracaoStatus/{idPagamento}")
    public ResponseEntity<Pagamento> modificarStatusPagamento(@PathVariable Integer idPagamento,
                                                              @RequestParam("status") String status){
        pagementoService.modificarStatusPagamento(idPagamento,status);
        return ResponseEntity.noContent().build();
    }


}