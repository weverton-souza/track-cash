CREATE TABLE IF NOT EXISTS "user" (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL DEFAULT 'OWNER',
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING_APPROVAL',
    verified BOOLEAN DEFAULT FALSE,
    seller_tenant_id UUID,
    deleted BOOLEAN DEFAULT FALSE,
    created_by UUID,
    created_at TIMESTAMP,
    updated_by UUID,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_seller FOREIGN KEY (seller_tenant_id) REFERENCES seller(tenant_id),
    CONSTRAINT fk_user_created_by FOREIGN KEY (created_by) REFERENCES "user"(id),
    CONSTRAINT fk_user_updated_by FOREIGN KEY (updated_by) REFERENCES "user"(id)
);

CREATE INDEX idx_user_email ON "user"(email);
CREATE INDEX idx_user_seller_tenant ON "user"(seller_tenant_id);