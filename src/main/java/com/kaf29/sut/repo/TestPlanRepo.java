package com.kaf29.sut.repo;

import com.kaf29.sut.domain.TestPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestPlanRepo extends JpaRepository<TestPlan, Long> {
}
