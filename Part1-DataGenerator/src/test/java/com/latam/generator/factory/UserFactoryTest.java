package com.latam.generator.factory;

import com.latam.generator.model.Company;
import com.latam.generator.model.Person;
import com.latam.generator.model.User;
import com.latam.generator.model.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class UserFactoryTest {

    private final UserFactory factory = new UserFactory();

    @Test
    void createsACompanyWithBlankLastName() {
        User user = factory.createUser(
                true, "Acme", "should-be-ignored", 30, "9123456789", "Bogota", "Colombia", "Español"
        );

        assertInstanceOf(Company.class, user);
        assertEquals(UserType.COMPANY, user.getType());
        assertEquals("", user.getLastName());
    }

    @Test
    void createsAPersonWithTheGivenLastName() {
        User user = factory.createUser(
                false, "Ana", "Gomez", 30, "123456789", "Bogota", "Colombia", "Español"
        );

        assertInstanceOf(Person.class, user);
        assertEquals(UserType.PERSON, user.getType());
        assertEquals("Gomez", user.getLastName());
    }

}
