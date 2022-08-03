package com.cs2001.group34;

import static org.assertj.core.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cs2001.group34.model.Guide;
import com.cs2001.group34.processes.GuideService;



@SpringBootTest
class Group34ApplicationTests {

	@Autowired
	GuideService guideService;
	
	@Test
	public void getGuides() {
	List<Guide> guides=guideService.getByTopicId(3);
	assertThat(guides).size().isEqualTo(9);
	}
	
	

}
