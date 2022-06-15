package com.demo.controller;

import com.demo.entity.Course;
import com.demo.entity.Student;
import com.demo.service.CourseService;
import com.demo.service.StudentService;
import com.demo.util.ResponseUtil;
import com.demo.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;


    /**
     * 课程保存学生
     * @param courseId
     * @param studentIdList
     * @return
     */
    @PostMapping("/save/students")
    public Object saveStudents(@RequestParam("courseId") Long courseId,
                              @RequestBody List<Integer> studentIdList) {
        for (Integer studentId : studentIdList) {
            Integer id = studentService.selectStudentAndCourse(studentId, courseId);
            if (id != null) {
                continue;
            }
            studentService.saveCourses(studentId, courseId);
        }
        return ResponseUtil.ok("保存信息成功!");
    }


    /**
     * 查询一个课程下的学生
     * @param courseId
     * @return
     */
    @GetMapping("/select/students")
    public Object selectStudents(@RequestParam("courseId") Long courseId){
        Course course = courseService.selectOne(courseId);
        List<Integer> studentIdList=courseService.selectStudentId(courseId);
        if(course==null){
            return ResponseUtil.fail("没有该课程信息!");
        }
        List<Student> studentList=new ArrayList<>();
        for(Integer studentId:studentIdList){
            Student student = studentService.selectOne(studentId);
            studentList.add(student);
        }
        CourseVo courseVo=new CourseVo(course,studentList);
        return ResponseUtil.ok(courseVo);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("courseId")Long courseId){
        if(courseId==null||courseId<=0){
            return ResponseUtil.badArgumentValue();
        }
        List<Integer> studentList = courseService.selectStudentId(courseId);
        if(studentList!=null&&studentList.size()>0){
            return ResponseUtil.fail("该课程还有学生在使用! 无法删除!");
        }
        Course course=courseService.selectOne(courseId);
        if(course==null){
             return ResponseUtil.fail("根据id找不到课程!");
        }
        //逻辑删除
        course.setDeleted(true);
        courseService.update(course);
        return ResponseUtil.ok("课程删除成功!");
    }










    /**
     *
     * @param courseName 书名模糊查询
     * @param page 页码
     * @param limit 页码数据
     * @param order 排序方式 asc desc
     * @return
     */
    @GetMapping("/select/list")
    public Object selectList(
            @RequestParam(value = "courseName",required = false) String courseName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "4") Integer limit,
            @RequestParam(value = "order", defaultValue = "desc") String order){

        List<Course> courseList=courseService.selectAll(courseName,page,limit,order);
        return ResponseUtil.okList(courseList);
    }

    @PostMapping("/add")
    public Object addCourse(
            @RequestBody Course course
    ){
        //gradevalue 是学分的最大值 学分不能超过合法范围
        Object error = validated(course, 10.0F);
        if (error != null) {
            return error;
        }

        //设置course为存在状态
        course.setDeleted(false);
        courseService.add(course);
        return ResponseUtil.ok("课程增加成功!");
    }

    @PostMapping("/update")
    public Object updateCourse(@RequestBody Course course){
        Long id = course.getId();
        if(id==null){
            return ResponseUtil.fail("请输入课程id!");
        }
        Course course1=courseService.selectOne(id);
        if(course1==null){
            return ResponseUtil.fail("根据课程id无法查到课程 无法更新");
        }
        courseService.update(course);
        return ResponseUtil.ok("课程信息更新成功!");
    }

    private Object validated(Course course, Float creditMax) {
        String courseName = course.getCourseName();
        Float credit = course.getCredit();
        if(StringUtils.isEmpty(courseName)){
            return ResponseUtil.fail("缺少课程名信息!");
        }
        if(credit==null||credit<=0||credit>creditMax){
            return ResponseUtil.fail("学分值设置不正确!");
        }
        return null;
    }


}
