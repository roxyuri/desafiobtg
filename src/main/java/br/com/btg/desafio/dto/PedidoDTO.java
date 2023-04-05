package br.com.btg.desafio.dto;

import br.com.btg.desafio.anotations.NotEmptyList;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PedidoDTO {
    @NotNull(message = "codigoPedido não pode ser nulo")
    private Long codigoPedido;

    @NotNull(message = "CodigoCliente não pode ser nulo")
    private Long codigoCliente;
    @NotEmptyList(message = "Itens não pode ser nulo ou vazio")
    private List<ItemPedidoDTO> itens;

    public PedidoDTO(Long codigoPedido, Long codigoCliente, List<ItemPedidoDTO> itens) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
        this.itens = itens;
    }

    public PedidoDTO() {
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

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "codigoPedido=" + codigoPedido +
                ", codigoCliente=" + codigoCliente +
                ", itens=" + itens +
                '}';
    }
}