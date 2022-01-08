package com.ray.ecommerce.controller;

import com.ray.ecommerce.constant.FileConstant;
import com.ray.ecommerce.dao.VideoCatRepository;
import com.ray.ecommerce.domain.HttpResponse;
import com.ray.ecommerce.entity.PageInfo;
import com.ray.ecommerce.entity.VideoCategories;
import com.ray.ecommerce.exception.*;
import com.ray.ecommerce.service.VideoCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private VideoCategoriesService categoriesService;
    private VideoCatRepository catRepository;

    @Autowired
    public CategoriesController(VideoCategoriesService categoriesService, VideoCatRepository catRepository) {
        this.categoriesService = categoriesService;
        this.catRepository = catRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllVideos(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue= "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<VideoCategories> categories = catRepository.findAll(paging);

        PageInfo myPage = new PageInfo(categories.getNumber(), categories.getTotalElements(), categories.getTotalPages(), categories.getSize());

        Map<String, Object> response = new HashMap<>();
        response.put("categories", categories);
        response.put("page", myPage);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<VideoCategories> addNewCategories(
            @RequestParam("title") String title,
            @RequestParam("alias") String alias,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("weight") String weight,
            @RequestParam("keywords") String keywords) throws IOException, NotAnImageFileException {

        VideoCategories newCategories = categoriesService.addCategory(
                title,alias,description,image,
                Integer.parseInt(weight),
                keywords
        );
        return new ResponseEntity<>(newCategories, HttpStatus.OK);
    }
    // display profile Image
    @GetMapping(path = "/image/{title}/{title}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("title") String title, @PathVariable("image") String image)
            throws IOException {
        // /user/ray/springAngularEcommerce2/users/
        return Files.readAllBytes(Paths.get(FileConstant.USER_FOLDER + title + "/" + image));
    }

    @GetMapping(path = "/image/category/{title}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("title") String title) throws IOException {
        // http://localhost:8080/api/user/image/profile/{username}  => https://robohash.org/{username}
        URL url = new URL(FileConstant.DEFAULT_VIDEO_IMAGE_PATH + title);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            byte[] chunk = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    @PostMapping("/update")
    public ResponseEntity<VideoCategories> updateCategories(
            @RequestParam("currentAlias") String currentAlias,
            @RequestParam("title") String title,
            @RequestParam("alias") String alias,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image,
            @RequestParam("keywords") String keywords) throws IOException, NotAnImageFileException, AliasExistException {

        VideoCategories updateCategories = categoriesService.updateCategory(
                currentAlias,title,alias,description,image,
                keywords
        );
        return new ResponseEntity<>(updateCategories, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteCategory(@PathVariable("id") Long id) throws UserNotFoundException, IOException {
        categoriesService.deleteCategory(id);
        return response(HttpStatus.OK, "Category has been deleted successfully");
    }


    @PostMapping("/updateProfileImage")
    public ResponseEntity<VideoCategories> updateProfileImage(@RequestParam("image") MultipartFile image)
            throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException, AliasExistException {
        String title = SecurityContextHolder.getContext().getAuthentication().getName();
        VideoCategories categories = categoriesService.updateProfileImage(title, image);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(),
                httpStatus, httpStatus.getReasonPhrase(), message), httpStatus);
    }
}
