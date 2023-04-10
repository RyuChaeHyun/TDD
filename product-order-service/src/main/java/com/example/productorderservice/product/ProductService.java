package com.example.productorderservice.product;

public class ProductService {

    //요청 시 흐름
    //Service -> Port(Interface) -> Adapter
    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }
}