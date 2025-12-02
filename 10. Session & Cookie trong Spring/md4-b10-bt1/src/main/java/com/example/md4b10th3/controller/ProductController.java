package com.example.md4b10th3.controller;

import com.example.md4b10th3.model.Cart;
import com.example.md4b10th3.model.Product;
import com.example.md4b10th3.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shop")
    public ModelAndView showShop() {
        ModelAndView mav = new ModelAndView("/shop");
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) {
        Optional<Product> productOpt = productService.findById(id);
        if (!productOpt.isPresent()) return "/error_404";

        cart.addProduct(productOpt.get());

        if ("show".equals(action)) return "redirect:/shopping-cart";
        return "redirect:/shop";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id) {
        Optional<Product> productOpt = productService.findById(id);
        if (!productOpt.isPresent()) return new ModelAndView("/error_404");

        ModelAndView mav = new ModelAndView("/product_detail");
        mav.addObject("product", productOpt.get());
        return mav;
    }
}
