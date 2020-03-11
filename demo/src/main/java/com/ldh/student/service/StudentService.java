package com.ldh.student.service;

import com.ldh.platform.mybatis.service.BaseService;
import com.ldh.student.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student,Integer> {

    List<Student> queryAll(String param);
}
