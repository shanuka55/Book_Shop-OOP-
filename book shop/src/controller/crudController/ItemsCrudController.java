package controller.crudController;

import Util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsCrudController {
    public static ArrayList<String> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT ItemCode FROM item");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
