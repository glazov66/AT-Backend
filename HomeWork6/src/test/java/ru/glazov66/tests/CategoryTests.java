package ru.glazov66.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import retrofit2.Response;
import ru.glazov66.dto.Category;
import ru.glazov66.enums.CategoryType;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import ru.glazov66.service.CategoryService;
import ru.glazov66.utils.RetrofitUtils;

@Slf4j

public class CategoryTests {

    static Retrofit client;
    static CategoryService categoryService;
    private final int INCORRECT_CATEGORY_ID = (int) ((Math.random() + 10) * 100);
    private final int NEGATIVE_CATEGORY_ID = -3;
    private final int DOUBLE_CATEGORY_ID = (int) (12.75);
    private final int ZERO_CATEGORY_ID = 0;
    private final int BIG_CATEGORY_ID = 1000000000;

    @BeforeAll
    static void beforeAll() {
        client = RetrofitUtils.getRetrofit();
        categoryService = client.create(CategoryService.class);
    }

    @DisplayName("Получить категорию по валидному ID")
    @Test
    void getCategoryByIdTest() throws IOException {
        Integer id = CategoryType.FOOD.getId();
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();

        System.out.println(response.body().getProducts().get(1));
        log.info(response.body().toString());
        System.out.println("Category title: " + response.body().getTitle());
        System.out.println("Category 'type FOOD' title: " + CategoryType.FOOD.getTitle());
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getId(), equalTo(id));

        log.info(String.valueOf(response.body().getProducts().get(3)));
    }

    @DisplayName("Получить категорию по  несуществующему ID")
    @Test
    void getCategoryByNoexistIdTest() throws IOException {
        Integer id = INCORRECT_CATEGORY_ID;
        Response<Category> response = categoryService
                .getCategory(id)
                .execute();

        log.info("INCORRECT_CATEGORY_ID:" + INCORRECT_CATEGORY_ID);
        log.info("response.code:" + response.code());
        assertThat(response.code(), greaterThanOrEqualTo(400));

    }

    @DisplayName("Получить категорию по невалидному ID")
    @ParameterizedTest(name = "Тест №{index}: ID {index} {arguments}")
    @ValueSource(ints = {NEGATIVE_CATEGORY_ID, DOUBLE_CATEGORY_ID, ZERO_CATEGORY_ID, BIG_CATEGORY_ID})

    void getCategoryByIncorrectIdTest(int paramCategory) throws IOException {

        Response<Category> response = categoryService
                .getCategory(paramCategory)
                .execute();

        System.out.println(INCORRECT_CATEGORY_ID);
        System.out.println(response.code());
        log.info(response.toString());
//        log.info(response.body().toString());
        assertThat(response.code(), greaterThanOrEqualTo(400));

    }



}
