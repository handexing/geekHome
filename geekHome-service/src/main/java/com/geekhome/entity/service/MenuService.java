package com.geekhome.entity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekhome.entity.Menu;
import com.geekhome.entity.dao.MenuDao;
import com.geekhome.entity.dao.RoleMenuDao;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	RoleMenuDao roleMenuDao;

	public List<Menu> getChildMenuList(ArrayList<Menu> menuLists, Long parentId) {

		List<Menu> List = menuDao.findMenuByParentId(parentId);
		for (Menu menu : List) {
			menuLists.add(menu);
			getChildMenuList(menuLists, menu.getId());
		}
		return menuLists;
	}

	@Transactional
	public void saveMenu(Menu menu) {
		if (menu.getId() != null) {
			Menu m = menuDao.findOne(menu.getId());
			m.setUpdateTime(new Date());
			m.setName(menu.getName());
			m.setCode(menu.getCode());
			m.setUrl(menu.getUrl());
			m.setType(menu.getType());
			menuDao.save(m);
		} else {
			menu.setCreateTime(new Date());
			menu.setSort(1);
			menuDao.save(menu);
		}

		if (menu.getParentId() != 0) {
			menu = menuDao.findOne(menu.getParentId());
			menu.setUpdateTime(new Date());
			menuDao.save(menu);
		}

	}
	
	@Transactional
	public void delMenu(Long id) {
		menuDao.delete(id);
		roleMenuDao.delete(id);
	}

}
