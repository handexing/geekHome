package com.geekhome.entity.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geekhome.entity.Menu;
import com.geekhome.entity.dao.MenuDao;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> getChildMenuList(ArrayList<Menu> menuLists ,Long parentId){
       
        List<Menu> List = menuDao.findMenuByParentId(parentId);
        for(Menu menu : List){
            menuLists.add(menu);
            getChildMenuList(menuLists, menu.getId());
        }
        return menuLists;
    }

}
