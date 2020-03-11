package com.ldh.student.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseRowModel {

    private Integer id;
    @ExcelProperty(value = "姓名",index = 0)
    private String name;
    @ExcelProperty(value = "住址",index = 1)
    private String addr;
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
    @ExcelProperty(value = "身高",index = 3)
    private String height;
    @ExcelProperty(value = "体重",index = 4)
    private String weight;
}
