package com.stefanini.challenge.controller;

import com.stefanini.challenge.model.Client;
import com.stefanini.challenge.model.OrderDetail;
import com.stefanini.challenge.model.Product;
import com.stefanini.challenge.service.interfaces.IClientService;
import com.stefanini.challenge.service.interfaces.IOrderService;
import com.stefanini.challenge.service.interfaces.IProductService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedBean
@RequestScoped
public class NewOrderController implements Serializable {

    private final IClientService clientService;
    private final IProductService productService;
    private final IOrderService orderService;
    private List<Client> clients;
    private Client selectedClient;
    private List<OrderDetail> items;
    private List<Product> products;
    private Product selectedProduct;
    private int quantity;
    private double amount;

    public NewOrderController(IClientService clientService,
                              IProductService productService,
                              IOrderService orderService){
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        selectedProduct = Product.builder().build();
        products = productService.findAllProducts();
        clients = clientService.findAllClients();
    }

    public List<String> completeClientInformation(String query) {
        String queryLowerCase = query.toLowerCase();

        return clients.stream().map(Client::toString).filter(t -> t.toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public List<String> completeProductInformation(String query) {
        String queryLowerCase = query.toLowerCase();

        return products.stream().map(Product::getName).filter(name -> name.toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client client) {
        selectedClient = client;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        if(selectedProduct != null && !selectedProduct.getName().equals("")) amount = quantity * selectedProduct.getPrice();
        this.quantity = quantity;
    }

    public double getAmount(){
        return amount;
    }

    public void addItem(){
        if(selectedProduct  != null && !selectedProduct.getName().equals("")) {
            double unitPrice = selectedProduct.getPrice();
            OrderDetail item = OrderDetail.builder().product(selectedProduct)
                    .quantity(quantity)
                    .unitPrice(unitPrice)
                    .amount(quantity * unitPrice).build();

            items.add(item);
        } else {
            String message = "It's necessary selection an product";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
        }
    }

    public Product getSelectedProduct(){
        return selectedProduct;
    }

    public void setSelectedProduct(Product product){
        selectedProduct = product;
    }

    private void clearFields(){
        items = new ArrayList<>();
        selectedClient = null;
        selectedProduct = null;
        quantity = 0;
        amount = 0;
    }

    public List<OrderDetail> getItems(){
        return items;
    }

    public void removeItem(OrderDetail item){
        items.remove(item);
    }

    public double getAmountOrder(){
        return items.stream().mapToDouble(OrderDetail::getAmount).sum();
    }

    public boolean saveOrder() throws Exception {
        boolean success = orderService.saveOrder(selectedClient, items);
        String message = success ? "Order saved with success" : "Error saving order";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));

        if(success) {
            clearFields();
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }

        return success;
    }

    public void returnPage() throws IOException {
        clearFields();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
