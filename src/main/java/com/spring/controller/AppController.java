package com.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Customers;
import com.spring.model.Orders;
import com.spring.service.AppService;

@Controller
public class AppController {
	@Autowired
	private AppService appService;

	@RequestMapping("/custSearchPage")
	public ModelAndView custSearchPage() {
		return new ModelAndView("searchCust");
	}

	@RequestMapping("/orderSearchPage")
	public ModelAndView orderSearchPage() {
		return new ModelAndView("searchOrder");
	}

	@RequestMapping(value = "/custSearch", method = RequestMethod.POST)
	public ModelAndView custSearch(@RequestParam(value = "orderDate") String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedDate = df.parse(date);
		java.sql.Date orderDate = new java.sql.Date(parsedDate.getTime());

		List<Customers> searchResult = appService.getCustomerByOrderDate(orderDate);

		ModelAndView model = new ModelAndView("searchCust");
		model.addObject("searchResults", searchResult);
		model.addObject("orderDate", date);
		model.addObject("noData", searchResult.isEmpty());
		return model;
	}

	@RequestMapping(value = "/orderSearch", method = RequestMethod.POST)
	public ModelAndView orderSearch(@RequestParam(value = "custName") String name) {

		List<Orders> searchResult = appService.getOrdersBelongTo(name);

		ModelAndView model = new ModelAndView("searchOrder");
		model.addObject("searchResults", searchResult);
		model.addObject("custName", name);
		model.addObject("noData", searchResult.isEmpty());
		return model;
	}
}
