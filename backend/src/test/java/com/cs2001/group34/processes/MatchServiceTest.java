package com.cs2001.group34.processes;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class MatchServiceTest 
{
	
	MatchService matchService = new MatchService();
	
	@Test
	public void canCreateMatchPercentageCorrectly()
	{
		
		List<String> firstList = new ArrayList<>(List.of("Computer Architechture","Algorithms"));
		List<String> secondList = new ArrayList<>(List.of("Computer Architechture","Algorithms", "Discrete Maths"));
		int ans = matchService.getPercentage(firstList, secondList);
		Assertions.assertEquals(100, ans);
	}
}
