package com.ssafy.enjoyTrip.bookmark.model.service;

import com.ssafy.enjoyTrip.bookmark.dto.Bookmark;
import com.ssafy.enjoyTrip.bookmark.model.mapper.BookmarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkMapper bookmarkMapper;

    @Autowired
    public BookmarkServiceImpl(BookmarkMapper bookmarkMapper){
        this.bookmarkMapper = bookmarkMapper;
    }

    @Override
    public List<Bookmark> selectByUserUid(int accountUid) throws SQLException {
        return bookmarkMapper.selectByUserUid(accountUid);
    }

    @Override
    public void addBookmark(Bookmark bookmark) throws SQLException {
        bookmarkMapper.addBookmark(bookmark);
    }

    @Override
    public void removeBookmark(int id) throws SQLException {
    bookmarkMapper.removeBookmark(id);
    }

    /**
     * true면 이미 등록한 북마크가 존재, false면 등록한 북마크가 없음
     */
    @Override
    public boolean alreadyRegist(Bookmark bookmark) throws SQLException {
        return bookmarkMapper.alreadyRegist(bookmark) >= 1;
    }

    @Override
    public List<Bookmark> recent3Bookmark(int accountUid) throws SQLException {
        return bookmarkMapper.recent3Bookmark(accountUid);
    }

    @Override
    public void removeBookmarkToUidId(Bookmark bookmark) throws SQLException {
        bookmarkMapper.removeBookmarkToUidId(bookmark);
    }
}
