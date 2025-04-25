# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /build

COPY . .
RUN ./mvnw clean package -DskipTests -T 2C

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

# Configuração do ambiente
WORKDIR /app
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0 -XX:MinRAMPercentage=25.0"

# Criação de usuário não-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Cópia do artefato
COPY --from=builder /build/target/*.jar app.jar

# Configuração de healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Exposição da porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=${SPRING_PROFILE:-local}"]