package com.demo.service;

import com.demo.entity.Course;
import com.demo.entity.CourseExample;
import com.demo.entity.StudentExample;
import com.demo.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;


    public List<Course> selectAll(String courseName, Integer page, Integer limit, String order) {
        PageHelper.startPage(page, limit);
        CourseExample example=new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(courseName)) {
            criteria.andCourseNameLike("%" + courseName + "%");
        }
        if (order.equals("desc") || order.equals("asc")) {
            example.setOrderByClause("credit " + order);
        } else {
            example.setOrderByClause("credit desc");
        }
        criteria.andDeletedEqualTo(false);
        List<Course> courseList=courseMapper.selectByExample(example);
        return courseList;
    }

    public void add(Course course) {
        courseMapper.insertSelective(course);
    }

    public Course selectOne(Long id) {
       return courseMapper.selectOne(id);
    }

    public void update(Course course) {
        courseMapper.updateByPrimaryKeySelective(course);
    }

    public List<Integer> selectStudentId(Long courseId) {
        return courseMapper.selectStudent(courseId);
    }
}
