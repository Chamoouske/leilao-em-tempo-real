FROM node:slim AS builder

WORKDIR /app
COPY package*.json ./
RUN npm ci && npm i -g @angular/cli && npm install react --save
COPY . .

ARG GRAPHQL_URL
RUN sed -i "s|%%GRAPHQL_URL%%|${GRAPHQL_URL}|g" src/environments/environment.ts
RUN npm run build -- --configuration=production

FROM nginx:alpine

RUN rm /etc/nginx/conf.d/default.conf
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/leilao-ui /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
