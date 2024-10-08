package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.State;
import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.order.OrderedProduct;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponseDto createOrder(List<OrderProductRequestDto> orderProductRequestDtos) {
        List<OrderedProduct> orderedProducts = makeOrderedProducts(orderProductRequestDtos);

        Order order = new Order(orderedProducts);
        orderRepository.add(order);

        return OrderResponseDto.toDto(order);
    }

    private List<OrderedProduct> makeOrderedProducts(List<OrderProductRequestDto> orderProductRequestDtos) {
        return orderProductRequestDtos
                .stream()
                .map(orderProductRequestDto -> {
                    Product product = productRepository.findById(orderProductRequestDto.getId());

                    Integer orderedAmount = orderProductRequestDto.getAmount();

                    // 재고 수량 확인 후 주문 수량만큼 감소
                    product.discountAmount(orderedAmount);

                    return new OrderedProduct(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            orderProductRequestDto.getAmount()
                    );
                }).toList();
    }

    public OrderResponseDto changeState(Long orderId, ChangeStateRequestDto changeStateRequestDto) {
        Order order = orderRepository.findById(orderId);

        State state = changeStateRequestDto.getState();
        order.forceChangeState(state);

        return OrderResponseDto.toDto(order);
    }

    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        return OrderResponseDto.toDto(order);
    }

    public List<OrderResponseDto> getOrderByState(State state) {
        List<Order> orders = orderRepository.findByState(state);
        return orders
                .stream()
                .map(OrderResponseDto::toDto)
                .toList();
    }

    public OrderResponseDto cancelById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
        return OrderResponseDto.toDto(order);
    }

}
