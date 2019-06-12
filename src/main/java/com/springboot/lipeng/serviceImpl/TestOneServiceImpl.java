package com.springboot.lipeng.serviceImpl;

import com.springboot.lipeng.dao.TestOneMapper;
import com.springboot.lipeng.model.ParkUser;
import com.springboot.lipeng.service.TestOneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TestOneServiceImpl implements TestOneService {

    @Resource
    private TestOneMapper testOneMapper;

    @Override
    public List<ParkUser> selectAll() {
        return testOneMapper.selectAll();
    }
}
