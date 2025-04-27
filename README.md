# Plataforma de Leilões em Tempo Real

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/)
[![Docker](https://img.shields.io/badge/Docker-24.0%2B-2496ED)](https://www.docker.com/)
[![Kafka](https://img.shields.io/badge/Apache_Kafka-3.5%2B-231F20)](https://kafka.apache.org/)

Uma plataforma escalável para leilões em tempo real, com processamento de lances via Kafka e atualizações via GraphQL.  
**Destaques**: Ordem garantida de lances, alta concorrência e subsicrições em tempo real.

---

## 📋 Índice
- [Funcionalidades](#-funcionalidades)
- [Stack Tecnológica](#-stack-tecnológica)
- [Configuração do Ambiente](#%EF%B8%8F-configuração-do-ambiente)
  - [Variáveis de Ambiente](#variáveis-de-ambiente)
  - [Docker](#-docker)
- [Documentação da API](#-documentação-da-api)
- [Contribuição](#-contribuição)
- [Licença](#-licença)

---

## 🚀 Funcionalidades
- **Lances em Tempo Real**: Processamento de lances com Kafka, garantindo ordem e consistência.
- **Subscrições GraphQL**: Notificações instantâneas de atualizações de preço e resultados.
- **Gestão de Leilões**: Criação, listagem e encerramento de leilões via API REST.
- **Autenticação JWT**: Acesso seguro às APIs e subscrições.

---

## 🛠 Stack Tecnológica
- **Backend**: Java 17+, Spring Boot 3.1, GraphQL
- **Streaming**: Apache Kafka
- **Banco de Dados**: PostgreSQL
- **Infraestrutura**: Docker, Docker Compose
- **Autenticação**: JWT

---

## ⚙️ Configuração do Ambiente

### Variáveis de Ambiente
Antes de executar, crie um arquivo `.env` na raiz do projeto seguindo o modelo [.env.example](.env.example):  
```ini
# profiles das aplicações
APP_ENV=development
COMPOSE_PROFILES=dev

# endereços de acesso ao Kafka
KAFKA_UI_PORT=9000
KAFKA_BOOTSTRAP_SERVERS=kafka:9092

# configurações de portas e endereços para acessar o GraphQL
API_PORT=7001
FRONTEND_PORT=8080
URL_ACCESS=http://localhost:${FRONTEND_PORT}

# configurações de acesso ao DB
DB=banco
DB_USER=USR_APLICACAO
DB_PSW=Senha1234
DB_PORT=5432
DB_HOST=postgresql://db:${DB_PORT}/${DB}
```

### 🐳 Docker
O projeto usa perfis do Docker para diferentes ambientes. Comandos disponíveis:

1. **Desenvolvimento** (com hot-reload e logs detalhados):
```bash
docker-compose --profile dev up --build
```

2. **Produção** (otimizado para desempenho):
```bash
docker-compose --profile prod up --build
```

3. **Testes** (executa testes de integração):
```bash
docker-compose --profile test up --build
```

> **Serviços incluídos**:
> - Aplicação Java (porta `8080`)
> - PostgreSQL (porta `5432`)
> - Kafka + Zookeeper (portas `9092`, `2181`)

---

## 📚 Documentação da API
### Endpoints REST
| Método | Caminho           | Descrição               |
|--------|-------------------|-------------------------|
| POST   | `/api/bids`       | Enviar lance            |
| POST   | `/api/auctions`   | Criar leilão            |
| GET    | `/api/auctions`   | Listar leilões ativos   |

### Subscrições GraphQL
```graphql
subscription {
  priceUpdated(auctionId: "123") {
    currentPrice
  }
}

subscription {
  auctionEnded(auctionId: "123") {
    winnerId
    finalPrice
  }
}
```

---

## 🤝 Contribuição
1. Faça um fork do repositório.
2. Crie uma branch: `git checkout -b minha-feature`.
3. Commit suas mudanças: `git commit -m 'Adicionei algo incrível!'`.
4. Push para a branch: `git push origin minha-feature`.
5. Abra um Pull Request.

---

## 📄 Licença
Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para mais detalhes.
