package com.ssafy.enjoyTrip.picture.model.service;

import com.ssafy.enjoyTrip.picture.dto.Picture;
import com.ssafy.enjoyTrip.picture.model.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public boolean insertPicture(Picture picture) throws SQLException {
        return pictureMapper.insertPicture(picture) == 1;
    }

    @Override
    public String picturePath(int id) throws SQLException {
        return pictureMapper.picturePath(id);
    }

    @Override
    public boolean alreadyUseProfile(Picture picture) throws SQLException {
        return pictureMapper.alreadyUseProfile(picture) == 1;
    }

    @Override
    public boolean changeProfilePic(Picture picture) throws SQLException {
        return pictureMapper.changeProfilePic(picture) == 1;
    }

    @Override
    public int idToAccountIdType(Picture picture) throws SQLException {
        return pictureMapper.idToAccountIdType(picture);
    }

    @Override
    public Picture selectFile(int id) throws SQLException {
        return pictureMapper.selectFile(id);
    }

    @Override
    public List<Picture> selectAllByAccountId(String accountId) throws SQLException {
        return pictureMapper.selectAllByAccountId(accountId);
    }

    @Override
    public String makePicName(Picture picture) throws SQLException {
        return picture.getType()+"_"+picture.getAccountId();
    }

    @Override
    public String makeFullRoute(Picture picture) throws SQLException {
        return picture.getRoute()+picture.getPicName();
    }

    @Override
    public List<Picture> getAllAccountProfile(Map<String, List<String>> accountIds) throws SQLException {
        List<Picture> list = new ArrayList<>();
        List<String> ls = accountIds.get("id");
        for(String id : ls){
            Picture picture = new Picture();
            picture.setAccountId(id);
            list.add(picture);
        }
        System.out.println(list);
        return pictureMapper.getAllAccountProfile(accountIds.get("id"));
    }
}
