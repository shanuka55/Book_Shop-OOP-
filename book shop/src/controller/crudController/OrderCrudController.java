package controller.crudController;

import Util.CrudUtil;
import model.Order;
import model.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderCrudController {

    public boolean saveOrder(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Orders VALUES(?,?,?,?,?)",
                order.getOrderId(), order.getItemName(), order.getOrderUnitPrice(),order.getOrderQTY(),order.getTotal());
    }
    public boolean saveOrderDetails(ArrayList<OrderDetails> details) throws SQLException, ClassNotFoundException {
        for (OrderDetails det:details
        ) {
            boolean isDetailsSaved=CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?,?,?)",
                    det.getCustomerId(),det.getItemCode(),det.getOrderId(),det.getItemName(), det.getQty(),det.getTotal());
            if (isDetailsSaved){
                if (!updateQty(det.getItemCode(), det.getQty())){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE items SET ItemQTY=ItemQTY-? WHERE ItemCode=?", qty,itemCode);
    }
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1");
        if (set.next()){
            return set.getString(1);
        }else{
            return "D001";
        }
    }

}
