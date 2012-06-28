package com.ua.shop.web;

import com.ua.shop.model.Attachment;
import com.ua.shop.model.User;
import com.ua.shop.service.AttachmentService;
import com.ua.shop.util.UserUtils;
import com.ua.shop.web.form.ImageUpload;
import com.ua.shop.web.form.UploadItem;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "ajax/test_page";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ImageUpload handle(@ModelAttribute UploadItem uploadItem, HttpServletRequest request)  {
        ImageUpload back = new ImageUpload();
        back.setStatus("fail");

        CommonsMultipartFile file = uploadItem.getFile();

        if (file != null) {
            FileItem fileItem = file.getFileItem();
            String fileName = fileItem.getName();
            byte[] bytes = fileItem.get();
            String contentType = fileItem.getContentType();

            User user = UserUtils.getUser(request);

            if (user != null) {
                try {
                    Attachment attachment = attachmentService.storeFile(user.getId(), fileName, contentType, bytes);
                    String url = request.getContextPath() + "/image/" + user.getId() + "/" + attachment.getName();
                    back.setStatus("success");
                    back.setUrl(url);
                } catch (IOException e) {
                    back.setMessage(e.getMessage());
                }
            }
        }


        return back;
    }


    
}
