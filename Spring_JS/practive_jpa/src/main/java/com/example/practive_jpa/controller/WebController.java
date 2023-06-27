package com.example.practive_jpa.controller;

import com.example.practive_jpa.entity.Course;
import com.example.practive_jpa.entity.Supporter;
import com.example.practive_jpa.service.CourseService;
import com.example.practive_jpa.statics.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping
public class WebController {
    CourseService courseService;
    @GetMapping("/khoahoc")
    public String getAll(Model model){
        List<Course> courseList= courseService.getAll();
        model.addAttribute("courceList",courseList);
        return "course-list";
    }

    @GetMapping("/khoahoc/onlab")
    public String getByTypeOnlab(Model model){
        List<Course> courses=courseService.getByType(Type.ON_LAB);
        model.addAttribute("onlab",courses);
        return "course-onlab-list";
    }

    @GetMapping("/khoahoc/online")
    public String getByTypeOffline(Model model){
        List<Course> courses=courseService.getByType(Type.ONLINE);
        model.addAttribute("offline",courses);
        return "course-online-list";
    }

    @GetMapping("khoahoc/detail/{id}")
    public String getDetails(Model model, @PathVariable("id") Integer id){
        Course course=courseService.getCourseById(id);
        Supporter supporter= courseService.getSupporterByCourseid(id);
        model.addAttribute("supporter",supporter);
        model.addAttribute("cource",course);
        return "detail";
    }

}
