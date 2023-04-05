package br.com.btg.desafio.controller;

import br.com.btg.desafio.dto.ClientePedidoCountDTO;
import br.com.btg.desafio.response.PedidoResponse;
import br.com.btg.desafio.response.ValorTotalResponse;
import br.com.btg.desafio.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/pedidos", method = RequestMethod.GET)
@Tag(name = "Pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Lista todos os pedidos realizados por um determinado cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Page<PedidoResponse>> listarPedidosPorCliente(
            @RequestParam("codigoCliente")
            @Parameter(name = "codigoCliente", description = "Código do cliente") Long codigoCliente,
            @RequestParam(value = "pagina", defaultValue = "0")
            @Parameter(name = "pagina", description = "Número da página") int pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10")
            @Parameter(name = "tamanhoPagina", description = "Tamanho da página") int tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        return ResponseEntity.ok(pedidoService.buscarPedidosPorCliente(codigoCliente, pageable));
    }

    @GetMapping("/valor-total-pedido")
    @Operation(summary = "Busca o valor total de um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Valor encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<ValorTotalResponse> buscarrValorTotal(
            @RequestParam("codigoPedido")
            @Parameter(name = "codigoPedido", description = "Código do pedido") Long codigoPedido) {
        BigDecimal valorTotal = pedidoService.buscarValorTotalPedido(codigoPedido);
        ValorTotalResponse response = new ValorTotalResponse(valorTotal);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/relatorio-total-por-cliente")
    @Operation(summary = "Traz um relatório de todos os clientes com o total de pedidos feitos")
    public ResponseEntity<Page<ClientePedidoCountDTO>> getTotalPedidosPorCliente(
            @RequestParam(value = "pagina", defaultValue = "0")
            @Parameter(name = "pagina", description = "Número da página") int pagina,
            @RequestParam(value = "tamanhoPagina", defaultValue = "10")
            @Parameter(name = "tamanhoPagina", description = "Tamanho da página") int tamanhoPagina) {
        Page<ClientePedidoCountDTO> result = pedidoService.getTotalPedidosPorCliente(pagina, tamanhoPagina);
        return ResponseEntity.ok(result);
    }
}