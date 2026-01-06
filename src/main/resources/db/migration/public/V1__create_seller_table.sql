CREATE TABLE IF NOT EXISTS seller (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    short_name VARCHAR(50),
    tenant_id UUID UNIQUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_by UUID,
    created_at TIMESTAMP,
    updated_by UUID,
    updated_at TIMESTAMP
);