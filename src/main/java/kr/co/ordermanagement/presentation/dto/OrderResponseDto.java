package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.State;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderResponseDto {

    private Long id;
    private List<OrderedProductDto> orderedProducts;
    private Integer totalPrice;
    private State state;

    public static OrderResponseDto toDto(Order order) {
        List<OrderedProductDto> orderedProductDtos = order.getOrderedProducts()
                .stream()
                .map(OrderedProductDto::toDto)
                .toList();

        return new OrderResponseDto(
                order.getId(),
                orderedProductDtos,
                order.getTotalPrice(),
                order.getState()
        );
    }

}
