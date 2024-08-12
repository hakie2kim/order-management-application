package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final SimpleOrderService simpleOrderService;

    // 상품 주문 API
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> order(@RequestBody List<OrderProductRequestDto> orderProductRequestDtos) {
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtos);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

}
