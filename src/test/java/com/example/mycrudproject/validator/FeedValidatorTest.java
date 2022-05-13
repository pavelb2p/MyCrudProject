package com.example.mycrudproject.validator;

import com.example.mycrudproject.entity.Feed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
class FeedValidatorTest {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.usingContext().getValidator();

    private void checkFeedTest(Feed feed) {
        Set<ConstraintViolation<Feed>> validates = validator.validate(feed);
        Assertions.assertTrue(validates.size() > 0);
        validates.stream().map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
    }

    @Test
    void feedDisplayNameShouldBeNotEmptyTest() {
        checkFeedTest(new Feed("id22",
                "",
                "https://www.themeatballshop.com",
                "flag",
                "sourceDisplayName",
                "sourceSiteUrl",
                "description"));
    }

    @Test
    void feedDisplayNameShouldBeGreaterThanThreeCharactersTest() {
        checkFeedTest(new Feed("id22",
                "aa",
                "https://www.themeatballshop.com",
                "flag",
                "sourceDisplayName",
                "sourceSiteUrl",
                "description"));
    }

    @Test
    void feedImagesShouldBeNotEmptyTest() {
        checkFeedTest(new Feed("id22",
                "DisplayName",
                "",
                "flag",
                "sourceDisplayName",
                "sourceSiteUrl",
                "description"));
    }

    @Test
    void feedImagesShouldBeURLTest() {
        checkFeedTest(new Feed("id22",
                "DisplayName",
                "it is not valid URL",
                "flag",
                "sourceDisplayName",
                "sourceSiteUrl",
                "description"));
    }

    @Test
    void feedSourceDisplayNameShouldBeNotEmptyTest() {
        checkFeedTest(new Feed("id22",
                "DisplayName",
                "https://www.themeatballshop.com",
                "flag",
                "",
                "sourceSiteUrl",
                "description"));
    }

    @Test
    void feedSourceSiteUrlShouldBeNotEmptyTest() {
        checkFeedTest(new Feed("id22",
                "DisplayName",
                "https://www.themeatballshop.com",
                "flag",
                "sourceDisplayName",
                "",
                "description"));
    }

    @Test
    void feedDescriptionShouldBeNotEmptyTest() {
        checkFeedTest(new Feed("id22",
                "DisplayName",
                "https://www.themeatballshop.com",
                "flag",
                "sourceDisplayName",
                "sourceSiteUrl",
                ""));
    }
}
