package br.com.btg.desafio.entity;

import java.io.Serializable;
import java.util.Objects;

public class PedidoId implements Serializable {

    private Long codigoPedido;
    private Long codigoCliente;

    public PedidoId() {
    }

    public PedidoId(Long codigoPedido, Long codigoCliente) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
    }

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoId pedidoId = (PedidoId) o;
        return Objects.equals(codigoPedido, pedidoId.codigoPedido) &&
                Objects.equals(codigoCliente, pedidoId.codigoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPedido, codigoCliente);
    }
}
