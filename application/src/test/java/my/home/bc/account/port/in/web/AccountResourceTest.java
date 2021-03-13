package my.home.bc.account.port.in.web;

import static io.restassured.RestAssured.given;
import static my.home.bc.account.assertion.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import broker.Broker;
import broker.BrokerReceiver;
import ddd.IdGenerator;
import io.restassured.RestAssured;
import my.home.bc.account.model.Account;
import my.home.bc.account.port.in.web.model.AccountDto;
import my.home.bc.account.port.in.web.model.NewAccountDto;
import my.home.bc.account.repository.AccountRepository;
import my.home.infrastructure.web.AbstractIntegrationTest;
import my.home.infrastructure.web.WithToken;
import my.home.infrastructure.web.exception.ErrorResponse;
import my.home.infrastructure.web.exception.ErrorResponseAssert;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

public class AccountResourceTest extends AbstractIntegrationTest {

    private static final String EMAIL = "john.doe@gmail.com";
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    private static final String EXTERNAL_ID = "auth0|123123123";

    @LocalServerPort
    private int port;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Broker broker;

    @MockBean
    private BrokerReceiver fakeReceiver;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api";
        //    RestAssured.filters(
        //      new RequestLoggingFilter(),
        //      new ResponseLoggingFilter()
        //    );

        baseUrl = "http://localhost:" + port;
    }

    @Nested
    @DisplayName("POST /accounts")
    class RegisterRoute {

        @Test
        @WithToken(email = EMAIL, sub = EXTERNAL_ID)
        void register_account_returns_id_of_registered_account() {
            AccountDto dto = given()
                .contentType("application/json")
                .body(NewAccountDto.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build())
                .when()
                .post("/accounts")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", startsWith(baseUrl + "/api/accounts/"))
                .extract()
                .as(AccountDto.class);

            assertThat(dto).hasExternalId(EXTERNAL_ID).hasEmail(EMAIL).hasFirstName(FIRST_NAME).hasLastName(LAST_NAME);
        }

        @Test
        @WithToken(email = EMAIL)
        void rejects_invalid_input() {
            ErrorResponse error = given()
                .contentType("application/json")
                .body(NewAccountDto.builder().build())
                .when()
                .post("/accounts")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse.class);

            new ErrorResponseAssert(error)
                .hasStatus(HttpStatus.BAD_REQUEST.value())
                .hasMessage("Bad command")
                .hasErrors("firstname must not be null", "lastname must not be null");
        }

        @Test
        @WithToken(email = EMAIL)
        void rejects_when_account_already_exists() {
            accountRepository.add(new Account("account#id", "eid", EMAIL, FIRST_NAME, LAST_NAME));

            ErrorResponse error = given()
                .contentType("application/json")
                .body(NewAccountDto.builder().firstName("John").lastName("Doe").build())
                .when()
                .post("/accounts")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract()
                .as(ErrorResponse.class);

            new ErrorResponseAssert(error).hasStatus(HttpStatus.CONFLICT.value()).hasMessage("EXISTING_ACCOUNT");
        }
    }
}
