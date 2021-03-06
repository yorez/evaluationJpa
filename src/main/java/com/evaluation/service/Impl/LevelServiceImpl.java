package com.evaluation.service.Impl;

import java.util.Optional;

import com.evaluation.domain.Level;
import com.evaluation.persistence.LevelRepository;
import com.evaluation.service.LevelService;
import com.evaluation.vo.PageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepo;

    @Override
    public void register(Level level) {
        log.info("register " + level);

        levelRepo.save(level);
    }

    public Optional<Level> read(long lno) {
        log.info("read " + lno);

        Optional<Level> result = levelRepo.findById(lno);
        return result;
    }

    @Override
    public void modify(Level level) {
        log.info("modify " + level);

        levelRepo.findById(level.getLno()).ifPresent(origin -> {
            origin.setContent(level.getContent());
            origin.setUpdateId(level.getUpdateId());
            levelRepo.save(origin);
        });
    }

    @Override
    public void remove(long lno) {
        log.info("remove " + lno);
        levelRepo.findById(lno).ifPresent(origin -> {
            levelRepo.deleteById(origin.getLno());
        });
    }

    @Override
    public Page<Level> getList(long cno, PageVO vo) {
        log.info("getListWithPaging by " + cno);

        Pageable page = vo.makePageable(1, "content");
        Page<Level> result = levelRepo.findAll(levelRepo.makePredicate(vo.getType(), vo.getKeyword(), cno), page);
        return result;
    }
}
