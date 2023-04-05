package br.com.btg.desafio.repository;

import br.com.btg.desafio.dto.ClientePedidoCountDTO;
import br.com.btg.desafio.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p.valorTotal FROM Pedido p WHERE p.codigoPedido = :codigoPedido")
    BigDecimal findValorTotalByCodigoPedido(@Param("codigoPedido") Long codigoPedido);

    Optional<Pedido> findByCodigoPedidoAndCodigoCliente(Long codigoPedido, Long codigoCliente);

    Optional<Pedido> findByCodigoPedidoAndCodigoClienteNot(Long codigoPedido, Long codigoCliente);

    Page<Pedido> findByCodigoCliente(Long codigoCliente, Pageable pageable);

    @Query("SELECT NEW br.com.btg.desafio.dto.ClientePedidoCountDTO(p.codigoCliente, COUNT(p)) FROM Pedido p GROUP BY p.codigoCliente")
    Page<ClientePedidoCountDTO> countPedidosByCliente(Pageable pageable);

}