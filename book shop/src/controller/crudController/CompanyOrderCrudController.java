package controller.crudController;

import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyOrderCrudController {
    public static ArrayList<String> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT CompanyId FROM Company");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static ArrayList<String> getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT CompanyOrderId FROM companyorder");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
