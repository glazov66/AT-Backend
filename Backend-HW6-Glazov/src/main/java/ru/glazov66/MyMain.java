package ru.glazov66;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import ru.glazov66.db.dao.CategoriesMapper;
import ru.glazov66.db.model.CategoriesExample;

import java.io.IOException;
@Slf4j
public class MyMain {

    static Faker faker = new Faker();
    private static String resource = "mybatisConfig.xml";

    public static void main(String[] args) throws IOException {

        SqlSession sqlSession = getSqlSession();
        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);

        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        log.info("categoriesCount = " + categoriesCount);

    }

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
}
