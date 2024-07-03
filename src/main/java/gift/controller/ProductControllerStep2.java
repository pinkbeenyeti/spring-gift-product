package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class ProductControllerStep2 {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("v2/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("v2/products")
    public String addProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
        products.put(newProduct.getId(), newProduct);

        return "redirect:/v2/products";
    }

    @PostMapping("v2/products/{id}")
    public String modifyProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = products.get(id);

        updatedProduct.setName(productDTO.name());
        updatedProduct.setPrice(productDTO.price());
        updatedProduct.setImageUrl(productDTO.imageUrl());

        return "redirect:/v2/products";
    }

    @DeleteMapping("v2/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        products.remove(id);
        return "redirect:/v2/products";
    }
}