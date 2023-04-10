package com.example.productorderservice.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//생성자 주입 final 사용 이유
//값의 불변을 보장할 수 있음
//생성자를 통해 의존성을 설정하고 변경할 일이 없으므로 final으로 안전하게 불변을 보장할 수 있음


//@Component
@RestController
@RequestMapping("/products")
public class ProductService {

    //요청 시 흐름
    //Service -> Port(Interface) -> Adapter
    private final ProductPort productPort;

    ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}