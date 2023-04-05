package br.com.btg.desafio.consumer;

import br.com.btg.desafio.dto.ItemPedidoDTO;
import br.com.btg.desafio.dto.PedidoDTO;
import br.com.btg.desafio.service.PedidoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PedidosConsumer {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(PedidosConsumer.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consomePedido(PedidoDTO pedidoDTO) {
        logger.info("Consumindo pedido {}.", pedidoDTO);

        Set<ConstraintViolation<?>> violations = new HashSet<>(validator.validate(pedidoDTO));

        if (pedidoDTO.getItens() != null) {
            for (ItemPedidoDTO item : pedidoDTO.getItens()) {
                violations.addAll(validator.validate(item));
            }
        }

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            logger.error("Erro de validação no pedido: {}", errorMessage);
            return;

        }

        pedidoService.consumirPedido(pedidoDTO);
    }
}
