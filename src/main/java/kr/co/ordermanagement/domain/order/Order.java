package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.State;
import kr.co.ordermanagement.domain.exception.CannotCancelStateException;
import kr.co.ordermanagement.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
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

    public Boolean isSameId(Long id) {
        return Objects.equals(this.id, id);
    }

    public Boolean isSameState(State state) {
        return Objects.equals(this.state, state);
    }

    public void forceChangeState(State state) {
        this.state = state;
    }

    public void cancel() {
        if (this.state == State.CREATED) {
            this.state = State.CANCELED;
            return;
        }
        throw new CannotCancelStateException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
    }

}
