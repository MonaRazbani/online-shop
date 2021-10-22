import models.person.Customer;
import services.CartOrderService;
import services.OnlineShopService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {
        OnlineShopService onlineShopService = new OnlineShopService();
        CartOrderService cartOrderService = new CartOrderService();
        Scanner scanner = new Scanner(System.in);
        mainMenu:
        while (true) {
            System.out.println("1: manager\n2:customer\n3:exit ");
            try {
                exitUserMenu:
                while (true) {
                    try {
                        int type = scanner.nextInt();
                        outer:
                        switch (type) {
                            case 1: {
                                managerMenu:
                                while (true) {
                                    System.out.println("1:add new product \n2:exit");
                                    try {
                                        int managerType = scanner.nextInt();
                                        switch (managerType) {
                                            case 1: {
                                                onlineShopService.addNewProductInOnlineShop();
                                                break;
                                            }
                                            case 2: {
                                                break exitUserMenu;

                                            }
                                            default: {
                                                System.out.println("wrong number");
                                                break outer;
                                            }
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("enter valid number ");
                                        break managerMenu;
                                    }
                                }
                            }
                            case 2: {
                                Customer customer = onlineShopService.customerSignUp();
                                if (onlineShopService.addNewCustomerInDB(customer)) {
                                    buyMenu:
                                    while (true) {
                                        System.out.println("1:add new purchase \n2:delete purchase \n3:get total cost of cart \n4:confirm cart \n5:show cart \n6:exit ");
                                        try {
                                            int customerMenuType = scanner.nextInt();
                                            switch (customerMenuType) {
                                                case 1: {
                                                    onlineShopService.DisplayProducts();
                                                    cartOrderService.addNewPurchaseToOrder(customer);
                                                    break;
                                                }
                                                case 2: {
                                                    cartOrderService.displayPurchases(customer);
                                                    cartOrderService.deletePurchaseFromCart(customer);
                                                }
                                                case 3: {
                                                    cartOrderService.calculateCartTotalPrice(customer);
                                                    System.out.println(customer.getCart().getTotalCost());
                                                    break;
                                                }
                                                case 4: {
                                                    cartOrderService.confirmCart(customer);
                                                    break;
                                                }
                                                case 5: {
                                                    cartOrderService.displayPurchases(customer);
                                                }
                                                case 6: {
                                                    break outer;
                                                }
                                                default: {
                                                    System.out.println("enter valid number");
                                                }
                                            }

                                        } catch (NumberFormatException e) {
                                            System.out.println("enter valid number ");
                                            break;
                                        }
                                    }
                                } else System.out.println("sign up fail ");
                            }
                            case 3: {
                                break exitUserMenu;
                            }
                            default: {
                                System.out.println("enter valid number ");
                                break exitUserMenu;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("enter valid number ");
                        break exitUserMenu;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}


