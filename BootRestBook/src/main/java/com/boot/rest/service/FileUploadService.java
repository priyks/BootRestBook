package com.boot.rest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private  final String FILE_PATH="C:\\Users\\priyankaku\\spring-workspace\\BootRestBook\\src\\main\\resources\\static\\image";
	boolean upload=false;
	public boolean uploadFile(MultipartFile file) {
		try {
//			InputStream fileStream=file.getInputStream();
//			byte [] data=new byte[fileStream.available()];
//			FileOutputStream fos=new FileOutputStream(FILE_PATH+File.separator+file.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();
			Files.copy(file.getInputStream(),Paths.get(FILE_PATH+File.separator+file.getOriginalFilename() ),StandardCopyOption.REPLACE_EXISTING);
			upload=true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return upload;
	}
}
