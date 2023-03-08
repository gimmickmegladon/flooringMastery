package com.example.flooringMastery.controller;

import com.example.flooringMastery.model.Order;
import com.example.flooringMastery.service.FlooringService;
import com.example.flooringMastery.view.FlooringView;
import com.example.flooringMastery.view.UserIO;

import java.util.ArrayList;
import java.util.List;

public class FlooringController {
    private final FlooringService service;
    private final FlooringView view;

    public FlooringController(FlooringService flooringService, FlooringView flooringView) {
        this.service = flooringService;
        this.view = flooringView;
    }

    public void startFlooring() {
        int userInput;
        String date;
        int id;
        String orderPath = "";
        String productPath = "SampleFileData/Data/Products.txt";
        String taxPath = "SampleFileData/Data/Taxes.txt";
        service.getData(orderPath, productPath, taxPath);
        view.mainMenu();
        while (true) {
            userInput = view.getInput();
            switch (userInput) {

                case 0:
                    view.mainMenu();
                    break;

                case 1:
                    date = view.getDate();

                    List orders = service.getOrdersForDay(date).getOrders();
                    if (orders.isEmpty()) {
                        view.notFound();
                    } else {
                        view.listOrders((ArrayList<Order>) orders, date);
                    }
                    break;

                case 2:
                    String[] toAdd = view.addOrder();
                    Order newOrder = service.addValidation(toAdd);
                    if (newOrder.getOrderNum() == -1) {
                        view.addFailure();
                    } else {
                        newOrder.setOrderNum(service.getOrdersForDay(toAdd[0]).getNextNum());
                        service.populateDataForOrder(newOrder);
                        view.displayFoundOrder(newOrder.toString());
                        String confirm = view.confirmAdd();
                        if (confirm.toLowerCase().trim().equals("y")) {
                            service.addOrder(newOrder, toAdd[0]);
                            view.added();
                        } else {
                            view.abort();
                        }
                    }
                    break;

                case 3:
                    date = view.getDate();
                    id = view.getID();
                    Order result = service.findOrder(date, id);
                    if (result.getOrderNum() == -1) {
                        view.notFound();
                    } else {
                        String[] toEdit = view.editOrder(result.toString(), result.getCustomerName(),
                                result.getState(), result.getProductType(), String.valueOf(result.getArea()));
                        Order editedOrder = service.editOrderValidation(date, id, result, toEdit);

                        if (editedOrder.getOrderNum() != -1) {
                            System.out.println("edited order");
                            String confirm = view.confirmEdit(editedOrder.toString());
                            if (confirm.toLowerCase().trim().equals("y")) {
                                service.replaceOrder(date, result, editedOrder);
                            }
                        } else {
                            view.editFailure();
                        }
                    }
                    break;


                case 4:
                    date = view.getDate();
                    id = view.getID();
                    result = service.findOrder(date, id);
                    if (result.getOrderNum() == -1) {
                        view.notFound();
                    } else {
                        view.displayFoundOrder(result.toString());
                        String confirm = view.confirmDelete();
                        if (confirm.toLowerCase().trim().equals("y")) {
                            service.removeOrder(result, date);
                            view.removed();
                        } else {
                            view.abort();
                        }
                    }
                    break;

                case 5:
                    service.exportAll();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    view.invalidInput();
                    break;
            }

        }
    }

}
