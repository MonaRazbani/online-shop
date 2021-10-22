package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.enums.ElectronicType;
import models.enums.ProductStatus;
import models.enums.PurchaseStatus;
import models.products.Electronic;
import models.products.Product;
import models.products.ReadableItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElectronicProductsDao extends DataBaseAccess {
    public ElectronicProductsDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public List<Electronic> displayElectronicProduct() throws SQLException {
        if (getConnection() != null) {
            List<Electronic> electronicPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = "SELECT product.id, product.name,product.cost,product.company,product.status,electronic_product.electronic_type ,product.wiki FROM (product INNER JOIN electronic_product ON product.id = electronic_product.id_electronic);" ;

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //product_id, name, cost, company, status, electronic_type
                Electronic electronic = new Electronic();
                electronic.setId(resultSet.getInt(1));
                electronic.setName(resultSet.getString(2));
                electronic.setCost(resultSet.getDouble(3));
                electronic.setCompanyName(resultSet.getString(4));
                electronic.setStatus(ProductStatus.valueOf(resultSet.getString(5)));
                electronic.setType(ElectronicType.valueOf(resultSet.getString(6)));
                electronic.setWiki(resultSet.getInt(7));
                electronicPurchase.add(electronic);
            }
            return electronicPurchase;
        } else return Collections.emptyList();
    }

    public int save(Electronic electronic,int electronicId) throws SQLException {
            if (getConnection()!= null ) {
                Statement statement = getConnection().createStatement();
                String sql = String.format("insert into `electronic_product` (`id_electronic` ,`electronic_type`) VALUES ('%d','%s')",
                                            electronicId, electronic.getType() );
                return statement.executeUpdate(sql);
            }
            else return -1 ;
        }


}

