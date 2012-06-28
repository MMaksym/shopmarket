package com.ua.shop.service.search;

import com.ua.shop.search.model.ProductItem;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
public class ProductIndexer {

    private CommonsHttpSolrServer server;

    public ProductIndexer() throws MalformedURLException {
        server = new CommonsHttpSolrServer("http://localhost:8080/solr/products");
    }

    public void index(ProductItem item) throws IOException, SolrServerException {
        server.addBean(item);
        server.commit();
    }

    public void delete(String id) throws IOException, SolrServerException {
        server.deleteById(id);
        server.commit();
    }

    public QueryResponse search(SolrParams params) throws SolrServerException {
        QueryResponse response = server.query(params);

        return response;
    }

}
