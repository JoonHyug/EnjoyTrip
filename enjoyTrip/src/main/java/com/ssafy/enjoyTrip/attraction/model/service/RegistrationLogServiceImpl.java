package com.ssafy.enjoyTrip.attraction.model.service;

import com.ssafy.enjoyTrip.attraction.dto.RegistrationLog;
import com.ssafy.enjoyTrip.attraction.model.mapper.RegistrationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RegistrationLogServiceImpl implements RegistrationLogService{

    @Autowired
    private RegistrationLogMapper registrationLogMapper;

    @Override
    public void addRegistrationLog(RegistrationLog registrationLog) throws SQLException {
        registrationLogMapper.addRegistrationLog(registrationLog);
    }

    @Override
    public List<RegistrationLog> selectByContentId(int contentId) throws SQLException {
        return registrationLogMapper.selectByContentId(contentId);
    }

    @Override
    public void changeCheckToTrue(int contentId) throws SQLException {
        registrationLogMapper.changeCheckToTrue(contentId);
    }

    @Override
    public RegistrationLog alreadyRegistration(RegistrationLog registrationLog) throws SQLException {
        return registrationLogMapper.alreadyRegistration(registrationLog);
    }
}
