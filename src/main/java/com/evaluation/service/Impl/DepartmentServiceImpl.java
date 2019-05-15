package com.evaluation.service.Impl;

import java.util.List;
import java.util.Optional;

import com.evaluation.domain.Department;
import com.evaluation.persistence.DepartmentRepository;
import com.evaluation.service.DepartmentService;
import com.evaluation.vo.PageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Setter(onMethod_ = { @Autowired })
    private DepartmentRepository departmentRepo;

    @Override
    public void register(Department department) {
        log.info("register " + department);

        departmentRepo.save(department);
    }

    public Optional<Department> read(long dno) {
        log.info("read " + dno);

        Optional<Department> result = departmentRepo.findById(dno);
        return result;
    }

    @Override
    public void modify(Department department) {
        log.info("modify " + department);

        departmentRepo.save(department);
    }

    @Override
    public void remove(long dno) {
        log.info("remove " + dno);

        departmentRepo.deleteById(dno);
    }

    @Override
    public List<Department> getList(long cno) {
        log.info("getList by " + cno);

        List<Department> result = departmentRepo.getDepartmentOfCompany(cno);
        return result;
    }

    @Override
    public Page<Department> getListWithPaging(long cno, PageVO vo) {
        log.info("getListWithPaging by " + cno);

        Pageable page = vo.makePageable(1, "dno");
        Page<Department> result = departmentRepo
                .findAll(departmentRepo.makePredicate(vo.getType(), vo.getKeyword(), cno), page);
        return result;
    }
}
