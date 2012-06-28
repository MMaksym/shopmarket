package com.ua.shop.web;

import com.ua.shop.model.Attachment;
import com.ua.shop.model.User;
import com.ua.shop.service.AttachmentService;
import com.ua.shop.util.UserUtils;
import com.ua.shop.web.form.UploadItem;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUploadForm(Model model, HttpServletRequest request) {
        User user = UserUtils.getUser(request);

        if (user != null) {
            List<Attachment> attachments = attachmentService.getAttachments(user.getId());

            model.addAttribute("attachments", attachments);
        }

        return "upload/upload_form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String upload(@ModelAttribute UploadItem uploadItem, HttpServletRequest request, Model model) {

        CommonsMultipartFile file = uploadItem.getFile();

        if (file != null) {
            FileItem fileItem = file.getFileItem();
            String fileName = fileItem.getName();
            byte[] bytes = fileItem.get();
            String contentType = fileItem.getContentType();

            User user = UserUtils.getUser(request);

            if (user != null) {
                try {
                    attachmentService.storeFile(user.getId(), fileName, contentType, bytes);
                    model.addAttribute("form", uploadItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return "redirect:upload";
    }

    @RequestMapping(value = "{userId}/{name}", method = RequestMethod.GET)
    public String removeFile(@PathVariable Long userId, @PathVariable String name,
                             @RequestParam(required = true) String remove) {
        try {
            attachmentService.removeFile(userId, name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/upload";
    }

}
