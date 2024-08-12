package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderedProduct {

    private Long id;
    private String name;
    private Integer price;
    private Integer amount;

}
