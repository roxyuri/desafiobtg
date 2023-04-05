package br.com.btg.desafio.response;

import java.math.BigDecimal;

public class ValorTotalResponse {
    private BigDecimal valorTotal;

    public ValorTotalResponse(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
