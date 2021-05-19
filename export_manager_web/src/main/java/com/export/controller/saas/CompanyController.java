package com.export.controller.saas;

import com.export.domain.Company;
import com.export.service.CompanyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        PageInfo<Company> all = companyService.findAll(page,size);
        model.addAttribute("pageInfo", all);
        return "company/company-list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping("edit")
    public String edit(Company company){
        // 判断如果传入的ID为空则新增
        if(StringUtils.isEmpty(company.getId())){
            company.setId(UUID.randomUUID().toString());
            companyService.save(company);
        }else{// 如果传入的ID不为空则是修改
            companyService.update(company);
        }
        return "redirect:/company/list.do";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String id, Model model){
        Company company = companyService.getById(id);
        model.addAttribute("company", company);
        return "company/company-update";
    }

    @RequestMapping("delete")
    public String delete(String id){

        String[] ids = id.split(",");
        companyService.delete(ids);
        return "redirect:/company/list.do";
    }
}
