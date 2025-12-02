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
public class ShoppingController {

    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart(@SessionAttribute("cart") Cart cart) {
        ModelAndView mav = new ModelAndView("/cart");
        mav.addObject("cart", cart);
        return mav;
    }

    @PostMapping("/update-cart")
    public String updateCart(@RequestParam Long productId,
                             @RequestParam int quantity,
                             @ModelAttribute("cart") Cart cart) {
        productService.findById(productId)
                .ifPresent(product -> cart.updateProductQuantity(product, quantity));
        return "redirect:/shopping-cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam Long productId,
                                 @ModelAttribute("cart") Cart cart) {
        productService.findById(productId)
                .ifPresent(cart::removeProduct);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/checkout")
    public ModelAndView showCheckout(@SessionAttribute("cart") Cart cart) {
        ModelAndView mav = new ModelAndView("/checkout");
        mav.addObject("cart", cart);
        return mav;
    }

    @PostMapping("/process-checkout")
    public String processCheckout(@ModelAttribute("cart") Cart cart) {
        // Xử lý thanh toán ở đây (có thể lưu đơn hàng vào DB)
        cart.getProducts().clear();
        return "redirect:/checkout-success";
    }

    @GetMapping("/checkout-success")
    public String checkoutSuccess() {
        return "/checkout_success";
    }
}
