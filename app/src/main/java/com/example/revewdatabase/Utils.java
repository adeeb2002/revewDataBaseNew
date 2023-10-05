package com.example.revewdatabase;

import java.util.ArrayList;

public class Utils {
    public static final ArrayList<Item> items=new ArrayList<>();
    public static void addItem(Item item){
        items.add(item);
    }
    public static ArrayList<Item> getItems(){
        return items;
    }
    public static void deleteItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)){
                items.remove(i);
            }
        }
    }
    public static void editItem(Item item){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId()==item.getId()){
                items.get(i).setCategory(item.getCategory());
                items.get(i).setName(item.getName());
                items.get(i).setPrice(item.getPrice());
                items.get(i).setImage(item.getImage());
            }
        }
    }
    public static int getIdItem(){
        return items.size()+1;
    }
}
