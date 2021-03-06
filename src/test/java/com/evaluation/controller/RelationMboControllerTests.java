package com.evaluation.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * RelationMboControllerTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
@Slf4j
public class RelationMboControllerTests {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(
                MockMvcRequestBuilders.post("/relationMbo/register").param("tno", "1").param("evaluated", "207"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc
                .perform(MockMvcRequestBuilders.post("/relationMbo/remove").param("tno", "1").param("rno", "1"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void testGetList() throws Exception {
        log.info("" + mockMvc.perform(MockMvcRequestBuilders.get("/relationMbo/list").param("tno", "1")).andReturn()
                .getModelAndView().getModelMap());
    }

    @Test
    public void testGetMboEvaluatedList() throws Exception {
        log.info("" + mockMvc.perform(MockMvcRequestBuilders.get("/relationMbo/evaluated/1")).andReturn());
    }

    @Test
    public void testGetMboEvaluatorList() throws Exception {
        log.info("" + mockMvc.perform(MockMvcRequestBuilders.get("/relationMbo/evaluator/1/1")).andReturn());
    }

    @Test
    public void testDeleteEvaluatedInfo() throws Exception {
        log.info("" + mockMvc
                .perform(MockMvcRequestBuilders.post("/relationMbo/removeRow").param("tno", "1").param("sno", "1"))
                .andReturn());
    }

    @Test
    public void testXlDownload() throws Exception {
        log.info("" + mockMvc.perform(MockMvcRequestBuilders.post("/relationMbo/xlDownload").param("tno", "1"))
                .andReturn());
    }
}