package top.tinx.blog.service;

import top.tinx.blog.bean.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(int id);

    List<Category> getAllCategorys();

    void deleteCategoryById(String id);

    void insert(Category category);

    void updateById(Category category);
}
