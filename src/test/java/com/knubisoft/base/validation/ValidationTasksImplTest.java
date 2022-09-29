package com.knubisoft.base.validation;

import com.knubisoft.base.validation.ValidationTasks.*;
import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("MyName" ,validation.buildUser().getName());
        assertEquals("MySurName" ,validation.buildUser().getSurname());
        assertEquals(0 ,validation.buildUser().getId());
        assertEquals(10 ,validation.buildUser().getFkUserGeneralDetails());
        assertEquals(1 ,validation.buildUser().getCountOfPets());
        assertEquals(3 ,validation.buildUser().getCountOfChildren());
        assertFalse(validation.buildUser().isMarried());
    }

    @Test
    @SneakyThrows
    void buildUserGeneralDetails() {
        UserGeneralDetails userGeneralDetails = new UserGeneralDetails();
        userGeneralDetails.setId(0L);
        userGeneralDetails.setPreviousProfession("developer");
        userGeneralDetails.setCountry("Ukraine");
        userGeneralDetails.setOblast("Kharkiv");
        userGeneralDetails.setCity("Kupiansk");
        userGeneralDetails.setFkUserAddressDetails(10L);

        UserGeneralDetails userGeneralWrongDetails = new UserGeneralDetails();
        userGeneralDetails.setPreviousProfession("");
        userGeneralDetails.setFkUserAddressDetails(10L);

        UserGeneralDetails userGeneralWrongDetails2 = new UserGeneralDetails();
        userGeneralWrongDetails2.setPreviousProfession("maxlengthfourteen");
        userGeneralWrongDetails2.setCountry("countrywithmorethanfifteen");

        validation.validate(userGeneralDetails);
        assertThrows(ExceptionHandler.class, () -> validation.validate(userGeneralWrongDetails));
        assertThrows(ExceptionHandler.class, () -> validation.validate(userGeneralWrongDetails2));
    }

    @Test
    @SneakyThrows
    void buildUserAddressDetails() {
        UserAddressDetails userAddressDetails = new UserAddressDetails();
        userAddressDetails.setId(0L);
        userAddressDetails.setZipCode("Wits");
        userAddressDetails.setStreet("Backer Street 221B");
        userAddressDetails.setNumberOfHouse("10");

        validation.validate(userAddressDetails);

        UserAddressDetails userAddressWrongDetails = new UserAddressDetails();
        userAddressDetails.setZipCode("lengthmorethansix");

        UserAddressDetails userAddressWrongDetails2 = new UserAddressDetails();

        assertThrows(ExceptionHandler.class, () -> validation.validate(userAddressWrongDetails));
        assertThrows(ExceptionHandler.class, () -> validation.validate(userAddressWrongDetails2));

    }
}