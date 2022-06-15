package com.demo.controller;

import com.demo.entity.Course;
import com.demo.entity.Student;
import com.demo.service.CourseService;
import com.demo.service.StudentService;
import com.demo.util.ResponseUtil;
import com.demo.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;


    /**
     * 学生保存课程
     * @param studentId
     * @param courseIdList 课表idList
     * @return
     */
    @PostMapping("/save/courses")
    public Object saveCourses(@RequestParam("studentId") Integer studentId,
                              @RequestBody List<Long> courseIdList ){
        for (Long courseId:courseIdList) {
            Integer id=studentService.selectStudentAndCourse(studentId,courseId);
            if(id!=null){
                continue;
            }
            studentService.saveCourses(studentId, courseId);
        }
        return ResponseUtil.ok("保存信息成功!");
    }

    /**
     * 学生查询课程
     * @param studentId
     * @return
     */
    @GetMapping("/select/courses")
    public Object selectCourse(@RequestParam("studentId") Integer studentId){
        Student student = studentService.selectOne(studentId);
        if(student==null){
            return ResponseUtil.fail("没有该学生!");
        }
        List<Long> courseIdList = studentService.selectCourseId(studentId);
        List<Course> courseList=new ArrayList<>();
        for (Long courseId:courseIdList) {
            Course course = courseService.selectOne(courseId);
            courseList.add(course);
        }
        StudentVo studentVo=new StudentVo(student,courseList);
        return ResponseUtil.ok(studentVo);
    }


    /**
     * 删除学生 连带课程信息
     * @param studentId
     * @return
     */
    @GetMapping("/delete/course")
    public Object deleteCourses(@RequestParam("studentId") Integer studentId){
        if(studentId==null){
            return ResponseUtil.badArgumentValue();
        }
        Student student = studentService.selectOne(studentId);
        if(student==null){
            return ResponseUtil.fail("没有该学生信息!");
        }
        student.setDeleted(true);
        studentService.delete(studentId,student);
        return ResponseUtil.ok("学生信息删除成功!");
    }





    /**
     * 若不输入则为分页查询所有数据 默认根据年级降序排序
     * @param stuName 学生姓名 模糊查询
     * @param sex 学生性别 精准查询
     * @param grade 学生年级 精准查询
     * @param page 页码
     * @param limit 单页数据
     * @return
     */
    @GetMapping("/select/list")
    public Object selectList(
       @RequestParam(value = "stuName",required = false)  String stuName,
       @RequestParam(value = "sex",required = false) Byte sex,
       @RequestParam(value = "grade",required = false) Integer grade,
       @RequestParam(value = "page", defaultValue = "1") Integer page,
       @RequestParam(value = "limit", defaultValue = "4") Integer limit,
       @RequestParam(value = "order",defaultValue = "desc") String order
    ){
        List<Student> studentList=studentService.selectAll(stuName,sex,grade,page,limit,order);
        return ResponseUtil.okList(studentList);
    }


    /**
     * 增加学生
     * @param student
     * @return
     */
    @PostMapping("/add")
    public Object addStudent(@RequestBody Student student){
        //gradevalue 是年级的最大值 年级值不能超过合法范围
        Object error= validated(student,20);
        if(error!=null){
            return error;
        }
        //设置学生为未删除的状态
        student.setDeleted(false);
        studentService.add(student);
        return ResponseUtil.ok("增加学生成功!");
    }

    /**
     * 更新学生
     * @param student
     * @return
     */
    @PostMapping("/update")
    public Object updateStudent(@RequestBody Student student) {
        Integer id=student.getId();
        if(id==null){
            return ResponseUtil.fail("请输入学生id");
        }
        Student student1=studentService.selectOne(id);
        if(student1==null){
            return ResponseUtil.fail("根据id查询不到学生,无法更新!");
        }

        //gradevalue 是年级的最大值 年级值不能超过合法范围
        Object error = validated(student, 20);
        if (error != null) {
            return error;
        }
        studentService.update(student);
        return ResponseUtil.ok("更新学生信息成功!");
    }


    /**
     * 检验student属性值
     * @param student
     * @param gradeMax 年级的合理值 检验年级值是否在一个范围
     * @return
     */
    private Object validated(Student student,Integer gradeMax) {
        Integer grade = student.getGrade();
        String studName = student.getStudName();
        Byte sex = student.getSex();
        if(grade==null||grade<=0||grade>gradeMax){
            return ResponseUtil.fail("年级值设置不正确!");
        }
        if(StringUtils.isEmpty(studName)){
            return ResponseUtil.fail("学生姓名不能为空!");
        }
        if(sex==null){
            return ResponseUtil.fail("请选择学生性别!");
        }
        if(sex!=1&&sex!=0){
            return ResponseUtil.fail("请重新选择学生性别!");
        }
        return null;
    }


}
