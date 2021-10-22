package services;

import controlValidValue.ControlValidValue;
import dataAccess.CartDao;
import dataAccess.CustomerDao;
import dataAccess.OrderPurchasesDao;
import dataAccess.ProductDaos.ProductDao;
import models.Cart;
import models.enums.CartStatus;
import models.person.Customer;
import models.products.Electronic;
import models.products.Product;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CartOrderService {
    static final Scanner scanner = new Scanner(System.in);
    private ProductDao productDao = new ProductDao();
    private CartDao cartDao = new CartDao();
    private CustomerDao customerDao = new CustomerDao();
    private OrderPurchasesDao orderPurchasesDao = new OrderPurchasesDao();
    private ControlValidValue controlValidValue = new ControlValidValue();


    public CartOrderService() throws SQLException, ClassNotFoundException {
    }

    public void addNewPurchaseToOrder(Customer customer) throws SQLException {
        Cart cart = new Cart(customer);
        customer.setCart(cart);
        int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());

            int cartId = cartDao.getId("cart", "customer", customerId);
            if (cartId != -1 && customerId!=-1 ){
            System.out.println("enter id : ");
            try {
                int productId = scanner.nextInt();
                Product product = productDao.findProductById(productId);

                if (product != null) {
                    customer.getCart().getListPurchases().add(product);
                    System.out.println("number of order : ");
                    int number = scanner.nextInt();
                    if (orderPurchasesDao.save(productId, cartId, customerId, number) == 1){
                        customer.getCart().setNumOfCartPurchases(customer.getCart().getNumOfCartPurchases()+1 );
                        if (cartDao.setNumber(customerId,customer.getCart().getNumOfCartPurchases()+1)==1) {

                            System.out.println(product + "insert into cart ");
                        }
                        else System.out.println(product + "but number of purchases does not update ");
                    }
                } else {
                    System.out.println("wrong id ");
                }
            } catch (NumberFormatException e) {
                System.out.println("enter id ");
            }
        } else {
            System.out.println("find customer id or cart id fail ");
        }
    }

    public void deletePurchaseFromCart(Customer customer) throws SQLException {
        int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());
        if (customerId != -1) {
            System.out.println("enter id product : ");
            try {
                int productId = scanner.nextInt();
                Product product = productDao.findProductById(productId);
                if (product != null) {
                    System.out.println("delete " + product + "\n from cart are you sure? Y/N ");
                    char deleteIt = controlValidValue.getValidChar();
                    if (deleteIt == 'y') {
                        customer.getCart().getListPurchases().remove(product);
                        if (orderPurchasesDao.delete(customerId, productId) == 1) {
                            customer.getCart().getListPurchases().remove(product);
                            customer.getCart().setNumOfCartPurchases(customer.getCart().getNumOfCartPurchases() - 1);
                            if (cartDao.setNumber(customerId, customer.getCart().getNumOfCartPurchases()) == 1) {
                                System.out.println("done");
                            } else System.out.println("delete done but number of purhases does not update ");
                        } else {
                            System.out.println("delete fail ");
                        }
                    } else System.out.println("delete canceled ");
                } else {
                    System.out.println("wrong id ");
                }
            } catch (NumberFormatException e) {
                System.out.println("enter id ");
            }
        } else {
            System.out.println("find customer id fail");
        }
    }

    public double calculateCartTotalPrice(Customer customer) throws SQLException {
        int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());
        if (customerId != -1) {
            List<Product> listOfCostAndNnmOfPurchase = orderPurchasesDao.findListOfCostAndNnmOfPurchase(customerId);
            double totalCost = 0;
            if (!listOfCostAndNnmOfPurchase.isEmpty()) {
                for (Product product : listOfCostAndNnmOfPurchase) {
                    totalCost = totalCost+(product.getCost()*product.getNumOfPurchases());

                }
                customer.getCart().setTotalCost(totalCost);
                return totalCost;
            } else System.out.println("find costs list fail ");
        } else
            System.out.println("find customer id fail");

        return -1;

    }

    public void confirmCart(Customer customer) throws SQLException {
        System.out.println("Are sure you want confirm carts ?Y/N");
        char isConfirm = controlValidValue.getValidChar();
        if (isConfirm == 'y') {
            int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());
            if (cartDao.confirmCart(customerId, CartStatus.CONFIRM) == 1) {
                boolean isUpdateWikiAllPurchases = true;
                for (Product product : customer.getCart().getListPurchases()) {
                    product.setWiki(product.getWiki() - 1);
                    if (productDao.updateWiki(product) != 1) {
                        isUpdateWikiAllPurchases = false;
                        System.out.println("update " + product.getName() + "wiki fail");
                    }
                }
                if (isUpdateWikiAllPurchases) {
                    System.out.println("confirm completely done ");

                } else System.out.println("confirm done but some wiki does nor update ");
            } else System.out.println("confirm fail ");
        }
        else System.out.println("confirm canceled ");


        }

    public void displayPurchases (Customer customer) throws SQLException {
        int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());
        if (customerId != -1) {
            List<Electronic> electronicPurchases = orderPurchasesDao.displayElectronicItem(customerId);
            if (!electronicPurchases.isEmpty()) {
                for (Electronic electronic : electronicPurchases) {
                    System.out.println("id : "+electronic.getId()+"  "+electronic);
                }
            } else System.out.println(" searching fail or there is no electronic item in your cart ");
            List<Shoes> shoesPurchases = orderPurchasesDao.displayShoesItem(customerId);
            if (!shoesPurchases.isEmpty()) {
                for (Shoes shoes : shoesPurchases) {
                    System.out.println("id : "+shoes.getId()+"  "+shoes);
                }
            } else System.out.println(" searching fail or there is no shoes item in your cart ");
            List<ReadableItems> readablePurchases = orderPurchasesDao.displayReadableItem(customerId);
            if (!readablePurchases.isEmpty()) {
                for (ReadableItems readableItems : readablePurchases) {
                    System.out.println("id :"+readableItems.getId()+"  "+readableItems);
                }
            } else System.out.println(" searching fail or there is no readable item in your cart ");
        }else System.out.println("find customer Id fail ");
    }

    }


