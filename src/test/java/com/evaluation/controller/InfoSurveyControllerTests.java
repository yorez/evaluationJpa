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
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class InfoSurveyControllerTests {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testModify() throws Exception {
		log.info(mockMvc.perform(
				MockMvcRequestBuilders.post("/infoSurvey/modify").param("tno", "1").param("title", "mockmvctest"))
				.andReturn().getModelAndView().getViewName());
	}

	@Test
	public void testModfiyGet() throws Exception {
		log.info("" + mockMvc.perform(MockMvcRequestBuilders.get("/infoSurvey/read").param("tno", "1")).andReturn()
				.getModelAndView().getModelMap());
	}

	@Test
	public void testModifyPost() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/infoSurvey/modify").param("tno", "1")
				.param("title", "testmoidfy").param("content", "test")).andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}
}
