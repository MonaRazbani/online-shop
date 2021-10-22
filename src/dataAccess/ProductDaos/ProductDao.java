package dataAccess.ProductDaos;

import dataAccess.DataBaseAccess;
import models.enums.ProductStatus;
import models.products.Product;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDao extends DataBaseAccess {

    public ProductDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Product product) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `product` ( `name`, `cost`, `company`, `wiki`,`status`) VALUES ('%s','%f','%s','%d','%s')"
                    , product.getName(), product.getCost(), product.getCompanyName(), product.getWiki(), product.getStatus());
            return statement.executeUpdate(sql);
        } else return -1;
    }

    public int findProductId (Product product) throws SQLException {
        if (getConnection()!=null){
            Statement statement = getConnection().createStatement();
            String sql = String.format("select `id` from `product` where `name` = '%s' AND `cost`='%f' AND `company` ='%S' ",product.getName(),product.getCost(),product.getCompanyName());
            ResultSet resultset = statement.executeQuery(sql);
            if (resultset.next()){
                return resultset.getInt(1);
            }
        }
        return -1 ;
    }

    public Product findProductById (int id ) throws SQLException {
        if (getConnection()!= null){
            Statement statement = getConnection().createStatement();
            String sql = String.format("select * from `product` where `id`= '%d' ",id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setCost(resultSet.getDouble("cost"));
                product.setCompanyName(resultSet.getString("company"));
                product.setStatus(ProductStatus.valueOf(resultSet.getString("status")));
                product.setWiki(resultSet.getInt("wiki"));
                return product;
            }
        }
        return null;
    }

    public int updateWiki (Product product ) throws SQLException {
        if (getConnection()!=null){
            int productId = findProductId(product);
            if (productId!=-1 ){
                Statement statement = getConnection().createStatement();
                String sql = String.format("update `product` set `wiki` = '%d' where `id` = '%d'" ,product.getWiki(),productId );
                return statement.executeUpdate(sql);
            }
        }
    return -1 ;
    }
}
