FROM node:22-slim AS build

WORKDIR /app
COPY . .
RUN npm ci && npm i -g @angular/cli

ARG GRAPHQL_URL
RUN sed -i "s|%%GRAPHQL_URL%%|${GRAPHQL_URL}|g" src/environments/environment.ts
RUN npm run build -- --configuration=production

FROM nginx:alpine

COPY --from=build /app/dist/leilao-ui/browser /usr/share/nginx/html
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]