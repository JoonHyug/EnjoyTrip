package com.ssafy.enjoyTrip.attraction.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoyTrip.search.model.service.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoyTrip.attraction.dto.Info;
import com.ssafy.enjoyTrip.attraction.model.mapper.InfoMapper;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoMapper infoMapper;

	@Autowired
	private ContentTypeService contentTypeService;

	@Override
	public List<Info> SearchBySidoGun(Map<String, Object> map) throws SQLException {
		return infoMapper.SearchBySidoGun(map);
	}

	@Override
	public List<Info> SearchBySidoGunKeyword(Map<String, Object> map) throws SQLException {
		return infoMapper.SearchBySidoGunKeyword(map);
	}

	@Override
	public Info selectByContentId(int contentId) throws SQLException {
		return infoMapper.selectByContentId(contentId);
	}

	@Override
	public void increaseReadCount(int contentId) throws SQLException {
		infoMapper.increaseReadCount(contentId);
	}

	@Override
	public void addInfo(Info info) throws SQLException {
		infoMapper.addInfo(info);
	}

	@Override
	public boolean isContentIdExist(int contentId) throws SQLException {
		return infoMapper.isContentIdExist(contentId) >= 1;
	}

	@Override
	public Info getInfo(int contentId) throws SQLException {
		Info info = infoMapper.getInfo(contentId);
		info.setContentTypeName(contentTypeService.getNamebyContentId(info.getContentTypeId()).getName());
		System.out.println(info);
		return info;
	}

	@Override
	public List<Info> getHitTop3() throws SQLException {
		return infoMapper.getHitTop3();
	}

}
