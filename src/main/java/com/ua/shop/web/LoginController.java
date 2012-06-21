package com.ua.shop.web;

import com.ua.shop.exception.EmailException;
import com.ua.shop.exception.TemplateException;
import com.ua.shop.json.UploadFeed;
import com.ua.shop.model.SecurityUser;
import com.ua.shop.model.User;
import com.ua.shop.service.UserService;
import com.ua.shop.service.manager.TwitpicManager;
import com.ua.shop.service.manager.UserManager;
import com.ua.shop.web.form.ChangePasswordForm;
import com.ua.shop.web.form.ImageUpload;
import com.ua.shop.web.form.UserForm;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private Validator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TwitpicManager twitpicManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView("loginPage");
        return model;
    }

    @RequestMapping(value = "register.html", method = RequestMethod.GET)
    public String registerPage(Model model) {

        model.addAttribute("form", new UserForm());

        return "registerPage";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute UserForm form, Errors errors, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        validator.validate(form, errors);

        if (errors.hasErrors() || existEmail(form.getEmail(), errors)) {
            model.addObject("form", form);
            model.setViewName("registerPage");
            return model;
        }

        Date dateOfBirth = null;
        try {
            dateOfBirth = DATE_FORMAT.parse(form.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = userService.addUser(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPassword(),
                dateOfBirth, form.getGender(), null);

        authenticate(form.getEmail(), form.getPassword());

        model.setViewName("redirect:/");

        return model;
    }



    @RequestMapping(value = "forgot_password", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {

        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value = "forgot_password", method = RequestMethod.POST)
    public ModelAndView forgotPasswordRequest(@RequestParam String email, HttpServletRequest request, ModelAndView model) {
        boolean existUser = userService.existUser(email);

        if (existUser) {
            String url = request.getScheme()+ "://" + request.getServerName() + ":" +
                    (request.getServerPort()) + "/" + request.getContextPath() + "/change_password";

            try {
                userManager.forgetPasswordRequest(email, url);
                model.addObject("successMessage", "Your request successfully sent to your email");
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (EmailException e) {
                e.printStackTrace();
            }

        }

        model.setViewName("forgotPassword");

        return model;
    }

    @RequestMapping(value = "change_password", method = RequestMethod.GET)
    public ModelAndView changePasswordPage(@ModelAttribute ChangePasswordForm form, ModelAndView model, Errors errors) {
        model.setViewName("newPassword");
        model.addObject("form", form);

        boolean isValidRequest = userManager.isValidForgotPasswordRequest(form);
        errors.reject("hash.code.is.not.valid");

        return model;
    }

    @RequestMapping(value = "change_password", method = RequestMethod.POST)
    public ModelAndView newPassword(@ModelAttribute ChangePasswordForm form, Errors errors, ModelAndView model) {

        validator.validate(form, errors);

        if (!errors.hasErrors()) {

            String email = form.getEmail();
            String password = form.getNewPassword();
            boolean success = userManager.changeForgotPassword(email, password, form.getHash());

            if (success) {
                authenticate(email, password);

                model.setViewName("redirect:/");
                return model;
            }
        }

        model.setViewName("newPassword");
        model.addObject("form", form);

        return model;
    }

    private boolean existEmail(String email, Errors errors) {
        if (userService.existUser(email)) {
            errors.rejectValue("email", "email.already.exists", new String[]{email}, "Email " + email + " already exists");
            return true;
        }

        return false;
    }

    @RequestMapping(value = "register/upload", method = RequestMethod.POST)
    public @ResponseBody ImageUpload uploadPhoto(@RequestParam CommonsMultipartFile photo) {
        ImageUpload upload = new ImageUpload();

        FileItem fileItem = photo.getFileItem();

        if (fileItem != null) {
            String fileName = fileItem.getName();
            byte[] bytes = fileItem.get();
            try {

                UploadFeed feed = twitpicManager.upload(bytes, fileName, fileName);

                if (feed != null) {
                    upload.setStatus("success");
                    String imageUrl = TwitpicManager.SHOW_URL + "thumb/" + feed.getId() + "." + feed.getType();
                    upload.setUrl(imageUrl);
                }

            } catch (IOException e) {
                upload.setMessage("IOException sss");
            }

        }

        return upload;
    }


    private void authenticate(String email, String password) {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        authentication = authenticationManager.authenticate(authentication);
        context.setAuthentication(authentication);
    }
}
