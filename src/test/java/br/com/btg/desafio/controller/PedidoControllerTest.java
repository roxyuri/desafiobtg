package br.com.btg.desafio.controller;

import br.com.btg.desafio.dto.ClientePedidoCountDTO;
import br.com.btg.desafio.response.PedidoResponse;
import br.com.btg.desafio.response.ValorTotalResponse;
import br.com.btg.desafio.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private List<PedidoResponse> pedidos;
    private List<ClientePedidoCountDTO> clientes;
    private Page<PedidoResponse> pedidosPage;
    private Page<ClientePedidoCountDTO> clientesPage;
    private ValorTotalResponse valorTotalResponse;

    @BeforeEach
    public void setUp() {
        PedidoResponse pedidoResponse1 = new PedidoResponse(1L, BigDecimal.valueOf(100));
        PedidoResponse pedidoResponse2 = new PedidoResponse(2L, BigDecimal.valueOf(200));
        pedidos = Arrays.asList(pedidoResponse1, pedidoResponse2);
        pedidosPage = new PageImpl<>(pedidos);

        ClientePedidoCountDTO cliente1 = new ClientePedidoCountDTO(1L, 5L);
        ClientePedidoCountDTO cliente2 = new ClientePedidoCountDTO(2L, 2L);
        clientes = Arrays.asList(cliente1, cliente2);
        clientesPage = new PageImpl<>(clientes);

        valorTotalResponse = new ValorTotalResponse(BigDecimal.valueOf(100));
    }

    @Test
    public void testListarPedidosPorCliente() {
        when(pedidoService.buscarPedidosPorCliente(anyLong(), any(Pageable.class))).thenReturn(pedidosPage);

        ResponseEntity<Page<PedidoResponse>> response = pedidoController.listarPedidosPorCliente(1L, 0, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().getTotalElements());
    }

    @Test
    public void testBuscarrValorTotal() {
        when(pedidoService.buscarValorTotalPedido(anyLong())).thenReturn(BigDecimal.valueOf(100));

        ResponseEntity<ValorTotalResponse> response = pedidoController.buscarrValorTotal(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(valorTotalResponse.getValorTotal(), response.getBody().getValorTotal());
    }

    @Test
    public void testGetTotalPedidosPorCliente() {
        when(pedidoService.getTotalPedidosPorCliente(anyInt(), anyInt())).thenReturn(clientesPage);

        ResponseEntity<Page<ClientePedidoCountDTO>> response = pedidoController.getTotalPedidosPorCliente(0, 10);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().getTotalElements());
    }
}
