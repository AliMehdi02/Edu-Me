package com.cs2001.group34.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs2001.group34.dto.PreferenceDto;
import com.cs2001.group34.model.Preferences;
import com.cs2001.group34.processes.PrefService;


@CrossOrigin
@RestController
@RequestMapping("/preferences")
public class PrefController {

	@Autowired
	private PrefService service;

	@PostMapping("/storePref/{check}")
	public String addData(@RequestBody PreferenceDto pref, @PathVariable String check) throws Exception {
		if (check.equals("goodAt")) {
			pref.setGoodAt(true);
			service.AddPref(pref);
		} else if (check.equals("toLearn")) {
			pref.setGoodAt(false);
			service.AddPref(pref);
		}
		return "{\"res\": \"done\"}";
	}
	
	@GetMapping(path="/goodAt-preferences/{id}") 
	private List<String> returnGoodAt(@PathVariable int id) {
		return service.returnGoodAt(id);
	}
	
	@GetMapping(value="/badAt-preferences/{id}")
	private List<String> returnBadAt(@PathVariable int id) {
		return service.returnBadAt(id);
	}
}
