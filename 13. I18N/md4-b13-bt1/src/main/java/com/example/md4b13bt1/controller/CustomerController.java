package com.example.md4b13bt1.controller;

import com.example.md4b13bt1.model.Customer;
import com.example.md4b13bt1.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final ICustomerService customerService;
    private final MessageSource messageSource;

    public CustomerController(ICustomerService customerService, MessageSource messageSource) {
        this.customerService = customerService;
        this.messageSource = messageSource;
    }

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    @PostMapping("/customers")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.findAll());
            return "customers";
        }
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("message",
                messageSource.getMessage("customer.add.success", null, LocaleContextHolder.getLocale()));
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    @PostMapping("/customers/edit")
    public String editCustomer(@RequestParam Long id,
                               @Valid @ModelAttribute("customer") Customer customer,
                               BindingResult result, Model model) {
        customer.setId(id);
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.findAll());
            return "customers";
        }
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("message",
                messageSource.getMessage("customer.edit.success", null, LocaleContextHolder.getLocale()));
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@RequestParam Long id, Model model) {
        customerService.remove(id);
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("message",
                messageSource.getMessage("customer.delete.success", null, LocaleContextHolder.getLocale()));
        model.addAttribute("customer", new Customer());
        return "customers";
    }
}