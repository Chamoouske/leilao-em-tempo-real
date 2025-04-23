const { Kafka } = require('kafkajs');

const kafka = new Kafka({
    clientId: 'graphql-service',
    brokers: [process.env.KAFKA_BOOTSTRAP_SERVERS]
});

const Query = {
    messages: async (_, { topic, partition, limit }) => {
        const consumer = kafka.consumer({ groupId: 'graphql-query-group' });
        await consumer.connect();
        return [];
    },

    topics: async () => {
        const admin = kafka.admin();
        await admin.connect();
        return admin.listTopics();
    }
};

const Mutation = {
    sendMessage: async (_, { topic, message }) => {
        const producer = kafka.producer();
        await producer.connect();

        await producer.send({
            topic,
            messages: [{
                key: message.key,
                value: message.value,
                partition: message.partition
            }]
        });

        return true;
    }
};

const Subscription = {
    messageReceived: {
        subscribe: async (_, { topic }) => {
            const consumer = kafka.consumer({ groupId: 'graphql-subscription-group' });
            await consumer.connect();
            await consumer.subscribe({ topic });

            return async function* () {
                await consumer.run({
                    eachMessage: async ({ message }) => {
                        this.yield({
                            messageReceived: {
                                key: message.key?.toString(),
                                value: message.value.toString(),
                                partition: message.partition,
                                offset: message.offset,
                                timestamp: message.timestamp
                            }
                        });
                    }
                });
            };
        }
    }
};

module.exports = { Query, Mutation, Subscription };