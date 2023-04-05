package br.com.btg.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemPedidoDTO {

    @NotBlank(message = "Produto não pode ser nulo ou vazio")
    private String produto;

    @NotNull(message = "quantidade não pode ser nulo")
    private int quantidade;

    @NotNull(message = "preço não pode ser nulo")
    private BigDecimal preco;

    public ItemPedidoDTO(String produto, int quantidade, BigDecimal preco) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemPedidoDTO() {
    }



    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO{" +
                "produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}