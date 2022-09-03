package com.example.uploadingfiles.rest;

import com.example.uploadingfiles.dto.UploadFileDto;
import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class FileUploadRestController {

    private final StorageService storageService;

    @Autowired
    public FileUploadRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files")
    public List<String> listUploadedFiles() throws IOException {

        return storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(FileUploadRestController.class,
                                "downloadFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        String s = "attachment; filename=\"" + URLEncoder.encode(file.getFilename()) + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, s)
                .contentType(MediaType.APPLICATION_JSON_UTF8).body(file);
    }

    /**
     * 如果上传单个文件，可以写单个multipartFile即可，但是也用List，List兼容
     *
     * @param files 待上传的文件
     * @return 返回上传结果
     */
    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            storageService.store(file);
        }

        return "success";
    }


    /**
     * 如果不指定requestParam, requestBody 啥的，spring默认注入
     *
     * @param uploadFileDto 待上传的文件对象
     * @return 返回成功标记
     */
    @PostMapping("/files/chunks")
    public String uploadChunkFiles(UploadFileDto uploadFileDto) {
        System.out.println(uploadFileDto);
        if (uploadFileDto.getTotalChunks() == 1 || (uploadFileDto.getTotalChunks() == 0 && Objects.nonNull(uploadFileDto.getFile()))) {
            storageService.store(uploadFileDto.getFile());
        }
        return "success";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
