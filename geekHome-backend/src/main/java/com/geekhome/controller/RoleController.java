package com.geekhome.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.geekhome.common.vo.ErrorCode;
import com.geekhome.common.vo.ExecuteResult;
import com.geekhome.common.vo.RetJson;
import com.geekhome.entity.Role;
import com.geekhome.entity.dao.RoleDao;
import com.geekhome.entity.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RoleDao roleDao;
	@Autowired
	RoleService roleService;

	@RequiresPermissions("role:index")
	@RequestMapping(value = "/rolePage")
	public ModelAndView menuPage() {
		return new ModelAndView("/view/system/rolePage");
	}
	
	@RequiresPermissions("role:list")
	@RequestMapping(value = "/roleList")
	public RetJson roleList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		try {
			retJson.setStatus(1);
			retJson.setMessage("加载成功");
			Sort sort = new Sort(Direction.DESC, "id");
			Pageable pageable = new PageRequest(start, length, sort);
			Page<Role> pageData = roleDao.findAll(pageable);
			retJson.setData(pageData.getContent());
			retJson.setRecordsTotal(pageData.getTotalElements());
			retJson.setRecordsFiltered(pageData.getTotalElements());
			retJson.setDraw(draw == null ? 0 : draw);
		} catch (Exception e) {
			logger.error("", e);
			retJson.setStatus(-1);
			retJson.setMessage("程序出错");
		}
		
		return retJson;
	}
	
	@RequiresPermissions("role:save")
	@RequestMapping("saveRole")
	public ExecuteResult<Boolean> saveRole(@RequestBody Role role) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			roleService.saveRole(role);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
	
	@RequiresPermissions("role:delete")
	@RequestMapping("delRole")
	public ExecuteResult<Boolean> delRole(Long id) {
		final ExecuteResult<Boolean> result = new ExecuteResult<>();
		try {
			roleDao.delete(id);
			result.setSuccess(true);
		} catch (final Exception e) {
			logger.error("", e);
			result.setSuccess(false);
			result.setErrorCode(ErrorCode.EXCEPTION.getErrorCode());
			result.setErrorMsg(ErrorCode.EXCEPTION.getErrorMsg());
		}
		return result;
	}
}
