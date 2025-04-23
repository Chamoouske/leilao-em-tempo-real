const { ApolloServer } = require('apollo-server');
const { readFileSync } = require('fs');
const { join } = require('path');
const { Query, Mutation, Subscription } = require('./resolver');

const typeDefs = readFileSync(
  join(__dirname, 'schema.graphql'),
  'utf-8'
);

const resolvers = {
  Query,
  Mutation,
  Subscription
};

const server = new ApolloServer({ typeDefs, resolvers });

server.listen({ port: 4000 }).then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});
