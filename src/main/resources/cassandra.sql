CREATE KEYSPACE lab_cassandra WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;

CREATE TABLE lab_cassandra.chave_valor (
    chave uuid PRIMARY KEY,
    valor text
);
