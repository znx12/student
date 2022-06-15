package com.demo.vo;

import com.demo.entity.Course;
import com.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseVo {
    private Course course;
    private List<Student> studentList;
}
