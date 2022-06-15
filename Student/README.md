技术栈:
    springboot+mysql+pageHelper+mybatis
    完善时可加入JWT+springSecurity
项目架构:
    demo.controller 
    demo.entity 实体类
    demo.exception 处理异常
    demo.mapper
    demo.service
    demo.util 返回工具类 ResponseUtil类
    demo.vo 合成数据传给前端
功能概述:
    所有功能均会检验数据的合法性
    学生功能:
        StudentController里面
        1.保存学生的相关课程 saveCourses方法
        2.查询学生信息以及对应的课程 selectCourse
        3.删除学生以及对应的课程信息 deleteCourses
        4.查询学生信息(不包括课程) 姓名 性别 年级 条件分页查询 selectList
        5.增加学生 addStudent
        6.更新学生 updateStudent
    课程功能:
        CourseController里面
        1.保存一个课程下的学生 saveStudents
        2.查询一个课程下的学生 selectStudents
        3.删除课程 若存在学生 则不能删除 delete
        4.查询课程 书名模糊查询 selectList
        5.增加课程 addCourse
        6.更新课程 updateCourse

完善之处:
    目前该项目缺少权限管理
    分为学生权限 老师权限 
    学生只能使用 查询学生信息以及对应的课程 功能
    老师则全部可以使用 
    增加的 1.保存一个课程下的学生 saveStudents 
            2.查询一个课程下的学生 selectStudents功能
    方便老师查询课程人数 课程学生等信息 
拓展之处:    
    可增加一些课程时间 课程老师等字段
    变成课程表系统
    该项目可拓展成 课程表+教学管理系统
    教学管理安排老师 课程 学生等 此时则就需要管理员权限

均用JWT+springSecurity实现
    