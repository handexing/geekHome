package com.geekhome.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.geekhome.common.vo.MarkdownUploadImage;
import com.geekhome.entity.dao.LabelDao;

@RestController
@RequestMapping("questionAnswers")
public class QuestionAnswersController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${upload.path}")
	String upload_dir;

	@Autowired
	LabelDao labelDao;

	@RequestMapping(value = "/uploadImage")
	@CrossOrigin
	public MarkdownUploadImage uploadImage( @RequestParam("editormd-image-file") MultipartFile file)
			throws IOException {
		try {
			String fileName = file.getOriginalFilename();
			String realPath = new Date().getTime() + fileName.substring(fileName.lastIndexOf("."));
			File tempFile = new File(upload_dir + realPath);
			if (!tempFile.getParentFile().exists()) {
				tempFile.mkdirs();
			}
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(tempFile));
			return new MarkdownUploadImage(1, "上传成功！", realPath);
		} catch (final Exception e) {
			logger.error("", e);
			return new MarkdownUploadImage(0, "上传失敗!", null);
		}
	}

}
