package com.ssafy.enjoyTrip.bookmark.model.service;

import com.ssafy.enjoyTrip.bookmark.dto.Bookmark;

import java.sql.SQLException;
import java.util.List;

public interface BookmarkService {

    /**
     * 해당 사용자의 모든 북마크 가져오기
     */
    List<Bookmark> selectByUserUid(int accountUid) throws SQLException;

    /**
     * 북마크 등록
     */
    void addBookmark(Bookmark bookmark) throws SQLException;

    /**
     * 북마크 삭제
     */
    void removeBookmark(int id) throws SQLException;

    /**
     * 이미 등록이 된 북마크 인지 아닌지 체크
     */
    boolean alreadyRegist(Bookmark bookmark) throws SQLException;

    /**
     * 사용자가 작성한 가장 최근 북마크 최대 3개까지 가져오기
     */
    List<Bookmark> recent3Bookmark(int accountUid) throws SQLException;

    /**
     * accountUid와 contentId를 통한 북마크 삭제
     */
    public void removeBookmarkToUidId(Bookmark bookmark) throws SQLException;
}
