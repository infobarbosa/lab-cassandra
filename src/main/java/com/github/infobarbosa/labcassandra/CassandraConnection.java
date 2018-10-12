package com.github.infobarbosa.labcassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraConnection {
    private final static Logger LOGGER = LoggerFactory.getLogger(CassandraConnection.class);

    private static Cluster cluster = null;
    private static Session session = null;

    private static void init(){
        LOGGER.debug("inicializando conexao com cassandra");
        cluster = Cluster.builder()
                    .addContactPoint("192.168.57.111")
                    .build();

        LOGGER.info("inicializando conexao com cassandra");

        LOGGER.debug("inicializando sessao com cassandra");
        session = cluster.connect("lab_cassandra");

        LOGGER.debug("sessao com cassandra inicializada");

    }

    public static Session getSession(){
        if( cluster == null ||  session == null )
            init();

        return session;
    }

}
