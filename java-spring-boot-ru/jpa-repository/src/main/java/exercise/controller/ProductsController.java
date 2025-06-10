package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> index(@RequestParam(required = false) Double min, @RequestParam(required = false) Double max) {
        if (min != null && max != null) {
            if (min > max) {
                throw new IllegalArgumentException("Min price cannot be greater than max price");
            }
            return productRepository.findByPriceBetweenOrderByPriceAsc(min, max);
        } else if (min != null) {
            return productRepository.findByPriceGreaterThanEqualOrderByPriceAsc(min);
        } else if (max != null) {
            return productRepository.findByPriceBetweenOrderByPriceAsc(0.0, max);
        } else {
            return productRepository.findAll().stream()
                    .sorted((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()))
                    .toList();
        }
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
