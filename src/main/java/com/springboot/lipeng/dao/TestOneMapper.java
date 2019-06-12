package com.springboot.lipeng.dao;

import com.springboot.lipeng.model.ParkUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestOneMapper {
    List<ParkUser> selectAll();
}
