-- V3__insert_tenant_products.sql

-- Produtos para Marketplace Central
INSERT INTO product (id, name, description, price, active, deleted, created_at)
VALUES
    (gen_random_uuid(), 'Sofá 3 Lugares', 'Sofá retrátil cinza', 1899.90, true, false, NOW()),
    (gen_random_uuid(), 'Mesa de Jantar', 'Mesa 6 lugares madeira', 1299.90, true, false, NOW()),
    (gen_random_uuid(), 'Cadeira Escritório', 'Cadeira ergonômica preta', 699.90, true, false, NOW());
