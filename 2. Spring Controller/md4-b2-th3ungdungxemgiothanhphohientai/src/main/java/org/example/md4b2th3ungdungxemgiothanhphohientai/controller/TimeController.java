package org.example.md4b2th3ungdungxemgiothanhphohientai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/world-clock")  // là ánh xạ request lên phương thức xử lý.
    public String getTimeByTimezone(ModelMap model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) { // khai báo tham số của phương thức xử lý, ràng buộc với tham số từ request.

        /*Bước 3: Xử lý để lấy thời gian hiện tại của một thành phố trên thế giới
        - Xử lý để lấy timezone của thành phố hiện tại và thành phố cần xác định thời gian thông qua abstract class TimeZone.*/

        // Lấy ra thời gian hiện tại (thời gian của server)
        Date date = new Date();
        // Lấy ra time zone mặc định của server
        TimeZone localTimeZone = TimeZone.getDefault();
        // Lấy ra time zone của thành phố được chọn
        TimeZone targetTimeZone = TimeZone.getTimeZone(city);
        // Tính toán sự chênh lệch thời gian giữa múi giờ của server và múi giờ được chọn
        long timeDifference = targetTimeZone.getRawOffset() - localTimeZone.getRawOffset();
        // Áp dụng sự chênh lệch vào thời gian hiện tại của server
        long targetTimeInMilliSeconds = date.getTime() + timeDifference;
        // Cài đặt lại thời gian cho biến date thành thời gian hiện tại của thành phố được chọn
        date.setTime(targetTimeInMilliSeconds);
        // Bước 4: Chuyển dữ liệu về view
        // Thêm tên thành phố và thời gian đã tính vào ModelMap để gửi tới View
        model.addAttribute("city", city);
        model.addAttribute("date", date);

        return "index";

    }

}