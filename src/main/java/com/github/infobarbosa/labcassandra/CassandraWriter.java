package com.github.infobarbosa.labcassandra;


import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CassandraWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraWriter.class);

    private static final int LOOP_INTERACTIONS = 10000000;

    public static void main(String[] args) {
        UUID chave = null;
        String valor = null;

        Session session = CassandraConnection.getSession();
        PreparedStatement prepared = session.prepare(
                "insert into chave_valor (chave, valor) values (?,?)")
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);

        BoundStatement bound = null;
        for (int i = 0; i < LOOP_INTERACTIONS; i++) {
            try {
                chave = UUID.randomUUID();
                valor = "Valor correspondente a chave " + chave;

                bound = prepared.bind(chave, valor);

                session.execute(bound);

                LOGGER.info("iteracao " +  i + ": " + chave + " / " + valor);
            }catch(Exception e){
                LOGGER.error("Erro escrevendo chave/valor: "+ chave + " - " + valor, e);
            }
        }
    }
}
