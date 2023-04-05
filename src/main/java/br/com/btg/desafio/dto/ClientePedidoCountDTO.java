package br.com.btg.desafio.dto;

public class ClientePedidoCountDTO {
    private Long codigoCliente;
    private Long totalPedidos;

    public ClientePedidoCountDTO(Long codigoCliente, Long totalPedidos) {
        this.codigoCliente = codigoCliente;
        this.totalPedidos = totalPedidos;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Long getTotalPedidos() {
        return totalPedidos;
    }

    public void setTotalPedidos(Long totalPedidos) {
        this.totalPedidos = totalPedidos;
    }
}
