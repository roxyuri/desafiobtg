package br.com.btg.desafio.response;

import br.com.btg.desafio.entity.Pedido;

import java.math.BigDecimal;

public class PedidoResponse {
    private Long codigoPedido;
    private BigDecimal valorTotal;

    public PedidoResponse(Pedido pedido) {
        this.codigoPedido = pedido.getCodigoPedido();
        this.valorTotal = pedido.getValorTotal();
    }

    public PedidoResponse(long codigoPedido, BigDecimal valorTotal) {
        this.codigoPedido = codigoPedido;
        this.valorTotal = valorTotal;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}