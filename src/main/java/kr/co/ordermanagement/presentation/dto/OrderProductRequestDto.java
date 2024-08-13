package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderProductRequestDto {

    private Long id;
    private Integer amount;

    public static OrderProductRequestDto toDto(OrderedProduct orderedProduct) {
        return new OrderProductRequestDto(
                orderedProduct.getId(),
                orderedProduct.getAmount()
        );
    }

}
