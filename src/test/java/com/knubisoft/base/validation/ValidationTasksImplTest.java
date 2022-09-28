package com.knubisoft.base.validation;

import com.knubisoft.base.validation.ValidationTasks.User;
import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationTasksImplTest {
    ValidationTasksImpl validation = new ValidationTasksImpl();

    @Test
    @SneakyThrows
    void validate() {
        User user = new User();
        user.setId(0L);
        user.setName("MyName");
        user.setSurname("MySurName");
        user.setMarried(false);
        user.setFkUserGeneralDetails(10L);

        User user1 = new User();
        user1.setId(1L);
        user1.setName("LengthMoreThanSeven");
        user1.setSurname("LengthMoreThanTen");
        user1.setFkUserGeneralDetails(100L);

        User user2 = new User();
        user2.setId(0L);

        User user3 = new User();
        user3.setId(0L);
        user3.setName("");
        user3.setSurname("");
        user3.setMarried(false);
        user3.setFkUserGeneralDetails(10L);

        validation.validate(user);
        assertThrows(ExceptionHandler.class, () -> validation.validate(user1));
        assertThrows(ExceptionHandler.class, () -> validation.validate(user2));
        assertThrows(ExceptionHandler.class, () -> validation.validate(user3));
    }

    @Test
    void buildUser() {
    }

    @Test
    void buildUserGeneralDetails() {
    }

    @Test
    void buildUserAddressDetails() {
    }
}