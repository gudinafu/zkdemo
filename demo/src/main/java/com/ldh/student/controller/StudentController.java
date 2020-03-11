package com.ldh.student.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.gupao.edu.template.FormatTemplate;
import com.ldh.excel.MyWriteHandler;
import com.ldh.student.entity.Student;
import com.ldh.student.service.StudentService;
import com.ldh.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;



@RestController
@RequestMapping("/student")
@Api(tags = "学生类")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Autowired
    private FormatTemplate formatTemplate;

    @RequestMapping(value = "/export", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "学生信息导出")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String fileName = "学生表";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName + ".xlsx", "utf-8"));

        List<Student> list = studentService.queryAll("老九");

        MyWriteHandler handler = new MyWriteHandler();
        ExcelWriter writer = new ExcelWriter(null, out, ExcelTypeEnum.XLSX, true, handler);
        Sheet sheet1 = new Sheet(1, 0, Student.class);
        sheet1.setSheetName("sheet1");
        writer.write(list, sheet1);
        writer.finish();
        out.flush();
    }

    @RequestMapping(value = "/queryById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据ID查询")
    public Result queryById( Integer id){
        Student student = studentService.selectByPrimaryKey(id);
        return Result.success(student);

    }

    @RequestMapping(value = "/format", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "格式化")
    public String format(){
        Student student = studentService.selectByPrimaryKey(1);
        return formatTemplate.format(student);

    }

}
