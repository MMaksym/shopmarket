package com.ua.shop.web;

import com.ua.shop.service.search.ProductIndexer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Lotus
 * Date: 25.03.12
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ProductIndexer pI;

    @RequestMapping(method = RequestMethod.GET)
    public String searchPage(HttpServletRequest request) {

        return "searchPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@RequestParam String key, HttpServletRequest request, Model model) {

        SolrQuery query = new SolrQuery(key);
        query.addFacetField("cat_name_sort");

        try {
            QueryResponse response = pI.search(query);
            model.addAttribute("res", response);

        } catch (SolrServerException e) {
            e.printStackTrace();
        }


        return "searchPage";
    }

    @RequestMapping(value = "ajax", method = RequestMethod.POST)
    public @ResponseBody QueryResponse searchJSON(@RequestParam String key, HttpServletRequest request, Model model) {

        SolrQuery query = new SolrQuery(key);
        query.addFacetField("cat_name_sort");

        try {
            QueryResponse response = pI.search(query);

            return response;
        } catch (SolrServerException e) {
            return null;
        }
    }
    
}
