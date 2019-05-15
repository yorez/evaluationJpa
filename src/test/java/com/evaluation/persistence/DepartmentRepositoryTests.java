package com.evaluation.persistence;

import java.util.stream.IntStream;

import com.evaluation.domain.Department;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Commit
public class DepartmentRepositoryTests {

    @Autowired
    DepartmentRepository departmentRepo;

    @Test
    public void insertDepartmentDummies() {

        IntStream.range(1, 11).forEach(i -> {

            Department department = new Department();
            department.setDepartment1("dep1" + i);  
            department.setDepartment2("dep2" + i);
            department.setCno(99L);
            departmentRepo.save(department);
        });

    }

    // @Test
    // public void testList1() {
    // Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "cno");
    // Page<Company> result = repo.findAll(repo.makePredicate(null, null),
    // pageable);
    // log.info("PAGE : " + result.getPageable());

    // log.info("----------------");
    // result.getContent().forEach(company -> log.info("" + company));
    // }

    // @Test
    // public void testList2() {
    // Pageable pageable = PageRequest.of(0, 20, Direction.DESC, "cno");
    // Page<Company> result = repo.findAll(repo.makePredicate("id", "1"), pageable);
    // log.info("PAGE : " + result.getPageable());

    // log.info("----------------");
    // result.getContent().forEach(company -> log.info("" + company));
    // }

}