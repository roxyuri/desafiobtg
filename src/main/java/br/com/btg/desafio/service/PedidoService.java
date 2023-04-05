package br.com.btg.desafio.service;

import br.com.btg.desafio.dto.ClientePedidoCountDTO;
import br.com.btg.desafio.dto.PedidoDTO;
import br.com.btg.desafio.entity.Pedido;
import br.com.btg.desafio.mappers.PedidoMapper;
import br.com.btg.desafio.repository.PedidoRepository;
import br.com.btg.desafio.response.PedidoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    public void consumirPedido(PedidoDTO pedidoDTO) {
        Optional<Pedido> optionalPedido = pedidoRepository.findByCodigoPedidoAndCodigoCliente(pedidoDTO.getCodigoPedido(), pedidoDTO.getCodigoCliente());
        Optional<Pedido> optionalPedidoDiferenteCliente = pedidoRepository.findByCodigoPedidoAndCodigoClienteNot(pedidoDTO.getCodigoPedido(), pedidoDTO.getCodigoCliente());

        if (optionalPedido.isPresent()) {
            logger.error("O pedido com código {} e cliente {} já existe.", pedidoDTO.getCodigoPedido(), pedidoDTO.getCodigoCliente());
        } else if (optionalPedidoDiferenteCliente.isPresent()) {
            logger.error("O pedido com código {} já existe não pode ser atribuído a um cliente diferente ({}).", pedidoDTO.getCodigoPedido(), pedidoDTO.getCodigoCliente());
        } else {
            Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
            pedidoRepository.save(pedido);
        }
    }

    public Page<PedidoResponse> buscarPedidosPorCliente(Long codigoCliente, Pageable pageable) {
        Page<Pedido> pedidos = pedidoRepository.findByCodigoCliente(codigoCliente, pageable);
        return pedidos.map(PedidoResponse::new);
    }

    public BigDecimal buscarValorTotalPedido(Long codigoPedido) {
        return pedidoRepository.findValorTotalByCodigoPedido(codigoPedido);
    }

    public Page<ClientePedidoCountDTO> getTotalPedidosPorCliente(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return pedidoRepository.countPedidosByCliente(pageable);
    }

}