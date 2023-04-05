package br.com.btg.desafio.mappers;


import br.com.btg.desafio.dto.ItemPedidoDTO;
import br.com.btg.desafio.dto.PedidoDTO;
import br.com.btg.desafio.entity.ItemPedido;
import br.com.btg.desafio.entity.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCodigoPedido(dto.getCodigoPedido());
        pedido.setCodigoCliente(dto.getCodigoCliente());
        pedido.setValorTotal(dto.getItens().stream()
                .map(ItemPedidoDTO::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        pedido.setItens(toEntityItemList(dto.getItens()));
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        return pedido;
    }

    public ItemPedido toEntity(ItemPedidoDTO dto) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(dto.getProduto());
        itemPedido.setQuantidade(dto.getQuantidade());
        itemPedido.setPreco(dto.getPreco());
        return itemPedido;
    }

    public List<ItemPedido> toEntityItemList(List<ItemPedidoDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
