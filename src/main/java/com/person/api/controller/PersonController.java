package com.person.api.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.person.api.model.Person;
import com.person.api.service.AddressPersonService;
import com.person.api.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	PersonService personService;
	@Autowired
	AddressPersonService addressService;
	
	@RequestMapping(value="getPersonById/{personId}",method = RequestMethod.GET)
	public ResponseEntity<Object> getperson(@PathVariable int personId)
	{
		Person person1=personService.getPersonById(personId);
		personService.getPersonById(personId);
		if(person1.getPersonId()==null)	{
			throw new RuntimeException("Person not found");
		}
		return new ResponseEntity<Object>(personService.getPersonById(personId), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="getAllPerson",method = RequestMethod.GET)
	public ResponseEntity<Object> getAllPerson()
	{
		return new ResponseEntity<Object>(personService.findAllPerson(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="getAddress",method = RequestMethod.GET)
	public ResponseEntity<Object> getAllAddress()
	{
		return new ResponseEntity<Object>(addressService.getAllAddress(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="getPersonByDate/fromdate/{startDate}/toDate/{endDate}",method = RequestMethod.GET)
	public ResponseEntity<Object> getPersonBetweenTwoDates(@PathVariable("startDate") LocalDate startDate,@PathVariable("endDate") LocalDate endDate)
	{
		return new ResponseEntity<Object>(personService.findAllPerson(startDate, endDate), HttpStatus.OK);
	}
	@RequestMapping(value="getPersonByDate",method = RequestMethod.POST)
	public ResponseEntity<Object> getPersonBetweenTwoDates(@RequestBody Person person)
	{
		return new ResponseEntity<Object>(personService.findAllPerson(person.getStartDate(), person.getEndDate()), HttpStatus.OK);
	}
	
}
