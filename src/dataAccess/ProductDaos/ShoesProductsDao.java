package dataAccess.ProductDaos;

import com.mysql.cj.protocol.Resultset;
import dataAccess.DataBaseAccess;
import models.enums.ProductStatus;
import models.enums.PurchaseStatus;
import models.enums.ReadableItemsType;
import models.enums.ShoesType;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoesProductsDao extends DataBaseAccess {
    public ShoesProductsDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Shoes shoes, int shoesId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `shoes_product` ( `id_shoes`,`type`,`size`) VALUES ('%d','%s','%d')"
                    ,shoesId, shoes.getType(), shoes.getSize(), shoes.getStatus());
            return statement.executeUpdate(sql);
        } else return -1;

    }


    public List<Shoes> displayShoesProduct () throws SQLException {
        if (getConnection() != null) {
            List<Shoes> shoesPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = "SELECT product.id, product.name,product.cost,product.company,product.status,shoes_product.type,shoes_product.size,product.wiki FROM (product INNER JOIN shoes_product ON product.id = shoes_product.id_shoes);" ;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //product_id, name, cost, company, status, electronic_type
                Shoes shoes = new Shoes();
                shoes.setId(resultSet.getInt(1));
                shoes.setName(resultSet.getString(2));
                shoes.setCost(resultSet.getDouble(3));
                shoes.setCompanyName(resultSet.getString(4));
                shoes.setStatus(ProductStatus.valueOf(resultSet.getString(5)));
                shoes.setType(ShoesType.valueOf(resultSet.getString(6)));
                shoes.setSize(resultSet.getInt(7));
                shoes.setWiki(resultSet.getInt(8));
                shoesPurchase.add(shoes);
            }
            return shoesPurchase;
        } else return Collections.emptyList();
    }
}
