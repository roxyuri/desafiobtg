package br.com.btg.desafio.repository;

import br.com.btg.desafio.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByPedidoCodigoCliente(Long codigoCliente);
}
