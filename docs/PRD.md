# Documento de Requisitos de Produto (PRD)
**Nome do Produto**: Plataforma de Leilões em Tempo Real  
**Versão**: 1.0  
**Data**: [Inserir Data]

---

## **1. Objetivo**
Desenvolver uma plataforma de leilões em tempo real que permita:
- Processamento de lances com alta concorrência e garantia de ordem.
- Atualizações em tempo real para usuários via GraphQL.
- Consistência de dados mesmo sob picos de tráfego.

---

## **2. Stakeholders**
- Equipe de desenvolvimento (backend, frontend, QA).
- Product Manager.
- Equipe de infraestrutura/DevOps.
- Usuários finais (compradores e vendedores).

---

## **3. Requisitos Funcionais**

### **3.1. Processamento de Lances (Kafka)**
- **RF01**: Receber lances via API REST.
    - Validação: Verificar se o lance é maior que o atual, se o leilão está ativo e se o usuário tem saldo.
- **RF02**: Publicar lances no tópico `bids` do Kafka, garantindo ordem por ID do leilão.
    - Chave do Kafka: `auction_id` para garantir ordem sequencial por leilão.
- **RF03**: Consumir lances do Kafka e atualizar o preço atual do leilão no banco de dados.

### **3.2. Atualizações em Tempo Real (GraphQL)**
- **RF04**: Subscrever atualizações de preço via GraphQL Subscription.
    - Exemplo: `subscription { priceUpdated(auctionId: "123") { currentPrice } }`.
- **RF05**: Notificar usuários sobre resultados de leilões (vencedor, preço final).
    - Exemplo: `subscription { auctionEnded(auctionId: "123") { winnerId, finalPrice } }`.

### **3.3. Gestão de Leilões**
- **RF06**: Criar/iniciar/finalizar leilões via API REST.
- **RF07**: Listar leilões ativos, históricos e detalhes (GraphQL Query).

---

## **4. Requisitos Não-Funcionais**

### **4.1. Desempenho e Escalabilidade**
- **RNF01**: Suportar até 10.000 lances/segundo por leilão.
- **RNF02**: Latência máxima de 500ms entre o lance e a atualização.

### **4.2. Consistência e Ordem**
- **RNF03**: Garantir ordem total dos lances dentro do mesmo leilão.
- **RNF04**: Evitar condições de corrida via controle concorrencial (ex: lock otimista no banco).

### **4.3. Segurança**
- **RNF05**: Autenticação JWT para APIs e subscriptions.
- **RNF06**: Criptografia TLS/SSL para todas as comunicações.

---

## **5. Arquitetura**

### **5.1. Componentes Principais**
1. **API Gateway**: REST/GraphQL para receber lances e gerenciar leilões.
2. **Kafka Cluster**: Tópicos `bids` (lances) e `auction_events` (eventos de atualização).
3. **Serviço de Processamento de Lances**: Consome lances do Kafka, valida e atualiza o banco.
4. **Banco de Dados**: PostgreSQL com tabelas `auctions` e `bids` (usar versionamento para lock otimista).
5. **GraphQL Subscriptions**: WebSocket para notificações em tempo real.

### **5.2. Diagrama de Fluxo**
```  
Usuário → [API Gateway] → Kafka → [Serviço de Processamento] → [Banco]  
                          ↓  
[GraphQL Subscription] ← Kafka (auction_events)  
```  

---

## **6. Tratamento de Dificuldades Técnicas**

### **6.1. Garantia de Ordem**
- Usar chave de partição `auction_id` no Kafka para garantir ordem sequencial por leilão.
- Configurar `max.in.flight.requests.per.connection=1` no consumidor Kafka para evitar paralelismo dentro da mesma partição.

### **6.2. Consistência em Alta Concorrência**
- **Lock Otimista**: Adicionar campo `version` na tabela `auctions`. Exemplo:
  ```sql  
  UPDATE auctions SET current_price = {novo_valor}, version = version + 1  
  WHERE auction_id = {id} AND version = {versão_atual};  
  ```  
- **Retry Automático**: Caso a versão mude, reprocessar o lance após pequeno delay.

---

## **7. Protótipo de Código (Java)**

### **7.1. Consumidor Kafka (Exemplo)**
```java  
@KafkaListener(topics = "bids", groupId = "auction-processor")  
public void processBid(ConsumerRecord<String, Bid> record) {  
    Auction auction = auctionRepository.findById(record.key())  
        .orElseThrow();  
    if (record.value().getAmount() > auction.getCurrentPrice()) {  
        auction.setCurrentPrice(record.value().getAmount());  
        auction.setVersion(auction.getVersion() + 1);  
        auctionRepository.save(auction);  
        // Publicar evento no tópico auction_events  
        kafkaTemplate.send("auction_events", auction.getId(), auction);  
    }  
}  
```  

### **7.2. GraphQL Subscription (Exemplo)**
```graphql  
type Subscription {  
    priceUpdated(auctionId: ID!): Auction  
}  
```  

---

## **8. Critérios de Aceitação**
1. Lances são processados na ordem exata de chegada para o mesmo leilão.
2. Usuários recebem atualizações de preço em <1 segundo após o lance.
3. O sistema rejeita lances simultâneos acima do preço atual sem condições de corrida.

---

## **9. Roadmap**
- **Fase 1 (4 semanas)**: MVP com processamento de lances e subscriptions básicas.
- **Fase 2 (2 semanas)**: Otimização de concorrência e testes de carga.
- **Fase 3 (1 semana)**: Piloto com usuários reais.

---

**Aprovação**:
______________________________________  
Product Manager