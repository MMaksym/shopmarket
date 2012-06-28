package com.ua.shop.web.form;

import com.ua.shop.model.Category;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Lotus
 * Date: 26.03.12
 */
public class CategoryJson {

    private String sEcho;
    private String iTotalRecords;
    private String iTotalDisplayRecords;
    private List<List<String>> aaData = new LinkedList<List<String>>();

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(String iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<List<String>> getAaData() {
        return aaData;
    }

    public void addCategory(Category category) {
        List<String> list = new LinkedList<String>();
        list.add(category.getTitle());
        list.add(category.getDescription());
        String actions = createEditLink(category);
        list.add(actions);


        aaData.add(list);
    }

    private String createEditLink(Category category) {
        return "<a href=\"category/" + category.getName() + "\">Edit</a>, " +
                "<a href=\"category/delete?id=" + category.getId() +"\">Remove</a>," +
                "<a href=\"category/"+ category.getName() + ".html\">Products</a>";
    }

}
