package com.boot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.rest.service.FileUploadService;

@RestController
public class FileUploadController {
     
	@Autowired
	private FileUploadService fileUploadService;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

//		System.out.println("file name : "+file.getName());
//		System.out.println("file content : "+file.getContentType());
//		System.out.println("file size : "+file.getSize());
//		System.out.println("file original name : "+file.getOriginalFilename());
		// validation
		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File..");

			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File Must Be JPEG/Image");
			}
			// upload file
            fileUploadService.uploadFile(file);
			return ResponseEntity.ok("File uploaded Successfully...");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.. ! Please try again..!!");
	}

}
