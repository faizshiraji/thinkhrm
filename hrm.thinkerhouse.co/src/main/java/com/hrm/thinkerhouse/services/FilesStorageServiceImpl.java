package com.hrm.thinkerhouse.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FileUploadService {

	private final Path root = Paths.get("./src/main/resources/static/img/uploads/");
	
	@Override
	  public void init() {
	    try {
	      Files.createDirectories(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	@Override
	  public String save(MultipartFile file) {
	    try {
	    	
	    	String newFileName = generateNewFileName(file.getOriginalFilename());
	    	
	      Files.copy(file.getInputStream(), this.root.resolve(newFileName));
	      
	      return newFileName;
	      
	    } catch (Exception e) {
	      if (e instanceof FileAlreadyExistsException) {
	        throw new RuntimeException("A file of that name already exists.");
	      }

	      throw new RuntimeException(e.getMessage());
	    }
	  }

	private String generateNewFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String randomId = UUID.randomUUID().toString().replace("-", "");
        return "upload_" + dateTime + randomId + extension;
    }
	
	@Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }

	@Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }

	@Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }

}
