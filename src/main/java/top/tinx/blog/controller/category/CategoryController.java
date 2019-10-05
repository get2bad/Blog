package top.tinx.blog.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tinx.blog.bean.Category;
import top.tinx.blog.bean.JsonData;
import top.tinx.blog.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("getAll")
    @ResponseBody
    public JsonData getAllCategory(){
        try{
            List<Category> all = categoryService.getAllCategorys();
            return JsonData.buildSuccess(all,1);
        }catch (Exception ex){
            ex.printStackTrace();
            return JsonData.buildError("遇到一些问题，已经通知管理员处理",-1);
        }
    }

    @PostMapping("deleteCategoryById")
    public String deleteCategoryById(String id){
        categoryService.deleteCategoryById(id);
        return "redirect:/pageManage";
    }

    @PostMapping("addCategory")
    public String addCategory(Category category){
        categoryService.insert(category);
        return "redirect:/pageManage";
    }
}
