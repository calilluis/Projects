package edu.upf.dism.findit;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GlobalVariables extends Application {

    boolean isRegistered = false;



    boolean already_filled = false;
    Set<String> categories = new HashSet<String>();
//    private String[][] products=new String[250][3];
    ArrayList<String[]> products = new ArrayList<String[]>();
    //getters
    public Set getCategories() {
        return categories;
    }
    public ArrayList getProducts() {
        return products;
    }
    //setters
    public void addCategory(String cat) {
        this.categories.add(cat);
    }
    public void setCategories(Set cat) {
        this.categories = cat;
    }
    public void setProducts(ArrayList prod) {
        this.products = prod;
    }
    public void addProduct(String[] prod) {
        this.products.add(prod);

    }
    public ArrayList<String> getresult(String str) {
        ArrayList<String> result = new ArrayList<String>();
        for (String[] element : products) {
            if(element[1].toLowerCase().contains(str)){
                String strr= element[1]+" ----- Prestatgeria: "+ element[2];
                result.add(strr);
            }
        }
        return result;

    }
    public ArrayList<String> getresult_byCategory(String str) {
        ArrayList<String> result = new ArrayList<String>();
        for (String[] element : products) {
            if(element[0].contains(str)){
                String strr= element[1]+" ----- Prestatgeria: "+ element[2];
                result.add(strr);
            }
        }
        return result;

    }
    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isAlready_filled() {
        return already_filled;
    }

    public void setAlready_filled(boolean already_filled) {
        this.already_filled = already_filled;
    }
}
