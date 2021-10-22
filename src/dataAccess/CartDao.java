package dataAccess;

import models.Cart;
import models.enums.CartStatus;

import java.sql.SQLException;
import java.sql.Statement;

public class CartDao extends DataBaseAccess {
    public CartDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Cart cart, int customerId) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `cart` (`customer`, `total_cost`, `num_of_purchases`, `status`, `payment_status`) values ('%d','%f','%d','%s','%s') "
                    , customerId, cart.getTotalCost(), cart.getNumOfCartPurchases(), cart.getStatus(), cart.getPaymentStatus());
            return statement.executeUpdate(sql);
        }
        return -1;
    }

    public int confirmCart (int customerId, CartStatus cartStatus) throws SQLException {
        if (getConnection()!= null){
            Statement statement = getConnection().createStatement();
            String sql = String.format("update `cart` set  `status` ='%s' where `customer` = '%d' ",cartStatus,customerId);
            return statement.executeUpdate(sql);
        }
        return -1 ;

    }

    public int setTotalCost  (int customerId, double totalCost ) throws SQLException {
        if (getConnection()!= null){
            Statement statement = getConnection().createStatement();
            String sql = String.format("update `cart` set  `total_cost` ='%f' where `customer` = '%d' ",totalCost,customerId);
            return statement.executeUpdate(sql);
        }
        return -1 ;

    }

    public int setNumber (int customerId, int numOfPurchases ) throws SQLException {
        if (getConnection()!= null){
            Statement statement = getConnection().createStatement();
            String sql = String.format("update `cart` set  `num_of_purchases` ='%d' where `customer` = '%d' ",numOfPurchases,customerId);
            return statement.executeUpdate(sql);
        }
        return -1 ;

    }


}
