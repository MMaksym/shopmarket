package com.ua.shop.web;

import com.ua.shop.model.Category;
import com.ua.shop.model.Currency;
import com.ua.shop.model.Product;
import com.ua.shop.service.CategoryService;
import com.ua.shop.service.ProductService;
import com.ua.shop.service.dao.CurrencyDao;
import com.ua.shop.service.dao.ProductDao;
import com.ua.shop.web.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Author: Lotus
 * Date: 11.03.12
 */
@Controller
@RequestMapping(value = "category")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private Validator validator;

    @RequestMapping(value = "{categoryName}.html")
    public String viewCategoryProducts(@PathVariable String categoryName, Model model, HttpServletRequest request) {

        Category category = categoryService.getCategory(categoryName);
        List<Product> products = productDao.getByCategory(category);

        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "viewProducts";
    }

    @RequestMapping(value = "{categoryName}/new_product")
    public String newProduct(@PathVariable String categoryName, Model model) {
        return editProduct(categoryName, model, null);
    }
    
    @RequestMapping(value = "{categoryName}/{productName}.html")
    public String editProduct(@PathVariable String categoryName, Model model, @PathVariable String productName) {

        Product product = null;
        if (productName != null) {
            product = productDao.get(categoryName, productName);
        }

        List<Currency> currencies = currencyDao.getAll();

        ProductForm productForm = new ProductForm(product);
        productForm.setCategoryName(categoryName);
        model.addAttribute("product", productForm);
        model.addAttribute("currencies", currencies);

        return "editProduct";
    }

    @RequestMapping(value = "{categoryName}/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@PathVariable String categoryName, @ModelAttribute ProductForm productForm, Model model, Errors errors) {

        validator.validate(productForm, errors);

        if (errors.hasErrors()) {
            List<Currency> currencies = currencyDao.getAll();

            model.addAttribute("product", productForm);
            model.addAttribute("currencies", currencies);

            return "editProduct";
        }

        productService.saveProduct(productForm);

        return "redirect:/category/" + categoryName + ".html";
    }
    
}
