package services;

import controlValidValue.ControlValidValue;
import dataAccess.AddressDao;
import dataAccess.CartDao;
import dataAccess.CustomerDao;
import dataAccess.OrderPurchasesDao;
import dataAccess.ProductDaos.ElectronicProductsDao;
import dataAccess.ProductDaos.ProductDao;
import dataAccess.ProductDaos.ReadableItemProductsDao;
import dataAccess.ProductDaos.ShoesProductsDao;
import exceptions.ExceptionInput;
import models.Address;
import models.enums.*;
import models.person.Customer;
import models.products.Electronic;
import models.products.Product;
import models.products.ReadableItems;
import models.products.Shoes;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class OnlineShopService {

    private ControlValidValue controlValidValue = new ControlValidValue();
    private AddressDao addressDao = new AddressDao();
    private CustomerDao customerDao = new CustomerDao();
    private ElectronicProductsDao electronicProductsDao = new ElectronicProductsDao();
    private ShoesProductsDao shoesProductsDao = new ShoesProductsDao();
    private ReadableItemProductsDao readableItemProductsDao = new ReadableItemProductsDao();
    private CartDao cartDao = new CartDao();
    private ProductDao productDao = new ProductDao();
    static final Scanner scanner = new Scanner(System.in);

    public OnlineShopService() throws SQLException, ClassNotFoundException {
    }

    private Product addNewProduct() throws ExceptionInput {

        boolean isContinue = true;
        boolean isValidInfo = true;
        while (isContinue) {
            System.out.println("enter product info like this \n name,cost,companyName,wiki");
            scanner.nextLine();
            String productInfo = scanner.nextLine();
            String[] arrInfo = productInfo.split(",", 4);
            String name = arrInfo[0];
            try {
                double cost = Double.parseDouble(arrInfo[1]);
            } catch (NumberFormatException e) {
                isValidInfo = false;
                System.out.println("cost is not valid ");
            }
            String companyName = arrInfo[2];

            try {
                int wiki = Integer.parseInt(arrInfo[3]);
            } catch (NumberFormatException e) {
                isValidInfo = false;
                System.out.println("wiki is not valid ");
            }
            if (isValidInfo) {
                return new Product(Double.parseDouble(arrInfo[1]), name, ProductStatus.EXIST, companyName, Integer.parseInt(arrInfo[3]));
            }
            isContinue = !isValidInfo;

        }
        return null;
    }

    public Electronic addNewElectronicProduct() throws ExceptionInput {
        Product product = addNewProduct();
        Electronic electronic = new Electronic(product.getCost(), product.getName(), product.getStatus(), product.getCompanyName(), product.getWiki());


        boolean isContinue = true;
        boolean isValidInfo = true;
        while (isContinue) {
            String type = scanner.next();
            System.out.println("enter Type : TELEVISION or RADIO");
            try {
                try {
                    if (ElectronicType.valueOf(type).getVal(type).equals("NOT_SET")) {
                        isValidInfo = false;
                        throw new ExceptionInput("type is not valid ");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                if (isValidInfo) {
                    electronic.setType(ElectronicType.valueOf(type));
                    return electronic;
                }
                isContinue = !isValidInfo;
            } catch (IllegalArgumentException e) {
                System.out.println("type is not valid ");
            }
        }

        return null;
    }

    public Shoes addNewShoesProduct() throws ExceptionInput {
        Product product = addNewProduct();
        Shoes shoes = new Shoes(product.getCost(), product.getName(), product.getStatus(), product.getCompanyName(), product.getWiki());

        System.out.println("enter Type  : SPORT or FORMAL");
        boolean isContinue = true;
        boolean isValidInfo = true;
        while (isContinue) {
            String type = scanner.next();
            try {
                if (ShoesType.valueOf(type).getVal(type).equals("NOT_SET")) {
                    isValidInfo = false;
                    throw new ExceptionInput("type is not valid ");
                }
            } catch (ExceptionInput exceptionInput) {
                System.out.println(exceptionInput.getMessage());
            }
            System.out.println("enter size : ");
            try {
                int size = scanner.nextInt();
                if (isValidInfo) {
                    shoes.setType(ShoesType.valueOf(type));
                    shoes.setSize(size);
                    isContinue = !isValidInfo;
                    return shoes;
                }
            } catch (NumberFormatException e) {
                System.out.println("size is not valid ");
            }
        }
        return null;
    }

    public ReadableItems addNewReadableItemProduct() throws ExceptionInput {
        Product product = addNewProduct();
        ReadableItems readableItems = new ReadableItems(product.getCost(), product.getName(), product.getStatus(), product.getCompanyName(), product.getWiki());

        System.out.println("enter Type  : JOURNAL or BOOK ");
        boolean isContinue = true;
        boolean isValidInfo = true;
        while (isContinue) {
            String type = scanner.next();
            try {
                if (ReadableItemsType.valueOf(type).getVal(type).equals("NOT_SET")) {
                    isValidInfo = false;
                    throw new ExceptionInput("type is not valid ");
                }
            } catch (ExceptionInput exceptionInput) {
                System.out.println(exceptionInput.getMessage());
            }
            System.out.println("enter subject : ");
            String subject = scanner.next();
            try {
                if (!controlValidValue.isValidName(subject)) {
                    isValidInfo = false;
                    throw new ExceptionInput("subject is not valid ");
                }
            } catch (ExceptionInput exceptionInput) {
                System.out.println(exceptionInput.getMessage());
            }
            if (isValidInfo) {
                readableItems.setSubject(subject);
                readableItems.setType(ReadableItemsType.valueOf(type));
                return readableItems;
            }
            isContinue = !isValidInfo;
        }
        return null;
    }

    public Customer customerSignUp() throws Exception {
        System.out.println("Please insert your Signup information like this :\n " +
                "  first_name,last_name,username,password,national_code,phone,gender");
        boolean isContinue = true;
        boolean isValidInfo = true;
        boolean isValidAddress = true;
        while (isContinue) {
            try {
                String info = scanner.nextLine();
                String[] arrInfo = info.split(",", 7);
                String firstName = arrInfo[0];
                String lastName = arrInfo[1];
                try {
                    if (!controlValidValue.isValidName(firstName) && !controlValidValue.isValidName(lastName)) {
                        isValidInfo = false;
                        throw new Exception("first name or last name is not valid ");
                    }
                } catch (ExceptionInput e) {
                    System.out.println(e.getMessage());

                }
                String userName = arrInfo[2];
                try {
                    if (!controlValidValue.isValidUserName(userName)) {
                        isValidInfo = false;
                        throw new ExceptionInput("username most include more then 3 words ");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                String password = arrInfo[3];
                try {
                    if (!controlValidValue.isValidPassword(password)) {
                        isValidInfo = false;
                        throw new ExceptionInput("password most include more than 3  number ");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                String nationalCode = arrInfo[4];
                try {
                    if (!controlValidValue.isValidUserNationalCode(nationalCode)) {
                        isValidInfo = false;
                        throw new ExceptionInput("nationalCode is not valid ");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                String phoneNumber = arrInfo[5];
                try {
                    if (!controlValidValue.isValidPhoneNumber(phoneNumber)) {
                        isValidInfo = false;
                        throw new ExceptionInput("wrong phone number");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                Gender gender = Gender.valueOf(arrInfo[6]);
                try {
                    if (gender.getVal(arrInfo[6]).equals("NOT_SET")) {
                        isValidInfo = false;
                        throw new ExceptionInput("enter valid gender");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }

                System.out.println("Please enter your address like this :\n " +
                        "city,Street,alley,house number,post_code");

                String addressInfo = scanner.nextLine();
                String[] arrAddressInfo = addressInfo.split(",", 5);
                String city = arrAddressInfo[0];
                try {
                    if (!controlValidValue.isValidName(city)) {
                        isValidAddress = false;
                        throw new ExceptionInput("wrong city");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }
                String street = arrAddressInfo[1];
                String allay = arrAddressInfo[2];
                try {
                    int houseNumber = Integer.parseInt(arrAddressInfo[3]);
                } catch (NumberFormatException e) {
                    isValidAddress = false;
                    System.out.println("wrong house number ");
                }
                String postCode = arrAddressInfo[4];
                try {
                    if (!controlValidValue.isValidPostCode(postCode)) {
                        isValidAddress = false;
                        throw new ExceptionInput("wrong post code ");
                    }
                } catch (ExceptionInput exceptionInput) {
                    System.out.println(exceptionInput.getMessage());
                }

                if (isValidInfo) {
                    Customer newCustomer = new Customer(firstName, lastName, userName, password, nationalCode, phoneNumber, gender);
                    if (isValidAddress) {
                        Address customerAddress = new Address(city, street, allay, Integer.parseInt(arrAddressInfo[3]), postCode);
                        newCustomer.setAddress(customerAddress);
                        return newCustomer;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("wrong info");
                isValidInfo = false;
            }
            isContinue = !isValidAddress && isValidInfo;
        }
        return null;
    }

    public void addNewProductInOnlineShop() throws ExceptionInput, SQLException {
        System.out.println("add new product \n enter type :\n 1.electronic product \n 2. readable item product \n 3.shoes product");
        try {
            int type = scanner.nextInt();
            switch (type) {
                case 1: {
                    Electronic electronic = addNewElectronicProduct();
                    addNewProductInDB(electronic);
                    break;
                }
                case 2: {
                    ReadableItems readableItems = addNewReadableItemProduct();
                    addNewProductInDB(readableItems);
                    break;
                }
                case 3: {
                    Shoes shoes = addNewShoesProduct();
                    addNewProductInDB(shoes);
                    break;
                }
                default:
                    System.out.println("wrong number");
            }
        } catch (NumberFormatException e) {
            System.out.println("enter number");
        }
    }

    public boolean addNewCustomerInDB(Customer customer) throws SQLException {
        boolean signup = false;
        if (addressDao.saveAddress(customer.getAddress()) == 1) {
            int addressId = addressDao.getId("address", "post_code", customer.getAddress().getPostCode());

            if (customerDao.save(customer, addressId) == 1) {
                int customerId = customerDao.getId("customer", "national_code", customer.getNationalCode());
                if (cartDao.save(customer.getCart(), customerId) == 1) {
                    System.out.println(customer);
                    signup = true;
                } else {
                    System.out.println("customer " + customer + "saved but insert cart fail ");
                }
            }
        } else {
            System.out.println("signup fail");
            signup = false;
        }
        return signup;
    }

    public boolean addNewProductInDB(Electronic electronic) throws SQLException {
        if (productDao.save(electronic) == 1) {
            int electronicId = productDao.findProductId(electronic);
            if (electronicProductsDao.save(electronic, electronicId) == 1) {
                System.out.println(electronic);
                return true;
            }
        } else {
            System.out.println("insert fail");
        }
        return false;
    }

    public boolean addNewProductInDB(Shoes shoes) throws SQLException {
        if (productDao.save(shoes) == 1) {
            int shoesId = productDao.findProductId(shoes);
            if (shoesProductsDao.save(shoes, shoesId) == 1) {
                System.out.println(shoes);
                return true;
            } else
                System.out.println("insert in shoes product table fail ");
        } else {
            System.out.println("insert fail");
        }
        return false;
    }

    public boolean addNewProductInDB(ReadableItems readableItems) throws SQLException {
        if (productDao.save(readableItems) == 1) {
            int readableItemsID = productDao.findProductId(readableItems);
            if (readableItemProductsDao.save(readableItems, readableItemsID) == 1) {
                System.out.println(readableItems);
                return true;
            } else {
                System.out.println("insert in readableItem table fail ");
            }
            System.out.println("insert fail");
        }
        return false;
    }

    public void DisplayProducts() throws SQLException {

        System.out.println("******* ELECTRONIC *******");
        List<Electronic> electronicProduct = electronicProductsDao.displayElectronicProduct();
        if (!electronicProduct.isEmpty()) {
            for (Electronic electronic : electronicProduct) {
                System.out.println("id :" + electronic.getId() + electronic);
            }
        } else System.out.println(" searching fail");
        System.out.println("******* SHOES *******");
        List<Shoes> shoesProduct = shoesProductsDao.displayShoesProduct();
        if (!shoesProduct.isEmpty()) {
            for (Shoes shoes : shoesProduct) {
                System.out.println("id :" + shoes.getId() + shoes);
            }
        } else System.out.println(" searching fail  ");
        System.out.println("******* READABLE ITEMS *******");
        List<ReadableItems> readableproduct = readableItemProductsDao.displayReadableProduct();
        if (!readableproduct.isEmpty()) {
            for (ReadableItems readableItems : readableproduct) {
                System.out.println("id :" + readableItems.getId() + readableItems);
            }
        } else System.out.println(" searching fail or there is no readable item in your cart ");
    }

}




