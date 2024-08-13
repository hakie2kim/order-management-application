package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleProductService;
import kr.co.ordermanagement.presentation.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final SimpleProductService simpleProductService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts() {
        return simpleProductService.findAll();
    }

}
