package com.example.md4b3btsudungthymeleafchoungdung.controller;

import com.example.md4b3btsudungthymeleafchoungdung.model.Product;
import com.example.md4b3btsudungthymeleafchoungdung.service.IProductService;
import com.example.md4b3btsudungthymeleafchoungdung.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("title", "Danh sách sản phẩm");
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("title", "Thêm sản phẩm mới");
        return "/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product product) {
        product.setId((int) (Math.random() * 100000));
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Product p = productService.findById(id);
        model.addAttribute("product", p);
        model.addAttribute("title", "Cập nhật sản phẩm");
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable int id, Model model) {
        Product p = productService.findById(id);
        model.addAttribute("product", p);
        model.addAttribute("title", "Xoá sản phẩm");
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable ("id") int id, Model model) {
        Product p = productService.findById(id);
        model.addAttribute("product", p);
        model.addAttribute("title", "Chi tiết sản phẩm");
        return "view";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("products", productService.searchByName(keyword));
        model.addAttribute("title", "Tìm kiếm sản phẩm");
        return "search";
    }
}
