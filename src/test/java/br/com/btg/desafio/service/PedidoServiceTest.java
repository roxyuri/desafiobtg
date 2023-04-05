package br.com.btg.desafio.service;

import br.com.btg.desafio.dto.ClientePedidoCountDTO;
import br.com.btg.desafio.dto.PedidoDTO;
import br.com.btg.desafio.entity.Pedido;
import br.com.btg.desafio.mappers.PedidoMapper;
import br.com.btg.desafio.repository.PedidoRepository;
import br.com.btg.desafio.response.PedidoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoMapper pedidoMapper;

    private PedidoDTO pedidoDTO;
    private Pedido pedido;

    @BeforeEach
    public void setUp() {
        pedidoDTO = new PedidoDTO(1L, 1L, new ArrayList<>());
        pedido = new Pedido(1L, BigDecimal.ONE, new ArrayList<>());
    }

    @Test
    public void testConsumirPedido() {
        when(pedidoRepository.findByCodigoPedidoAndCodigoCliente(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(pedidoRepository.findByCodigoPedidoAndCodigoClienteNot(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(pedidoMapper.toEntity(pedidoDTO)).thenReturn(pedido);

        pedidoService.consumirPedido(pedidoDTO);

        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    public void testBuscarPedidosPorCliente() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(pedido);
        Page<Pedido> pedidoPage = new PageImpl<>(pedidoList, pageable, 1);

        when(pedidoRepository.findByCodigoCliente(anyLong(), any(Pageable.class))).thenReturn(pedidoPage);

        Page<PedidoResponse> responsePage = pedidoService.buscarPedidosPorCliente(1L, pageable);

        assertEquals(1, responsePage.getContent().size());
        verify(pedidoRepository, times(1)).findByCodigoCliente(anyLong(), any(Pageable.class));
    }

    @Test
    public void testBuscarValorTotalPedido() {
        BigDecimal valorTotal = new BigDecimal("100.00");
        when(pedidoRepository.findValorTotalByCodigoPedido(anyLong())).thenReturn(valorTotal);

        BigDecimal result = pedidoService.buscarValorTotalPedido(1L);

        assertEquals(valorTotal, result);
        verify(pedidoRepository, times(1)).findValorTotalByCodigoPedido(anyLong());
    }

    @Test
    public void testGetTotalPedidosPorCliente() {
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ClientePedidoCountDTO> clientePedidoCountDTOList = new ArrayList<>();
        clientePedidoCountDTOList.add(new ClientePedidoCountDTO(1L, 5L));
        Page<ClientePedidoCountDTO> clientePedidoCountDTOPage= new PageImpl<>(clientePedidoCountDTOList, pageable, 1);

        when(pedidoRepository.countPedidosByCliente(any(Pageable.class))).thenReturn(clientePedidoCountDTOPage);

        Page<ClientePedidoCountDTO> result = pedidoService.getTotalPedidosPorCliente(pageNumber, pageSize);

        assertEquals(1, result.getContent().size());
        assertEquals(1L, result.getContent().get(0).getCodigoCliente());
        assertEquals(5L, result.getContent().get(0).getTotalPedidos());
        verify(pedidoRepository, times(1)).countPedidosByCliente(any(Pageable.class));
    }
}

