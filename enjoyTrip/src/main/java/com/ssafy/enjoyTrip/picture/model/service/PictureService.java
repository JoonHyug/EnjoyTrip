package com.ssafy.enjoyTrip.picture.model.service;

import com.ssafy.enjoyTrip.picture.dto.Picture;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PictureService {
    /**
     * DB에 이미지 파일 저장
     * 저장 형식
     * 사진 이름(pic_name) : (type)_(account_id)_(id)
     * 전체 경로(full_route) : (route)+(pic_name)
     */
    public boolean insertPicture(Picture picture) throws SQLException;

    /**
     * id를 통해
     * DB에 저장된 full_route를 반환
     */
    public String picturePath(int id) throws SQLException;

    /**
     * type과 account_id를 통해 이미 존재하는 프로필인지 확인
     */
    public boolean alreadyUseProfile(Picture picture) throws SQLException;

    /**
     * 프로필 사진 수정
     * type과 account_id를 통해
     * pic_name 변경, full_route도 같이 변경 되야 함
     */
    public boolean changeProfilePic(Picture picture) throws SQLException;

    /**
     *type과 account_id를 통해
     * 해당 사진의 id를 반환
     */
    public int idToAccountIdType(Picture picture) throws SQLException;

    /**
     * id를 통한 전체 조회
     */
    public Picture selectFile(int id) throws SQLException;

    /**
     * account_id(사용자)가 작성한 모든 리뷰 type(리뷰)를 반환
     */
    public List<Picture> selectAllByAccountId(String accountId) throws SQLException;

    /**
     * pic_name을 만들어 주는 메소드
     */
    public String makePicName(Picture picture) throws SQLException;

    /**
     * full_route를 만들어 주는 메소드
     */
    public String makeFullRoute(Picture picture) throws SQLException;

    /**
     *  입력받은 모든 id의 profile 주소를 반환
     */
    public List<Picture> getAllAccountProfile(Map<String, List<String>> accountIds) throws SQLException;

}
