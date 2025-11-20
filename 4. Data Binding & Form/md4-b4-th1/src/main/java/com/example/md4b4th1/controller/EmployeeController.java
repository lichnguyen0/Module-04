package com.example.md4b4th1.controller;

import com.example.md4b4th1.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  /*Đánh dấu class là Spring MVC Controller.*/
             /*Các phương thức trong class này sẽ xử lý HTTP request và trả về tên của view (ví dụ: "employee/create").
*/

@RequestMapping("/employee")  /*Định nghĩa một đường dẫn gốc cho tất cả các method bên trong controller này.*/
                              /*Ví dụ: /employee/show-form, /employee/add-employee.*/

public class EmployeeController {

    @GetMapping("/show-form")       /*Ánh xạ request HTTP GET đến /employee/show-form.*/
                                    /*Dùng để hiển thị form nhập thông tin nhân viên.*/
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @PostMapping("/add-employee")    /*Ánh xạ request HTTP POST đến /employee/add-employee.*/
                                     /*Dùng để nhận dữ liệu từ form và hiển thị kết quả.*/
    public String submit(@ModelAttribute("employee") Employee employee, Model model) {   /*Dùng để liên kết dữ liệu từ form HTML với object Employee (tự động binding các input name tương ứng).*/
                                                                                         // Gán object này vào Model dưới tên "employee".

                model.addAttribute("name", employee.getName());
        model.addAttribute("contactNumber", employee.getContactNumber());
        model.addAttribute("id", employee.getId());
        return "employee/info";
    }
}
