package com.example.flooringMastery.view;

import com.example.flooringMastery.model.Order;

import java.util.ArrayList;

public class FlooringView {
    private final UserIO io;
    public FlooringView(UserIO io){
        this.io = io;
    }

    public int getInput(){
        return io.readInt(" >>> ");
    }

    public void mainMenu(){
        io.print("\n");
        io.print("|--------------------||---------------------------------------------|");
        io.print("|--------------------||.'.'.'',    Flooring Order Book      ,''.'.'.|");
        io.print("|------- MENU -------||.'.'.'.'',                         ,''.'.'.'.|");
        io.print("|--------------------||.'.'.'.'.|                         |.'.'.'.'.|");
        io.print("|--------------------||.'.'.'.'.|===;                 ;===|.'.'.'.'.|");
        io.print("|1. Display Orders   ||.'.'.'.'.|:::|',             ,'|:::|.'.'.'.'.|");
        io.print("|2. Add Order  ⠀     ||.'.'.'.'.|---|'.|, _______ ,|.'|---|.'.'.'.'.|");
        io.print("|3. Edit Order    ⠀  ||.'.'.'.'.|:::|'.|'|???????|'|.'|:::|.'.'.'.'.|");
        io.print("|4. Remove Order⠀    ||,',',',',|---|',|'|???????|'|,'|---|,',',',',|");
        io.print("|5. Export all Data  ||.'.'.'.'.|:::|'.|'|???????|'|.'|:::|.'.'.'.'.|");
        io.print("|6. Exit             ||.'.'.'.'.|---|','   /%%%\\   ','|---|.'.'.'.'.|");
        io.print("|                    ||.'.'.'.'.|===:'    /%%%%%\\    ':===|.'.'.'.'.|");
        io.print("|0. Menu             ||.'.'.'.'.|%%%%%%%%%%%%%%%%%%%%%%%%%|.'.'.'.'.|");
        io.print("|--------------------||.'.'.'.','       /%%%%%%%%%\\       ','.'.'.'.|");
        io.print("|--------------------||.'.'.','        /%%%%%%%%%%%\\        ','.'.'.|");
        io.print("|--------------------||.'.','         /%%%%%%%%%%%%%\\         ','.'.|");
        io.print("|--------------------||---------------------------------------------|");
    }

    public void listOrders(ArrayList<Order> list, String date){
        io.print("Order list for date - "+date+" - in format:");
        io.print("#, Customer Name, State, Tax Rate, Product, Area, Cost/sq ft, Labor/sq ft, Material Cost, Labor Cost, Tax, Total");
        for (int i = 0; i < list.size(); i++) {
            io.print(list.get(i).toString());
        }
    }

    public String getDate(){
        io.print("Please type in the date of the order/s you're looking for:");
        io.print(" -eg: MMddyyyy");
        return io.readString(" >>> ");
    }

    public int getID() {
        io.print("Please type in the ID of the order/s you're looking for:");
        io.print(" -eg: 3");
        return io.readInt(" >>> ");
    }

    public String[] addOrder(){
        String[] userValues = new String[5];
        io.print(" --- Tip! You can leave fields blank(press Enter) to leave them unchanged! ---");
        userValues[0] = io.readString("Enter order date: ");
        userValues[1] = io.readString("Enter customer name: ");
        userValues[2] = io.readString("Enter order state: ");
        userValues[3] = io.readString("Enter product type: ");
        userValues[4] = io.readString("Enter area: ");
        return userValues;
    }

    public String[] editOrder(String order, String name, String state, String type, String area){
        io.print("The following order was found - in format:");
        io.print("#, Customer Name, State, Tax Rate, Product, Area, Cost/sq ft, Labor/sq ft, Material Cost, Labor Cost, Tax, Total");
        io.print(order);
        String[] userValues = new String[4];
        io.print(" --- Tip! You can leave fields blank(press Enter) to leave them unchanged! ---");
        userValues[0] = io.readString("Enter customer name ("+name+"): ");
        userValues[1] = io.readString("Enter order state ("+state+"): ");
        userValues[2] = io.readString("Enter product type ("+type+"): ");
        userValues[3] = io.readString("Enter area ("+area+"): ");
        return userValues;
    }

    public void displayFoundOrder(String foundOrder){
        io.print(foundOrder);
    }

    public void invalidInput() {
        io.print(" ----- INVALID INPUT -----");
        io.print("You can enter '0' to view the main menu again.");
    }

    public void notFound() {
        io.print("Sorry, the information you requested wasn't found.");
        mainMenu();
    }

    public String confirmDelete() {
        io.print("Are you sure you would like to delete the displayed order? (y/n)");
        return io.readString(" >>> ");
    }

    public void abort() {
        io.print(" -/-/-/-/- ABORTING - RETURNING TO MAIN MENU -\\-\\-\\-\\-");
        mainMenu();
    }

    public void removed() {
        io.print("Successfully removed the order from the database!");
        mainMenu();
    }

    public void editFailure() {
        io.print("Failed to edit order, some of the data you entered was not of the right type/format!");
        mainMenu();
    }

    public String confirmEdit(String order) {
        io.print("Are you sure you would like to edit the order as displayed? (y/n)");
        io.print(order);
        return io.readString(" >>> ");
    }

    public void addFailure() {
        io.print("Failed to add order, some of the data you entered was not of the right type/format!");
        mainMenu();
    }

    public String confirmAdd() {
        io.print("Are you sure you would like to add the displayed order? (y/n)");
        return io.readString(" >>> ");
    }

    public void added() {
        io.print("Order successfully added to the book!");
        mainMenu();
    }
}
