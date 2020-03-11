package com.ldh.student.service.impl;

import com.ldh.platform.mybatis.dao.BaseDao;
import com.ldh.platform.mybatis.service.BaseServiceImpl;
import com.ldh.student.dao.StudentDao;
import com.ldh.student.entity.Student;
import com.ldh.student.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Integer> implements StudentService{
    @Resource
    private StudentDao studentDao;

    @Override
    public BaseDao<Student, Integer> getDao() {
        return studentDao;
    }

    @Override
    public List<Student> queryAll(String param) {
        return studentDao.queryAll(param);
    }
}
