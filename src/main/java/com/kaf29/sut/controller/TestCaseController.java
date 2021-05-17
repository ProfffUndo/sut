package com.kaf29.sut.controller;

import com.kaf29.sut.domain.TestCase;
import com.kaf29.sut.repo.TestCaseRepo;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("testcases")
public class TestCaseController {

    private final TestCaseRepo testCaseRepo;

    @Autowired
    public TestCaseController(TestCaseRepo testCaseRepo) {
        this.testCaseRepo = testCaseRepo;
    }

    @GetMapping
    public List<TestCase> list(){
        return testCaseRepo.findAll();
    }

    @GetMapping ("{id}")
    public TestCase getOne(@PathVariable("id") TestCase testCase){
        return testCase;
    }

    @PostMapping
    public TestCase create (@RequestBody TestCase testCase){
        return testCaseRepo.save(testCase);
    }

    @PutMapping ("{id}")
    public TestCase update (
            @PathVariable ("id") TestCase testCaseFromDb,
            @RequestBody TestCase testCase
    ){
        BeanUtils.copyProperties(testCase,testCaseFromDb,"id");
        return testCaseRepo.save(testCaseFromDb);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") TestCase testCase){
        testCaseRepo.delete(testCase);
    }
}
