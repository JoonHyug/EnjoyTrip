package com.ssafy.enjoyTrip.picture.controller;

import com.ssafy.enjoyTrip.picture.dto.Picture;
import com.ssafy.enjoyTrip.picture.model.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
@Slf4j
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @PostMapping("/upload")
    public ResponseEntity<?> inputImage(MultipartFile uploadFile, @RequestParam String accountId, @RequestParam String type) throws IOException, SQLException {

        if(!uploadFile.getContentType().startsWith("image")){
            return ResponseEntity.status(500).build();
        }
        Picture picture = new Picture();
        picture.setType(type);
        picture.setAccountId(accountId);
        if(picture.getRoute() == null){
            picture.setRoute("../enjoytripvue/public/img/profile/");
        }
        String originalName = uploadFile.getOriginalFilename(); //원본파일 이름
        String extensionName = originalName.substring(originalName.lastIndexOf(".")); //확장자 이름
        String pictureName = originalName.substring(0, originalName.lastIndexOf(".")); //확장자를 제외한 사진 이름
        picture.setPicName(pictureService.makePicName(picture)+"_"+pictureName+extensionName); //사진 이름 생성
        System.out.println(picture.getPicName());
        picture.setFullRoute(pictureService.makeFullRoute(picture)); //
        String saveFile = picture.getFullRoute();
        System.out.println(picture);
        if(pictureService.alreadyUseProfile(picture)){
            picture.setId(pictureService.idToAccountIdType(picture));
           pictureService.changeProfilePic(picture);
        } else {
           pictureService.insertPicture(picture);
        }
        Path savePath = Paths.get(saveFile);
        uploadFile.transferTo(savePath);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageToId(@PathVariable int id) throws SQLException{
        return ResponseEntity.ok(pictureService.selectFile(id));
    }

    @GetMapping
    public ResponseEntity<?> getImageToTypeAccountId(Picture picture) throws SQLException{
        if(pictureService.alreadyUseProfile(picture)){
            return ResponseEntity.ok(pictureService.idToAccountIdType(picture));
        }
        return ResponseEntity.status(500).build();
    }

    @PostMapping("/allaccount")
    public ResponseEntity<?> getAllAccountProfile(@RequestBody Map<String, List<String>> accountIds) throws SQLException{
        return ResponseEntity.ok(pictureService.getAllAccountProfile(accountIds));
    }

}
