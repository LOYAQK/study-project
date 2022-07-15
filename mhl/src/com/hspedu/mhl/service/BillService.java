package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.BillDAO;
import com.hspedu.mhl.dao.MultiTableDAO;
import com.hspedu.mhl.domain.Bill;
import com.hspedu.mhl.domain.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * 处理和账单相关的业务逻辑
 */
public class BillService {
    //定义BillDAO 属性
    private BillDAO billDAO = new BillDAO();
    //定义MenuService 属性
    private MenuService menuService = new MenuService();
    //定义DiningTableService属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义MultiTableDAO属性
    private MultiTableDAO multiTableDAO = new MultiTableDAO();

    //编写点餐的方法
    //1.生成账单
    //2.需要更新对应餐桌的状态
    //3.如果成功返回true,否则返回false
    public boolean orderMenu(int menuId,int nums,int diningTableId) {
        //生成一个账单号,UUID
        String billID = UUID.randomUUID().toString();

        //将账单生成到bill表，要求直接计算账单金额
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }

        //需要更新对应餐桌的状态
        return diningTableService.updateDiningTableState(diningTableId,"就餐中");
    }

    //返回所有的账单并带有菜品名,价格，提供给View调用
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill",Bill.class);
    }

    //返回所有的账单，提供给View调用
    public List<MultiTableBean> list2() {
        return multiTableDAO.queryMulti("SELECT bill.*,NAME,price " +
                "FROM bill,menu " +
                "WHERE bill.menuId = menu.id", MultiTableBean.class);
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDAO.querySingle("select * from bill where diningTableId=? and state = '未结账' limit 0,1", Bill.class, diningTableId);
        return bill != null;
    }

    //完成结账[如果餐桌存在，并且该餐桌有未结账的账单]
    public boolean payBill(int diningTableId,String payMode) {

        //1.修改bill表
        int update = billDAO.update("update bill set state=? where diningTableId=? and state='未结账'", payMode, diningTableId);
        if (update <= 0) {//如果更新没有成功，则表示失败
            return false;
        }

        //2.修改diningTable表
        //注意：不要直接在这里操作，而应该调用DiningTableService 方法
        if (!diningTableService.updateDiningTableToFree(diningTableId,"空")) {
            return false;
        }

        return true;
    }
}
