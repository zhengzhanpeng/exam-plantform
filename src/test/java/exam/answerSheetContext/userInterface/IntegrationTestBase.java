package exam.answerSheetContext.userInterface;

import exam.answerSheetContext.infrastructure.Repository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    private static final String UTF_8 = "UTF-8";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private List<Repository> repositories;

    @BeforeEach
    public void initApplication() {
        repositories.forEach(repository -> {
            repository.findAll().clear();
        });
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc
                .given()
                .header("Accept", ContentType.JSON.withCharset(UTF_8))
                .header("Content-Type", ContentType.JSON.withCharset(UTF_8));
    }
}
