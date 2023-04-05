package br.com.btg.desafio.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedido", uniqueConstraints = @UniqueConstraint(columnNames = {"codigoPedido", "codigoCliente"}))
@IdClass(PedidoId.class)
public class Pedido {
    @Id
    private Long codigoPedido;
    @Id
    private Long codigoCliente;

    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<ItemPedido> itens;

    private BigDecimal valorTotal;

    public Pedido() {
    }

    public Pedido(Long codigoCliente, BigDecimal valorTotal, List<ItemPedido> itens) {
        this.codigoCliente = codigoCliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
