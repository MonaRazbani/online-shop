package dataAccess;

import models.Address;
import models.enums.ElectronicType;
import models.enums.ProductStatus;
import models.products.Electronic;
import models.products.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressDao extends DataBaseAccess {
    public AddressDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int saveAddress(Address address) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `address` (`city`,`street`,`allay`,`house_number`,`post_code`) values ('%s','%s','%s','%d','%s')"
                    , address.getCity(), address.getStreet(), address.getAlley(), address.getHouseNumber(), address.getPostCode());
            return statement.executeUpdate(sql);
        } else return -1;

    }
    public List<Product> displayAll(String tableName ) throws SQLException {
        if (getConnection()!=null){
            List<Product> productList = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `'%s'` ");
            while (resultSet.next()){
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setCost(resultSet.getDouble("cost"));
                product.setStatus(ProductStatus.valueOf(resultSet.getString("status")));
                product.setWiki(resultSet.getInt("wiki"));
                product.setCompanyName(resultSet.getString("company"));
               productList.add(product);
            }
            return productList;

        }
        else return Collections.emptyList();
    }
}

