package com.cs2001.group34.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs2001.group34.processes.SearchService;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/search-filter")
	private List<String[]> searchFilter(@RequestParam(value="q", required=false, defaultValue="") String lookupText,
										@RequestParam(value="topics", required=false, defaultValue="") ArrayList<Integer> topics,
										@RequestParam(value="exp", required=false, defaultValue="") ArrayList<Integer> experience,
										@RequestParam(value="author", required=false, defaultValue="") String authorName,
										@RequestParam(value="tags", required=false, defaultValue="") ArrayList<String> tags) {
		return searchService.searchFilter(lookupText, topics, experience, authorName, tags);
	}

}
