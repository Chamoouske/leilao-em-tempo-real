FROM node:23-alpine

ARG APP_ENV
ARG GRAPHQL_PORT

ENV NODE_ENV=${APP_ENV}

WORKDIR /app

COPY package*.json ./
RUN npm install --production

COPY . .

EXPOSE ${GRAPHQL_PORT}
CMD ["npm", "start"]