package com.evaluation.service;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.evaluation.domain.RelationSurvey;
import com.evaluation.domain.Staff;
import com.evaluation.vo.PageVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Transactional
public class RelationSurveyServiceTests {

    @Setter(onMethod_ = { @Autowired })
    RelationSurveyService relationSurveyService;

    @Setter(onMethod_ = { @Autowired })
    StaffService staffService;

    @Test
    public void diTest() {
        assertNotNull(relationSurveyService);
    }

    @Test
    public void registerTest() {
        Staff evaluated = staffService.read(2L).get();
        Staff evaluator = staffService.read(15L).get();

        RelationSurvey relationSurvey = new RelationSurvey();
        relationSurvey.setEvaluated(evaluated);
        relationSurvey.setEvaluator(evaluator);
        relationSurvey.setRelation("me");
        relationSurvey.setFinish("N");
        relationSurvey.setTno(9L);

        relationSurveyService.register(relationSurvey);
    }

    @Test
    public void readTest() {
        log.info("read ---->" + relationSurveyService.read(60L).get().getEvaluator());
    }

    @Test
    public void removeTest() {
        relationSurveyService.remove(61L);
    }

    @Test
    public void getDistinctEvaluatedListTest() {
        PageVO vo = new PageVO();
        vo.setType("evaluator");
        vo.setKeyword("id2");

        Page<Staff> result = relationSurveyService.getDistinctEvaluatedList(9L, vo);

        result.getContent().forEach(staff -> log.info("" + staff.getSno()));
    }

    @Test
    @Transactional
    public void getSurveyReultTest() {
        relationSurveyService.findAllByTno(1L).ifPresent(list -> {
            Set<String> answerKey = new HashSet<String>();
            for (int i = 0; i < list.size(); i++) {
                // answer를 위한
                Set<Map.Entry<String, Double>> entries = list.get(i).getAnswers().entrySet();
                for (Map.Entry<String, Double> entry : entries) {
                    answerKey.add(entry.getKey());
                }

                // log.info("" + list.get(i).getAnswers().get("q1"));
                // for (String key : answerKey) {
                // }
            }
            log.info("" + answerKey);
        });
    }

}