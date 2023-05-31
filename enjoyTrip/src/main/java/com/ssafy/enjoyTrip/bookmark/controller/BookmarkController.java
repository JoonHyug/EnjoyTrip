package com.ssafy.enjoyTrip.bookmark.controller;

import com.ssafy.enjoyTrip.attraction.dto.Info;
import com.ssafy.enjoyTrip.attraction.model.service.InfoService;
import com.ssafy.enjoyTrip.bookmark.dto.Bookmark;
import com.ssafy.enjoyTrip.bookmark.model.service.BookmarkService;
import com.ssafy.enjoyTrip.search.model.service.ContentTypeService;
import com.ssafy.enjoyTrip.user.model.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bookmark")
@CrossOrigin("*")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private UserService userService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private ContentTypeService contentTypeService;

    @GetMapping("/{accountId}")
    public ResponseEntity<?> selectAllByAccountId(@PathVariable String accountId) throws SQLException {
        List<Bookmark> bookmarkList = bookmarkService.selectByUserUid(userService.getUserUidToId(accountId));
        List<Map<String, Object>> data = new ArrayList<>();
        for(Bookmark bookmark : bookmarkList){
            Info info = infoService.selectByContentId(bookmark.getContentId());
            Map<String, Object> temp = new HashMap<>();
            temp.put("contentId", bookmark.getContentId());
            temp.put("contentTypeName", contentTypeService.getNamebyContentId(info.getContentTypeId()).getName());
            temp.put("bookmarkId", bookmark.getId());
            temp.put("title", info.getTitle());
            temp.put("addr1", info.getAddr1());
            temp.put("firstImage", info.getFirstImage());
            temp.put("latitude", info.getLatitude());
            temp.put("longitude", info.getLongitude());
            data.add(temp);
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<?> addBookmark(@RequestBody Bookmark bookmark) throws SQLException{
        bookmark.setAccountUid(userService.getUserUidToId(bookmark.getAccountId()));
        if(bookmarkService.alreadyRegist(bookmark)){
            bookmarkService.removeBookmarkToUidId(bookmark);
            return ResponseEntity.status(500).build();
        }
        bookmarkService.addBookmark(bookmark);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) throws SQLException{
        bookmarkService.removeBookmark(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check")
    public ResponseEntity<?> alreadyRegistCheck(@RequestBody Bookmark bookmark) throws SQLException{
        bookmark.setAccountUid(userService.getUserUidToId(bookmark.getAccountId()));
       return  ResponseEntity.ok(bookmarkService.alreadyRegist(bookmark));
    }

    @GetMapping("/recent/{accountId}")
    public ResponseEntity<?> recentBookmark(@PathVariable String accountId) throws SQLException{
        List<Bookmark> bookmarkList = bookmarkService.recent3Bookmark(userService.getUserUidToId(accountId));
        List<Map<String, Object>> data = new ArrayList<>();
        for(Bookmark bookmark : bookmarkList){
            Info info = infoService.selectByContentId(bookmark.getContentId());
            Map<String, Object> temp = new HashMap<>();
            temp.put("contentId", bookmark.getContentId());
            temp.put("bookmarkId", bookmark.getId());
            temp.put("title", info.getTitle());
            temp.put("addr1", info.getAddr1());
            temp.put("firstImage", info.getFirstImage());
            data.add(temp);
        }
        return ResponseEntity.ok(data);

    }

}
