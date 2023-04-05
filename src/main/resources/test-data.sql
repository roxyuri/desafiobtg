-- Inserção de dados de exemplo para pedidos
INSERT INTO pedido (codigo_cliente, codigo_pedido, valor_total)
VALUES (1, 1001, 35.97)
    ON CONFLICT DO NOTHING;

INSERT INTO pedido (codigo_cliente, codigo_pedido, valor_total)
VALUES (2, 1002, 42.93)
    ON CONFLICT DO NOTHING;

INSERT INTO pedido (codigo_cliente, codigo_pedido, valor_total)
VALUES (3, 1003, 29.99)
    ON CONFLICT DO NOTHING;

-- Inserção de dados de exemplo para itens de pedido
INSERT INTO item_pedido (id, produto, quantidade, preco, codigo_cliente, codigo_pedido)
VALUES (1, 'Produto 1', 2, 9.99, 1, 1001)
    ON CONFLICT DO NOTHING;

INSERT INTO item_pedido (id, produto, quantidade, preco, codigo_cliente, codigo_pedido)
VALUES (2, 'Produto 3', 1, 19.99, 2, 1002)
    ON CONFLICT DO NOTHING;

INSERT INTO item_pedido (id, produto, quantidade, preco, codigo_cliente, codigo_pedido)
VALUES (3, 'Produto 4', 4, 4.99, 3, 1003)
    ON CONFLICT DO NOTHING;