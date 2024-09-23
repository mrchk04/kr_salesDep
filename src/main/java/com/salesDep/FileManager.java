package com.salesDep;

import com.salesDep.model.Contract;
import com.salesDep.model.Customer;
import com.salesDep.model.Product;
import com.salesDep.model.SalesEngineer;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public final class FileManager {
    //Константи - шляхи до файлів
    private static final String CUSTOMER_FILE_PATH = "customerData.txt";
    private static final String CONTRACT_FILE_PATH = "orderData.txt";
    private static final String SALES_ENGINEER_FILE_PATH = "salesEngineerData.txt";


    //Робота з клієнтами

    public static void saveCustomersToFile(ObservableList<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE_PATH))) {
            for (Customer customer : customers) {
                writer.println(
                        customer.getFullName().get() + ";" +
                        customer.getNameOfCompany().get() + ";" +
                        customer.getAddress().get() + ";" +
                        customer.getPhoneNum().get() + ";"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomerFromFile(Customer customerToDelete) {
        List<Customer> customerList = readCustomersFromFile();
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerList);
        customers.remove(customerToDelete);
        saveCustomersToFile(customers);
    }

    public static ArrayList<Customer> readCustomersFromFile() {
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.length() <= 2) continue;
                String[] parts = line.split(";");
                StringProperty fullName = new SimpleStringProperty(parts[0]);
                StringProperty nameOfCompany = new SimpleStringProperty(parts[1]);
                StringProperty address = new SimpleStringProperty(parts[2]);
                StringProperty phoneNumber = new SimpleStringProperty(parts[3]);

                Customer customer = new Customer(fullName, nameOfCompany, address, phoneNumber);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }


    //Робота з контрактами

    public static void saveContractsToFile(List<Contract> contracts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONTRACT_FILE_PATH, false))) {
            for (Contract contract : contracts) {
                writer.println(
                        contract.getContractID().get() + ";" +
                                contract.getTerm().get() + ";" +
                                convertProductsToString(contract.getProducts())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContractsFromFile(Contract contractToDelete) {
        List<Contract> contractList = readContractsFromFile();
        ObservableList<Contract> contracts = FXCollections.observableArrayList(contractList);
        contracts.remove(contractToDelete);
        saveContractsToFile(contracts);
    }

    public static void loadProductsFromFile(String contractID, ObservableList<Product> productsList) {
        try (BufferedReader br = new BufferedReader(new FileReader(CONTRACT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 3 && parts[0].equalsIgnoreCase(contractID)) {
                    List<Product> productList = new ArrayList<>();

                    for (int i = 2; i < parts.length; i++) {
                        if(i< parts.length) {
                            String[] details = parts[i].split(",");
                            String type = details[0];
                            int quantity = Integer.parseInt(details[1]);
                            double cost = Double.parseDouble(details[2]);

                            Product product = new Product(type, quantity, cost);
                            productList.add(product);
                        } else break;
                    }

                    // Очистіть дані відображення продуктів перед додаванням нових продуктів
                    productsList.clear();

                    // Додайте продукти до списку
                    productsList.addAll(productList);

                    // Вийдіть з циклу, оскільки контракт знайдено
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertProductsToString(ListProperty<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : products) {
            stringBuilder.append(product.getProductType().get()).append(",")
                    .append(product.getQuantity().get()).append(",")
                    .append(product.getCost().get()).append(";");
        }
        return stringBuilder.toString();
    }

    public static List<Contract> readContractsFromFile() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CONTRACT_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contractData = line.split(";");
                Contract contract = new Contract(
                        new SimpleStringProperty(contractData[0]), // contractID
                        new SimpleIntegerProperty(Integer.parseInt(contractData[1])), // term
                        new SimpleListProperty<>(FXCollections.observableArrayList()) // Ініціалізуємо products списком
                );

                // Assuming products are stored in a specific format in the file
                for (int i = 2; i < contractData.length; i++) {
                    String[] productAttributes = contractData[i].split(",");

                    Product newProduct = new Product(null, null, null);
                    newProduct.getProductType().set(productAttributes[0]);
                    newProduct.getQuantity().set(Integer.parseInt(productAttributes[1]));
                    newProduct.getCost().set(Double.parseDouble(productAttributes[2]));
                    contract.addProduct(newProduct);
                }

                contracts.add(contract);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contracts;
    }


    //Робота з інженерами

    public static void saveSalesEngineerToFile(List<SalesEngineer> salesEngineers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SALES_ENGINEER_FILE_PATH, false))) {
            for (SalesEngineer salesEngineer: salesEngineers) {
                writer.println(
                        salesEngineer.getFullName().get() + ";" +
                                salesEngineer.getNameOfCompany().get() + ";" +
                                salesEngineer.getAddress().get() + ";" +
                                salesEngineer.getPhoneNum().get() + ";" +
                                salesEngineer.getExperience().get() + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSalesEngineerFromFile(SalesEngineer salesEngineerToDelete) {
        List<SalesEngineer> salesEngineerList = readSalesEngineerFromFile();
        ObservableList<SalesEngineer> salesEngineers = FXCollections.observableArrayList(salesEngineerList);
        salesEngineers.remove(salesEngineerToDelete);
        saveSalesEngineerToFile(salesEngineers);
    }

    public static ArrayList<SalesEngineer> readSalesEngineerFromFile() {
        ArrayList<SalesEngineer> salesEngineers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_ENGINEER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() <= 2) continue;
                String[] parts = line.split(";");
                StringProperty fullName = new SimpleStringProperty(parts[0]);
                StringProperty nameOfCompany = new SimpleStringProperty(parts[1]);
                StringProperty address = new SimpleStringProperty(parts[2]);
                StringProperty phoneNumber = new SimpleStringProperty(parts[3]);
                IntegerProperty exp = new SimpleIntegerProperty(Integer.parseInt(parts[4])); // Конвертуємо рядок в int

                SalesEngineer salesEngineer = new SalesEngineer(fullName, nameOfCompany, address, phoneNumber, exp);
                salesEngineers.add(salesEngineer);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return salesEngineers;
    }

}