package ru.glazov66.utils;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.glazov66.db.dao.CategoriesMapper;
import ru.glazov66.db.dao.ProductsMapper;
import ru.glazov66.db.model.Categories;
import ru.glazov66.db.model.CategoriesExample;
import ru.glazov66.db.model.ProductsExample;

import java.io.IOException;
@UtilityClass
public class DbUtils {

    Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }

    @SneakyThrows
    public static CategoriesMapper getCategoriesMapper(){

        return getSqlSession().getMapper(CategoriesMapper.class);
    }
    @SneakyThrows
    public static ProductsMapper getProductsMapper() {

        return getSqlSession().getMapper(ProductsMapper.class);
    }
    private static void createNewCategory(CategoriesMapper categoriesMapper) {
        Categories newCategory = new Categories();
        newCategory.setTitle(faker.animal().name());

        categoriesMapper.insert(newCategory);
    }

    public static Integer countCategories(CategoriesMapper categoriesMapper) {
        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        return Math.toIntExact(categoriesCount);
    }

    public static Integer countProducts(ProductsMapper productsMapper) {
        long productsCount = productsMapper.countByExample(new ProductsExample());
        return Math.toIntExact(productsCount);
    }


}
