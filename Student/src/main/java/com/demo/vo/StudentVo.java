package com.demo.vo;

import com.demo.entity.Course;
import com.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private Student student;
    private List<Course> courseList;
}
