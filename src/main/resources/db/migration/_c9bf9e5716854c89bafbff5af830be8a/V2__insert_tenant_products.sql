-- Produtos para Loja Virtual Brasil
INSERT INTO product (id, name, description, price, active, deleted, created_at)
VALUES
    (gen_random_uuid(), 'Notebook Gamer', 'Notebook com RTX 4060', 4599.90, true, false, NOW()),
    (gen_random_uuid(), 'Mouse Wireless', 'Mouse sem fio ergonômico', 149.90, true, false, NOW()),
    (gen_random_uuid(), 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90, true, false, NOW());
