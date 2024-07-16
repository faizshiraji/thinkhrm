package com.hrm.thinkerhouse.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	public void init();

	  public String save(MultipartFile file);

	  public Resource load(String filename);
	  
	  public void deleteAll();

	  public Stream<Path> loadAll();
	
}
