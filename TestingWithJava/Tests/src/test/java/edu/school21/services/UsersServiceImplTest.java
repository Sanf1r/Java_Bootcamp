package edu.school21.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import javax.persistence.EntityNotFoundException;

import edu.school21.models.*;
import edu.school21.exceptions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import edu.school21.repositories.UsersRepository;

public class UsersServiceImplTest {

    private static UsersRepository mockUsersRepository = mock(UsersRepository.class);
    private static UsersServiceImpl usersServiceImpl = new UsersServiceImpl(mockUsersRepository);
    private static User returnAuth = new User(1L, "Dante", "pass", true);
    private static User returnPass = new User(1L, "V", "pass", false);
    private static User returnCorrectLogin = new User(1L, "Virgil", "pass", false);
    private static User updateCorrectLogin = new User(1L, "Virgil", "pass", true);

    @BeforeAll
    static void rules() {
        when(mockUsersRepository.findByLogin("Dante")).thenReturn(returnAuth);
        when(mockUsersRepository.findByLogin("Nero")).thenThrow(new EntityNotFoundException("Error"));
        when(mockUsersRepository.findByLogin("V")).thenReturn(returnPass);
        when(mockUsersRepository.findByLogin("Virgil")).thenReturn(returnCorrectLogin);
    }

    @Test
    public void alreadyAuth() {
        assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceImpl.authenticate("Dante", "test"));
    }

    @Test
    public void wrongLogin() {
        assertThrows(EntityNotFoundException.class, () -> usersServiceImpl.authenticate("Nero", "pass"));
    }

    @Test
    public void wrongPass() {
        assertEquals(usersServiceImpl.authenticate("V", "WRONGpass"), false);
    }

    @Test
    public void CorrectLoginPass() {
        assertEquals(usersServiceImpl.authenticate("Virgil", "pass"), true);
        verify(mockUsersRepository).update(updateCorrectLogin);
    }
}
