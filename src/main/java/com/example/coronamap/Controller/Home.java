package com.example.coronamap.Controller;

import com.example.coronamap.Model.Header;
import com.example.coronamap.service.CoronaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@Controller
public class Home {
    @Autowired
    CoronaData coronaData;

    @Autowired
    HttpServletRequest request;

    @GetMapping ("/")
    public String home(Model model){
        List<Header> allData = CoronaData.list;
        long totals = allData.stream().mapToLong(Header::getTotalcaseLatest).sum();
        model.addAttribute("list", allData);
        model.addAttribute("totals", totals);
        return "home";
    }
       @GetMapping("/find")
        public String find(Model model){
        String ct = request.getParameter("country");
           List<Header> fData = CoronaData.find_data(ct);
           long totals = fData.stream().mapToLong(Header::getTotalcaseLatest).sum();
           model.addAttribute("list", fData);
           model.addAttribute("totals", totals);
            return "home";
        }
}
