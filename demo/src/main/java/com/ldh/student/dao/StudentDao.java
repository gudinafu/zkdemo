package com.ldh.student.dao;

import com.ldh.platform.mybatis.dao.BaseDao;
import com.ldh.student.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student,Integer>{

    List<Student> queryAll(String params);
}
