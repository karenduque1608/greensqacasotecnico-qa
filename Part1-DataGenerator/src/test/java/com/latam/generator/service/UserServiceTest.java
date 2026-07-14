package com.latam.generator.service;

import com.latam.generator.model.User;
import com.latam.generator.model.UserType;
import com.latam.generator.repository.InMemoryUserRepository;
import com.latam.generator.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private static final int QUANTITY = 50;

    @Test
    void generateUsersProducesTheRequestedAmountRespectingAllBusinessRules() {

        UserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);

        List<User> users = userService.generateUsers(QUANTITY);

        assertEquals(QUANTITY, users.size());
        assertEquals(QUANTITY, repository.findAll().size());

        assertBusinessRulesAreRespected(users);
    }

    @Test
    void generateUsersParallelProducesTheRequestedAmountRespectingAllBusinessRules() {

        UserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);

        List<User> users = userService.generateUsersParallel(QUANTITY, 4);

        assertEquals(QUANTITY, users.size());
        assertEquals(QUANTITY, repository.findAll().size());

        assertBusinessRulesAreRespected(users);
    }

    private void assertBusinessRulesAreRespected(List<User> users) {

        long uniqueDocuments = users.stream().map(User::getDocument).distinct().count();
        long uniqueFullNames = users.stream()
                .map(user -> user.getName() + " " + user.getLastName())
                .distinct()
                .count();

        assertEquals(users.size(), uniqueDocuments, "Documents must not be duplicated");
        assertEquals(users.size(), uniqueFullNames, "Name + last name combination must not be duplicated");

        assertTrue(users.stream().allMatch(user -> user.getAge() > 10 && user.getAge() < 80));

        assertTrue(users.stream()
                .filter(user -> user.getType() == UserType.COMPANY)
                .allMatch(user -> user.getLastName().isEmpty()));

        assertTrue(users.stream()
                .filter(user -> !user.getCountry().equalsIgnoreCase("Colombia"))
                .noneMatch(user -> user.getLanguage().equalsIgnoreCase("Español")));

        List<String> companyDocuments = users.stream()
                .filter(user -> user.getType() == UserType.COMPANY)
                .map(User::getDocument)
                .collect(Collectors.toList());

        assertTrue(companyDocuments.stream().allMatch(document -> document.startsWith("9")));
    }

}
