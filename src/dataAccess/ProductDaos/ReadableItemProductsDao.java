package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.enums.ElectronicType;
import models.enums.ProductStatus;
import models.enums.PurchaseStatus;
import models.enums.ReadableItemsType;
import models.products.Electronic;
import models.products.Product;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadableItemProductsDao extends DataBaseAccess {
    public ReadableItemProductsDao() throws ClassNotFoundException, SQLException {
        super();
    }


    public List<ReadableItems> displayReadableProduct() throws SQLException {
        if (getConnection() != null) {
            List<ReadableItems> readableItemPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = "SELECT product.id, product.name,product.cost,product.company,product.status,readable_item_product.readable_item_Type,readable_item_product.subject,product.wiki FROM (product INNER JOIN readable_item_product ON product.id = id_readable_item);" ;

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //product_id, name, cost, company, status, electronic_type
                ReadableItems readableItem = new ReadableItems();
                readableItem.setName(resultSet.getString(2));
                readableItem.setCost(resultSet.getDouble(3));
                readableItem.setCompanyName(resultSet.getString(4));
                readableItem.setStatus(ProductStatus.valueOf(resultSet.getString(5)));
                readableItem.setType(ReadableItemsType.valueOf(resultSet.getString(6)));
                readableItem.setSubject(resultSet.getString(7));
                readableItem.setWiki(resultSet.getInt(8));
                readableItemPurchase.add(readableItem);
            }
            return readableItemPurchase;
        } else return Collections.emptyList();
    }


    public int save(ReadableItems readableItems, int readableItemId ) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `readable_item_product` (`id_readable_item`,`readable_item_Type`,`subject`) VALUES ('%d','%s','%s')"
                    , readableItemId, readableItems.getType(), readableItems.getSubject(), readableItems.getStatus());
            return statement.executeUpdate(sql);
        } else return -1;
    }
}

