package dataAccess;

import models.enums.*;
import models.person.Customer;
import models.products.Electronic;
import models.products.Product;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderPurchasesDao extends DataBaseAccess {
    public OrderPurchasesDao() throws SQLException, ClassNotFoundException {
        super();
    }

    public int save(int productId, int certId, int customerId, int number) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `order_purchases` (`customer_id`,`cart_id`,`product_id`,`status`,`number`)value('%d','%d','%d','%s','%d')"
                    , customerId, certId, productId, "NOT_CONFIRM", number);

            return statement.executeUpdate(sql);
        }

        return -1;
    }

    public int delete(int customerId, int productId) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("DELETE FROM `order_purchases` WHERE `customer_id` ='%d' and `product_id`='%d';", customerId, productId);
            return statement.executeUpdate(sql);
        }
        return -1;
    }

    public List<Electronic> displayElectronicItem(int customerId) throws SQLException {
        if (getConnection() != null) {
            List<Electronic> electronicPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = String.format("SELECT order_purchases.product_id, product.name,product.cost,product.company,product.status,electronic_product.electronic_type ,product.wiki,order_purchases.status FROM ((product INNER JOIN electronic_product ON product.id = electronic_product.id_electronic) INNER JOIN order_purchases ON electronic_product.id_electronic = order_purchases.product_id) where customer_id = '%d';",customerId);
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
                electronic.setPurchaseStatus(PurchaseStatus.valueOf(resultSet.getString(8)));
                electronicPurchase.add(electronic);
            }
            return electronicPurchase;
        } else return Collections.emptyList();
    }

    public List<Shoes> displayShoesItem(int customerId) throws SQLException {
        if (getConnection() != null) {
            List<Shoes> shoesPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = String.format("SELECT order_purchases.product_id, product.name,product.cost,product.company,product.status,shoes_product.type,shoes_product.size,product.wiki,order_purchases.status FROM ((product INNER JOIN shoes_product ON product.id = shoes_product.id_shoes) INNER JOIN order_purchases ON shoes_product.id_shoes = order_purchases.product_id) where customer_id = '%d';",customerId);
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
                shoes.setPurchaseStatus(PurchaseStatus.valueOf(resultSet.getString(9)));
                shoesPurchase.add(shoes);
            }
            return shoesPurchase;
        } else return Collections.emptyList();
    }

    public List<ReadableItems> displayReadableItem(int customerId ) throws SQLException {
        if (getConnection() != null) {
            List<ReadableItems> readableItemPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = String.format("SELECT order_purchases.product_id, product.name,product.cost,product.company,product.status,readable_item_product.readable_item_Type,readable_item_product.subject,product.wiki,order_purchases.status FROM ((product INNER JOIN readable_item_product ON product.id = id_readable_item) INNER JOIN order_purchases ON  readable_item_product.id_readable_item = order_purchases.product_id) where customer_id = '%d';",customerId);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //product_id, name, cost, company, status, electronic_type
                ReadableItems readableItem = new ReadableItems();
                readableItem.setId(resultSet.getInt(1));
                readableItem.setName(resultSet.getString(2));
                readableItem.setCost(resultSet.getDouble(3));
                readableItem.setCompanyName(resultSet.getString(4));
                readableItem.setStatus(ProductStatus.valueOf(resultSet.getString(5)));
                readableItem.setType(ReadableItemsType.valueOf(resultSet.getString(6)));
                readableItem.setSubject(resultSet.getString(7));
                readableItem.setWiki(resultSet.getInt(8));
                readableItem.setPurchaseStatus(PurchaseStatus.valueOf(resultSet.getString(9)));
                readableItemPurchase.add(readableItem);
            }
            return readableItemPurchase;
        } else return Collections.emptyList();
    }

    public List<Product> findListOfCostAndNnmOfPurchase(int customerId) throws SQLException {
        if (getConnection() != null) {
            List<Product> ListOfCostAndNnmOfPurchase = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            String sql = String.format("SELECT order_purchases.product_id, product.name,product.cost,product.company,product.status,product.wiki,order_purchases.number FROM (product INNER JOIN  order_purchases ON  product.id = order_purchases.product_id) where customer_id = '%d';",customerId);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = new Product();
                //product_id, name, cost, company, status, wiki, number
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setCost(resultSet.getDouble(3));
                product.setCompanyName(resultSet.getString(4));
                product.setStatus(ProductStatus.valueOf(resultSet.getString(5)));
                product.setWiki(resultSet.getInt(6));
                product.setNumOfPurchases(resultSet.getInt(7));
                ListOfCostAndNnmOfPurchase.add(product);
            }
            return ListOfCostAndNnmOfPurchase;
        } else return Collections.emptyList();

    }
}




