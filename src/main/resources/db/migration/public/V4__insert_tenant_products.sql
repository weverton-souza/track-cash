-- V4__insert_tenant_products.sql

-- Produtos para Loja Virtual Brasil
INSERT INTO _f47ac10b58cc4372a5670e02b2c3d479.product (id, name, description, price, active, deleted, created_at)
VALUES
    (gen_random_uuid(), 'Notebook Gamer', 'Notebook com RTX 4060', 4599.90, true, false, NOW()),
    (gen_random_uuid(), 'Mouse Wireless', 'Mouse sem fio ergonômico', 149.90, true, false, NOW()),
    (gen_random_uuid(), 'Teclado Mecânico', 'Teclado mecânico RGB', 349.90, true, false, NOW());

-- Produtos para Marketplace Central
INSERT INTO _c9bf9e5716854c89bafbff5af830be8a.product (id, name, description, price, active, deleted, created_at)
VALUES
    (gen_random_uuid(), 'Sofá 3 Lugares', 'Sofá retrátil cinza', 1899.90, true, false, NOW()),
    (gen_random_uuid(), 'Mesa de Jantar', 'Mesa 6 lugares madeira', 1299.90, true, false, NOW()),
    (gen_random_uuid(), 'Cadeira Escritório', 'Cadeira ergonômica preta', 699.90, true, false, NOW());