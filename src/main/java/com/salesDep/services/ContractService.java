package com.salesDep.services;

import com.salesDep.FileManager;
import com.salesDep.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContractService {

    public ObservableList<Product> loadProductsForContract(String contractId) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        FileManager.loadProductsFromFile(contractId, products);
        return products;
    }

    public boolean validateContractTerm(String termText) {
        try {
            int term = Integer.parseInt(termText);
            return term > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
