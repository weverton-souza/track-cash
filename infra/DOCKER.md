# Docker - Project Track Cash

## Como Rodar
```bash
# Subir os containers
docker-compose --env-file local.env up -d

# Ver logs
docker-compose logs -f

# Parar
docker-compose down

# Resetar (deleta dados do banco)
docker-compose down -v
```

## Build da Imagem
```bash
# Buildar
docker build -t trackcashinfra/project-track-cash-backend:dev .

# Push (opcional)
docker push trackcashinfra/project-track-cash-backend:dev
```

## Acessar

- API: http://localhost:8080
- Banco: localhost:5432

## Configuração

Edite o arquivo `dev.env` com suas credenciais.

**Nunca commite `.env` com senhas reais!**