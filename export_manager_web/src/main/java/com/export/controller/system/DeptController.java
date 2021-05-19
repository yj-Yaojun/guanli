package com.export.controller.system;

import com.alibaba.druid.util.StringUtils;
import com.export.domain.system.Dept;
import com.export.service.system.DeptService;
import com.github.pagehelper.PageInfo;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.POST;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        String companyId = "1";
        PageInfo<Dept> pageInfo = deptService.findByCompanyId(companyId, page, size);
        model.addAttribute("pageInfo", pageInfo);
        return "/system/dept/dept-list";
    }
    @RequestMapping("toAdd")
    public String toAdd(Model model){
        List<Dept> list = deptService.findDeptName();
        model.addAttribute("deptList",list);
        return "/system/dept/dept-add";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(String id,Model model){
        //根据parent_Id 查dept_Id对应的dept_Name
        Dept  dept = deptService.findById(id);
        model.addAttribute("dept",dept);
        return "/system/dept/dept-update";
    }
    @RequestMapping("edit")
    public String edit(Dept dept){
        //判断是否有ID
        if (StringUtils.isEmpty(dept.getId())){
            dept.setId(UUID.randomUUID().toString());
            dept.setCompanyId("1");
            dept.setCompanyName("传智播客教育股份有限公司");
            deptService.add(dept);
        }else{
            deptService.update(dept);
        }
        return "redirect:/system/dept/dept-list";
    }

    @RequestMapping("delete")
    public String delete(String id){
        deptService.delete(id);
        return "redirect:../system/dept/dept-list";

    }
}
