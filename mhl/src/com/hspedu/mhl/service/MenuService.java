package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.MenuDAO;
import com.hspedu.mhl.domain.Menu;

import java.util.List;

/**
 * 完成对menu表的各种操作(通过调用MenuDao)
 */
public class MenuService {

    //定义MenuDao 属性
    private MenuDAO menuDao = new MenuDAO();

    //返回所有的菜品，返回给界面使用
    public List<Menu> list() {
        return menuDao.queryMulti("select * from menu",Menu.class);
    }

    //根据id ,返回Menu对象
    public Menu getMenuById(int id) {
        return menuDao.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
