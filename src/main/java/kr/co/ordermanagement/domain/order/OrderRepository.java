package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.State;

import java.util.List;

public interface OrderRepository {

    Order add(Order order);

    Order findById(Long orderId);

    List<Order> findByState(State state);

}
