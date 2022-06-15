package com.demo.service;

import com.demo.entity.Student;
import com.demo.entity.StudentExample;
import com.demo.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;


    public List<Student> selectAll(String stuName, Byte sex, Integer grade, Integer page, Integer limit, String order) {
        PageHelper.startPage(page, limit);
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        if(!StringUtils.isEmpty(stuName)){
            criteria.andStudNameLike("%"+stuName+"%");
        }
        if(sex!=null&&(sex==0||sex==1)){
            criteria.andSexEqualTo(sex);
        }
        if(grade!=null){
            criteria.andGradeEqualTo(grade);
        }
        if(order.equals("desc")||order.equals("asc"))
        {
            example.setOrderByClause("grade "+order);
        }else {
            example.setOrderByClause("grade desc");
        }
        List<Student> studentList = studentMapper.selectByExample(example);
        return studentList;
    }

    public void add(Student student) {
        studentMapper.insertSelective(student);
    }

    public Student selectOne(Integer id) {
       return studentMapper.selectOne(id);
    }

    public void update(Student student) {
         studentMapper.updateByPrimaryKeySelective(student);
    }

    public List<Long> selectCourseId(Integer studentId) {
        return studentMapper.selectCourse(studentId);
    }

    public void delete(Integer studentId,Student student) {
        studentMapper.deleteCourse(studentId);
        studentMapper.updateByPrimaryKeySelective(student);
    }


    public void saveCourses(Integer studentId, Long courseId) {
        studentMapper.saveCourses(studentId,courseId);
    }

    public Integer selectStudentAndCourse(Integer studentId, Long courseId) {
        return studentMapper.selectStudentAndCourse(studentId,courseId);
    }
}
