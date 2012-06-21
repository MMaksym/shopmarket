package com.ua.shop.web;

import com.ua.shop.model.Category;
import com.ua.shop.model.Product;
import com.ua.shop.model.constants.RoleConstants;
import com.ua.shop.service.CategoryService;
import com.ua.shop.service.dao.CategoryDao;
import com.ua.shop.service.dao.ProductDao;
import com.ua.shop.util.NameConverterUtils;
import com.ua.shop.web.form.CategoryJson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Validator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model) {

        return "viewCategory";
    }

    @RequestMapping(value = "new_category", method = RequestMethod.GET)
    public String newCategory() {

        return "editCategory";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, @PathVariable("name") String categoryName, Model model) {

        Category category = categoryService.getCategory(categoryName);

        if (category == null) {
            logger.error("Category with name [" + categoryName + "] not found");
            return "redirect:category";
        }

        model.addAttribute("category", category);

        return "editCategory";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute Category category, Errors errors, HttpServletRequest request, Model model) {

        validator.validate(category, errors);

        if (errors.hasErrors()) {
            model.addAttribute("category", category);
            return "editCategory";
        }

        Long id = category.getId();
        String name = NameConverterUtils.convert(category.getTitle());

        Category updatableCategory = categoryService.getCategory(name);

        if ((id == null && updatableCategory != null) || (id != null && updatableCategory != null && !updatableCategory.getId().equals(id))) {
            errors.rejectValue("title", "category.name.already.exists");
            return "editCategory";
        }

        if (id == null) {
            category.setName(name);
            categoryService.saveOrUpdateCategory(category);
        } else {
            Category cat = categoryService.getCategory(id);
            cat.setTitle(category.getTitle());
            cat.setName(name);
            cat.setDescription(category.getDescription());
            categoryService.saveOrUpdateCategory(cat);
        }

        return "redirect:category";
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @Secured(value = RoleConstants.ADMIN)
    public String delete(@RequestParam(required = true, defaultValue = "0") Long id, HttpServletRequest request) {

        if (id > 0) {
            categoryService.removeCategory(id);
        }

        return "redirect:/category";
    }

    @RequestMapping(value = "ajax")
    public @ResponseBody CategoryJson viewJson(HttpServletRequest request) {
        String displayStart = request.getParameter("iDisplayStart");
        String displayLength=request.getParameter("iDisplayLength");
        String sEcho = request.getParameter("sEcho");

        CategoryJson json = new CategoryJson();

        int start = Integer.parseInt(displayStart);
        int length = Integer.parseInt(displayLength);

        int count = categoryService.count();

        List<Category> categories = categoryService.getCategories(start, length);

        json.setsEcho(sEcho);
        json.setiTotalRecords(String.valueOf(count));
        json.setiTotalDisplayRecords(String.valueOf(count));

        for (Category category : categories) {
            json.addCategory(category);
        }

        return json;
    }



}
