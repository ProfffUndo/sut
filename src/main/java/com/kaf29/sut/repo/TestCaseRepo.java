package com.kaf29.sut.repo;

import com.kaf29.sut.domain.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long>  {
}
