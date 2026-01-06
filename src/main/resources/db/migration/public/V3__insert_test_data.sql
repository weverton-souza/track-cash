INSERT INTO seller (id, name, short_name, tenant_id, deleted, created_at)
VALUES
    ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Loja Virtual Brasil', 'LVB', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', false, NOW()),
    ('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'Marketplace Central', 'MPC', 'c9bf9e57-1685-4c89-bafb-ff5af830be8a', false, NOW());

-- Users
INSERT INTO "user" (id, name, email, password, type, status, verified, seller_tenant_id, deleted, created_at)
VALUES
    -- Usuários do Seller 1 (Loja Virtual Brasil)
    (gen_random_uuid(), 'João Silva', 'joao@lojavirtualbrasil.com', '$2a$10$UB15fN591QF/g1IIB.5B0eRamd4y6XF9vSpTzQb26qJ1570/vP.wC', 'OWNER', 'ACTIVE', true, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', false, NOW()),
    (gen_random_uuid(), 'Maria Santos', 'maria@lojavirtualbrasil.com', '$2a$10$UB15fN591QF/g1IIB.5B0eRamd4y6XF9vSpTzQb26qJ1570/vP.wC', 'FINANCIAL_ANALYST', 'ACTIVE', true, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', false, NOW()),

    -- Usuários do Seller 2 (Marketplace Central)
    (gen_random_uuid(), 'Carlos Oliveira', 'carlos@marketplacecentral.com', '$2a$10$UB15fN591QF/g1IIB.5B0eRamd4y6XF9vSpTzQb26qJ1570/vP.wC', 'OWNER', 'ACTIVE', true, 'c9bf9e57-1685-4c89-bafb-ff5af830be8a', false, NOW()),
    (gen_random_uuid(), 'Ana Costa', 'ana@marketplacecentral.com', '$2a$10$UB15fN591QF/g1IIB.5B0eRamd4y6XF9vSpTzQb26qJ1570/vP.wC', 'ACCOUNTANT', 'PENDING_APPROVAL', false, 'c9bf9e57-1685-4c89-bafb-ff5af830be8a', false, NOW());