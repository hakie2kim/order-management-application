package kr.co.ordermanagement.infrastructure;

import kr.co.ordermanagement.domain.exception.EntityNotFoundException;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class ListOrderRepository implements OrderRepository {

    private List<Order> orders = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Order add(Order order) {
        order.setId(sequence.getAndAdd(1L));
        orders.add(order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        log.info("{}", id);
        return orders
                .stream()
                .filter(order -> order.isSameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("주문을 찾지 못했습니다."));
    }

}
