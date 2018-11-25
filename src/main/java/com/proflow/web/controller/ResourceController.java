package com.proflow.web.controller;

import cn.hutool.core.io.FileUtil;
import com.proflow.entity.ResourceAttachment;
import com.proflow.service.ResourceAttachmentService;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Leonid on 2018/9/3.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceAttachmentService resourceAttachmentService;

    /**
     *
     * @param resourceId 资源id
     * @param type 类型 1 本地 2 远程
     * @param response
     * @举例 http://localhost:8080/resource/show/1/1
     * @return
     */
    @GetMapping("/show/{resourceId}/{type}")
    public Object show(@PathVariable("resourceId")Long resourceId, @PathVariable("type")Integer type, HttpServletResponse response) {
        ResultForm<?> resultForm = null;
        if (resourceId == null || (!type.equals(ResourceAttachment.LOCAL) && !type.equals(ResourceAttachment.REMOTE))) {
            resultForm = ResultForm.createError("参数错误");
        }
        ResourceAttachment resourceAttachment = resourceAttachmentService.selectById(resourceId);
        if (resourceAttachment == null) {
            resultForm = ResultForm.createError("资源不存在");
        }

        try {
            if (type.equals(ResourceAttachment.LOCAL)) {
                File file = new File(resourceAttachment.getFilePath());
                if (!file.exists()) {
                    resultForm = ResultForm.createError("文件不存在");
                }
                byte[] result = FileUtil.readBytes(file);
                response.setContentLength(result.length);
                response.setCharacterEncoding("utf-8");
                if (resourceAttachment.getSuffix().equals("xls") || resourceAttachment.getSuffix().equals("xlsx")) {
                    response.setContentType("application/vnd.ms-excel;charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment; filename="
                            + new String(resourceAttachment.getName().getBytes(), "ISO8859-1"));
                } else if (resourceAttachment.getSuffix().equals("pdf")) {
                    response.setContentType("application/pdf;charset=utf-8");
                } else if (resourceAttachment.getSuffix().equals("doc") || resourceAttachment.getSuffix().equals("docx")) {
                    response.setContentType("application/msword;charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment; filename="
                            + new String(resourceAttachment.getName().getBytes(), "ISO8859-1"));
                }
                //response.setHeader("Content-type", "application/pdf;charset=utf-8");
                response.getOutputStream().write(result);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }
}
