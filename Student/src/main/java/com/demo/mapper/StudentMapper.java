package com.demo.mapper;

import com.demo.entity.Student;
import com.demo.entity.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    Student selectOne(Integer id);

    List<Long> selectCourse(Integer studentId);

    void deleteCourse(Integer studentId);

    void saveCourses(@Param("studentId") Integer studentId,@Param("courseId") Long courseId);

    Integer selectStudentAndCourse(@Param("studentId") Integer studentId, @Param("courseId") Long courseId);
}