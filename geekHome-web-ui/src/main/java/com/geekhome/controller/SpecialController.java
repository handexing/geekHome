package com.geekhome.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekhome.common.vo.PageableResultJson;
import com.geekhome.entity.Special;
import com.geekhome.entity.dao.SpecialDao;

@RestController
@RequestMapping("special")
public class SpecialController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SpecialDao specialDao;

	@RequestMapping("getSpecialList")
	@CrossOrigin
	public PageableResultJson getSpecialList(@RequestParam(value = "page") Integer page, Integer rows) {
		PageableResultJson tableJson = new PageableResultJson();
		Sort sort = new Sort(Sort.Direction.ASC, "sort");
		Pageable pageRequest = new PageRequest(page / rows, rows, sort);
		Page<Special> pageData = specialDao.findAll(pageRequest);
		tableJson.setData(pageData.getContent());
		tableJson.setPageSize(10);
		tableJson.setTotalPageNumber(pageData.getTotalPages());
		return tableJson;
	}
	
}
