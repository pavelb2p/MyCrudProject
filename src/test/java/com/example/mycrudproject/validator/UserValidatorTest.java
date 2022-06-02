package com.example.mycrudproject.validator;

import com.example.mycrudproject.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
class UserValidatorTest {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.usingContext().getValidator();

    private void checkUserTest(User user) {
        Set<ConstraintViolation<User>> validates = validator.validate(user);
        Assertions.assertTrue(validates.size() > 0);
        validates.stream().map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
    }

    @Test
    void userNameShouldBeGreaterThanTwoCharactersTest() {
        checkUserTest(new User(11L, "a", 22));
    }

    @Test
    void userNameShouldBeLessThanFifteenCharactersTest() {
        checkUserTest(new User(11L, "less than fifteen letters", 22));
    }

    @Test
    void userNameShouldBeNotEmptyTest() {
        checkUserTest(new User(11L, "", 22));
    }

    @Test
    void userAgeShouldBeEqualsOrGreaterThanZeroTest() {
        checkUserTest(new User(11L, "Elza", -22));
    }
}
