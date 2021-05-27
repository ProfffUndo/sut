package com.kaf29.sut.controller;

import com.kaf29.sut.domain.TestPlan;
import com.kaf29.sut.repo.TestPlanRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("testplan")
public class TestPlanController {
    private final TestPlanRepo testPlanRepo;

    @Autowired
    public TestPlanController(TestPlanRepo testPlanRepo) {
        this.testPlanRepo = testPlanRepo;
    }

    @GetMapping
    public List<TestPlan> list(){
        return testPlanRepo.findAll();
    }

    @GetMapping ("{id}")
    public TestPlan getOne(@PathVariable("id") TestPlan testPlan){
        return testPlan;
    }

    @PostMapping
    public TestPlan create (@RequestBody TestPlan testPlan){
        return testPlanRepo.save(testPlan);
    }

    @PutMapping ("{id}")
    public TestPlan update (
            @PathVariable ("id") TestPlan testPlanFromDb,
            @RequestBody TestPlan testPlan
    ){
        BeanUtils.copyProperties(testPlan,testPlanFromDb,"id");
        return testPlanRepo.save(testPlanFromDb);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") TestPlan testPlan){
        testPlanRepo.delete(testPlan);
    }
}
