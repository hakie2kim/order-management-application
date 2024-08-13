package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.State;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final SimpleOrderService simpleOrderService;

    // 상품 주문 API
    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody List<OrderProductRequestDto> orderProductRequestDtos) {
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtos);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    // 주문상태 강제 변경 API
    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> changeOrderState(@PathVariable Long orderId, @RequestBody ChangeStateRequestDto changeStateRequestDto) {
        OrderResponseDto orderResponseDto = simpleOrderService.changeState(orderId, changeStateRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    // 주문번호로 조회 API
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.getOrderById(orderId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    // 주문상태로 조회 API
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrderByState(@RequestParam State state) {
        List<OrderResponseDto> orderResponseDto = simpleOrderService.getOrderByState(state);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

}
