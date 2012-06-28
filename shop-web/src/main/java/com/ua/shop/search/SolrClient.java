package com.ua.shop.search;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SolrClient {

    private CommonsHttpSolrServer server;

    public SolrClient(CommonsHttpSolrServer server) {
        this.server = server;
    }

    public <E> List<E> getAll(Class<E> clazz) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");

        QueryResponse response = server.query(query);

        return response.getBeans(clazz);
    }

    public <E> void upload(E bean) throws IOException, SolrServerException {
        server.addBean(bean);
        server.commit();
    }

    public void removeById(String id) throws IOException, SolrServerException {
        server.deleteById(id);
        server.commit();
    }

    public void remove(String query) throws IOException, SolrServerException {
        server.deleteByQuery(query);
        server.commit();
    }
    
}
