package com.example.uploadingfiles.rest;

import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest")
public class FileUploadRestController {

	private final StorageService storageService;

	@Autowired
	public FileUploadRestController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/files")
	public List<String> listUploadedFiles(Model model) throws IOException {

		Stream<Path> pathStream = storageService.loadAll();
		Stream<String> serveFile = pathStream.map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadRestController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString());
		List<String> collect = serveFile
				.collect(Collectors.toList());

		return collect;
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		String s = "attachment; filename=\"" + URLEncoder.encode(file.getFilename()) + "\"";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,s)
				.contentType(MediaType.APPLICATION_JSON_UTF8).body(file);
	}

	@PostMapping("/files")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {

		storageService.store(file);

		return "success";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
