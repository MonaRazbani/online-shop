package dataAccess;

import models.person.Customer;

import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao extends DataBaseAccess{
    public CustomerDao() throws ClassNotFoundException, SQLException {
        super();
    }

    public int save(Customer customer, int addressId ) throws SQLException {
        if (getConnection()!=null) {
            Statement statement = getConnection().createStatement();
            String sql = String.format("insert into `customer` (`first_name`, `last_name`, `username`, `password`, `national_code`, `phone`, `gender`, `wallet`,`address`) values ('%s','%s','%s','%s','%s','%s','%s','%f','%d'  )",
                    customer.getFirstName(), customer.getLastName(), customer.getUserName(), customer.getPassword(), customer.getNationalCode(), customer.getPhoneNumber(),customer.getGender(), customer.getWallet(), addressId );
            return statement.executeUpdate(sql);
        }
            else return -1 ;
        }


    }

