package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.State;
import kr.co.ordermanagement.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Order {

    private Long id;
    private List<OrderedProduct> orderedProducts;
    private Integer totalPrice;
    private State state;

    public void setId(Long id) {
        this.id = id;
    }

    public Order(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.totalPrice = orderedProducts
                .stream()
                .mapToInt(orderedProduct -> orderedProduct.getPrice() * orderedProduct.getAmount())
                .sum();
        this.state = State.CREATED;
    }

}
