package com.example.md4b4bt3.controller;

import com.example.md4b4bt3.model.HealthDeclaration;
import com.example.md4b4bt3.service.HealthDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HealthDeclarationController {

    @Autowired
    private HealthDeclarationService declarationService;

    @GetMapping("/")
    public String showForm(Model model) {
        HealthDeclaration declaration = declarationService.getLatest();
        if (declaration == null) {
            declaration = new HealthDeclaration();
        }
        model.addAttribute("declaration", declaration);
        return "form";
    }

    @PostMapping("/submit")
    public String submitDeclaration(@ModelAttribute HealthDeclaration declaration, Model model) {
        System.out.println("=== DEBUG: FORM SUBMISSION START ===");

        // DEBUG: In ra tất cả các field để kiểm tra
        System.out.println("FullName: " + declaration.getFullName());
        System.out.println("YearOfBirth: " + declaration.getYearOfBirth());
        System.out.println("Gender: " + declaration.getGender());
        System.out.println("Passport: " + declaration.getPassportNumber());
        System.out.println("TransportType: " + declaration.getTransportType());
        System.out.println("DepartureDate: " + declaration.getDepartureDate());
        System.out.println("Province: " + declaration.getProvince());
        System.out.println("District: " + declaration.getDistrict());
        System.out.println("Address: " + declaration.getAddress());
        System.out.println("Phone: " + declaration.getPhone());

        // Validate required fields - SỬA THÀNH TỪNG FIELD RIÊNG
        boolean hasError = false;
        StringBuilder errors = new StringBuilder();

        if (declaration.getFullName() == null || declaration.getFullName().trim().isEmpty()) {
            errors.append("Họ tên là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getYearOfBirth() == null) {
            errors.append("Năm sinh là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getGender() == null || declaration.getGender().trim().isEmpty()) {
            errors.append("Giới tính là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getPassportNumber() == null || declaration.getPassportNumber().trim().isEmpty()) {
            errors.append("Số CMND/Hộ chiếu là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getTransportType() == null || declaration.getTransportType().trim().isEmpty()) {
            errors.append("Thông tin đi lại là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getDepartureDate() == null || declaration.getDepartureDate().trim().isEmpty()) {
            errors.append("Ngày khởi hành là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getVisitedCities() == null || declaration.getVisitedCities().trim().isEmpty()) {
            errors.append("Thông tin thành phố đã đến là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getProvince() == null || declaration.getProvince().trim().isEmpty()) {
            errors.append("Tỉnh/thành là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getDistrict() == null || declaration.getDistrict().trim().isEmpty()) {
            errors.append("Quận/huyện là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getAddress() == null || declaration.getAddress().trim().isEmpty()) {
            errors.append("Địa chỉ là bắt buộc. ");
            hasError = true;
        }
        if (declaration.getPhone() == null || declaration.getPhone().trim().isEmpty()) {
            errors.append("Điện thoại là bắt buộc. ");
            hasError = true;
        }

        if (hasError) {
            System.out.println("=== DEBUG: VALIDATION ERRORS ===");
            System.out.println("Errors: " + errors.toString());
            model.addAttribute("error", errors.toString());
            model.addAttribute("declaration", declaration);
            return "form";
        }

        System.out.println("=== DEBUG: SAVING DECLARATION ===");
        declarationService.save(declaration);
        return "redirect:/confirmation";
    }

    // PHƯƠNG THỨC DỰ PHÒNG - DÙNG RequestParam nếu @ModelAttribute vẫn lỗi
    @PostMapping("/submit-simple")
    public String submitDeclarationSimple(
            @RequestParam String fullName,
            @RequestParam Integer yearOfBirth,
            @RequestParam String gender,
            @RequestParam String passportNumber,
            @RequestParam String transportType,
            @RequestParam String transportNumber,
            @RequestParam String departureDate,
            @RequestParam String visitedCities,
            @RequestParam String province,
            @RequestParam String district,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam(required = false) Boolean fever,
            @RequestParam(required = false) Boolean cough,
            @RequestParam(required = false) Boolean shortnessOfBreath,
            @RequestParam(required = false) Boolean soreThroat,
            @RequestParam(required = false) Boolean visitedAnimalFarm,
            @RequestParam(required = false) Boolean contactedCovidPatient,
            Model model) {

        HealthDeclaration declaration = new HealthDeclaration();
        declaration.setFullName(fullName);
        declaration.setYearOfBirth(yearOfBirth);
        declaration.setGender(gender);
        declaration.setPassportNumber(passportNumber);
        declaration.setTransportType(transportType);
        declaration.setTransportNumber(transportNumber);
        declaration.setDepartureDate(departureDate);
        declaration.setVisitedCities(visitedCities);
        declaration.setProvince(province);
        declaration.setDistrict(district);
        declaration.setAddress(address);
        declaration.setPhone(phone);
        declaration.setFever(fever != null ? fever : false);
        declaration.setCough(cough != null ? cough : false);
        declaration.setShortnessOfBreath(shortnessOfBreath != null ? shortnessOfBreath : false);
        declaration.setSoreThroat(soreThroat != null ? soreThroat : false);
        declaration.setVisitedAnimalFarm(visitedAnimalFarm != null ? visitedAnimalFarm : false);
        declaration.setContactedCovidPatient(contactedCovidPatient != null ? contactedCovidPatient : false);

        declarationService.save(declaration);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmation(Model model) {
        HealthDeclaration declaration = declarationService.getLatest();
        model.addAttribute("declaration", declaration);
        return "confirmation";
    }

    @GetMapping("/edit")
    public String editDeclaration(Model model) {
        HealthDeclaration declaration = declarationService.getLatest();
        if (declaration == null) {
            declaration = new HealthDeclaration();
        }
        model.addAttribute("declaration", declaration);
        return "form";
    }
}