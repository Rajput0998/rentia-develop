package com.rentia.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.ClassPathResource;


@Service
public class FileService {


	public String uploadImage(String path, MultipartFile file, String preName) throws IOException {

		String fullPath = System.getProperty("user.dir") + "/" + path;
		System.out.println("fullPath :- "+fullPath);
		File directory = new File(fullPath);
		if (!directory.exists()) {
			directory.mkdirs(); // Create the directory if it doesn't exist
		}

		if( null == preName)
		{
			preName = "test";
		}
		// File name
		String name = file.getOriginalFilename();
		// abc.png
		System.out.println("FileName :- "+name.substring(name.lastIndexOf(".")));

		// random name generate file
		String randomID = preName + "-" + UUID.randomUUID();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		System.out.println("fileName1 :- "+fileName1);
		// Full path
		String filePath = path + File.separator + fileName1;
		System.out.println("filePath :- "+filePath);
		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return fileName1;
	}
	
	

	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		return is;
	}



	public void deleteImage(String fileName, String path) throws IOException {
		
		String fullPath = path + File.separator + fileName;
		File file1 = new File(fullPath);
		file1.delete();	
	}

}

