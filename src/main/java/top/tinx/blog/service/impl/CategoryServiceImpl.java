package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Category;
import top.tinx.blog.maaper.CategoryMapper;
import top.tinx.blog.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(int id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategorys() {
        return categoryMapper.getAllCategorys();
    }

    @Override
    public void deleteCategoryById(String id) {
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    public void insert(Category category) {
        categoryMapper.insertCategory(category);
    }

    @Override
    public void updateById(Category category) {
        categoryMapper.updateCategoryById(category);
    }
}
