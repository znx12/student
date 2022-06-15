package com.demo;

import com.demo.entity.Student;
import com.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserService {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testInsert(){
        Student student=new Student(null,"1", (byte) 1,1,false);
        studentMapper.insert(student);
    }

}
