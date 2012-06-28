package com.ua.shop.web;

import com.ua.shop.model.Attachment;
import com.ua.shop.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/image")
public class ImageController {

    private static Logger logger = Logger.getLogger(ImageController.class);

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping(value = "{userId}/{name}", method = RequestMethod.GET)
    public void getImage(@PathVariable Long userId, @PathVariable String name, HttpServletResponse response) {

        Attachment attachment = attachmentService.get(userId, name);

        if (attachment != null) {
            InputStream inputStream = null;
            try {
                response.setContentType(attachment.getContentType());
                response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getTitle() + "." + attachment.getExtension());

                inputStream = attachmentService.getInputStream(userId, name);
                IOUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException e) {
                logger.error("dddd");
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ignored) {

                    }
                }
            }
        }
    }

    
}
