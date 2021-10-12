package com.aws.service1.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
@CrossOrigin(origins = "*")
public class MyController {

	private List<Map<String, String>> list = new ArrayList<>();
	private static final String NAME = "name";
	private static final String MOBILE = "mobile";

	@GetMapping("/health")
	public Map<String, String> getHealth() {
		System.out.println("Inside service1....");
		Map<String, String> map = new HashMap<>();
		map.put("service", "Service1");
		map.put("status", "UP");
		return map;
	}

	@PostMapping("/add")
	public Map<String, String> add(@RequestParam String name, @RequestParam String mobile) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put(NAME, name);
		map.put(MOBILE, mobile);
		list.add(map);
		return map;
	}

	@GetMapping("/get")
	public List<Map<String, String>> get() {
		return list;
	}

	@DeleteMapping("/delete")
	public Map<String, String> delete(@RequestParam String name) {
		Optional<Map<String, String>> findAny = list.stream().filter(user -> user.containsKey(name)).findAny();
		if (findAny.isPresent()) {
			Map<String, String> map = findAny.get();
			list.remove(map);
			return map;
		}
		return null;
	}

	@PutMapping("/update")
	public Map<String, String> update() {
		return null;
	}

	@GetMapping("/fetch")
	public List<Person> fetchDetails(@RequestParam String name, @RequestParam int age,
			@RequestParam String pincode) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		person.setPincode(pincode);
		List<Person> persons = Collections.nCopies(100, person);
		return persons;
	}

}
