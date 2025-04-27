FROM node:22-slim AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .

RUN npm i -g @angular/cli

ARG APP_ENV
RUN npm run build -- --configuration=${APP_ENV} --progress=false

FROM nginx:alpine

COPY --from=build /app/dist/leilao-ui/browser /usr/share/nginx/html
COPY nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]