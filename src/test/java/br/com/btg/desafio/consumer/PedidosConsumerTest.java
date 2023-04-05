package br.com.btg.desafio.consumer;

import br.com.btg.desafio.dto.ItemPedidoDTO;
import br.com.btg.desafio.dto.PedidoDTO;
import br.com.btg.desafio.service.PedidoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidosConsumerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidosConsumer pedidosConsumer;

    @Mock
    private Validator validator;

    @Test
    public void testConsumePedido() {
        PedidoDTO pedidoDTO = new PedidoDTO(1L, 1L, Arrays.asList(
                new ItemPedidoDTO("Produto 1", 2, new BigDecimal("9.99")),
                new ItemPedidoDTO("Produto 2", 3, new BigDecimal("14.99"))
        ));

        pedidosConsumer.consomePedido(pedidoDTO);

        Mockito.verify(pedidoService, Mockito.times(1)).consumirPedido(pedidoDTO);
    }

    @Test
    public void testPedidoDTOValidation() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        PedidoDTO invalidPedidoDTO = new PedidoDTO(null, null, null);

        Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(invalidPedidoDTO);
        assertEquals(3, violations.size());
    }

    @Test
    public void testItemPedidoDTOValidation() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ItemPedidoDTO invalidItemPedidoDTO = new ItemPedidoDTO("", 1, null);

        Set<ConstraintViolation<ItemPedidoDTO>> violations = validator.validate(invalidItemPedidoDTO);
        assertEquals(2, violations.size());
    }
}
