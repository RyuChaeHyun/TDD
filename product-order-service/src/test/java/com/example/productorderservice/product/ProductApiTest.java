package com.example.productorderservice.product;

import com.example.productorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProductApiTest extends ApiTest {

    //스프링 부트 테스트
//    @Autowired
//    private ProductService productService;
//
//    @Test
//    void 상품등록() {
//        final AddProductRequest request = 상품등록요청_생성();
//        productService.addProduct(request);
//    }

    @Test
    void 상품등록() {
        final var request = 상품등록요청_생성();
        //API 요청
        final var response = 상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static AddProductRequest 상품등록요청_생성() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest("상품명", 1000, DiscountPolicy.NONE);
    }

    private static ExtractableResponse<Response> 상품등록요청(AddProductRequest request) {
        //given().log().all() : 요청을 보내는 로그를 남기겠다
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
        return response;
    }
}


