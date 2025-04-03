package br.com.edilsonmaria.medicalconsult.user.services;

import br.com.edilsonmaria.medicalconsult.user.domain.User;
import br.com.edilsonmaria.medicalconsult.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void cadastrarUser(){
        User user = new User();
        user.setNomeUser("Edilson");
        //configuração de componente em Mock
        when(userRepository.save(any(User.class))).thenReturn(user);

        //a execução do metodo a ser testado
        var resulto = userService.cadastrarUser(user);

        //validação do teste
        assertNotNull(user);
        assertEquals("Edilson",resulto.getNomeUser());

        verify(userRepository, times(1)).save(user);

    }

    @Test
    void listarUser(){
        //criando o objeto users
        User user1 = new User();
        user1.setNomeUser("Edilson");

        User user2 = new User();
        user2.setNomeUser("Nayara");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //configuração de componente em Mock
        when(userRepository.findAll()).thenReturn(users);

        //a execução do metodo a ser testado
        var resultado = userService.listarUsers();

        //validação do teste
        //assertNotNull(resultado);
        //assertEquals(2,resultado.size());
        //assertEquals("Edilson", resultado.get(0).getNomeUser());
        //assertEquals("Nayara", resultado.get(1).getNomeUser());
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2,resultado.size()),
                () -> assertEquals("Edilson", resultado.get(0).getNomeUser()),
                () -> assertEquals("Nayara", resultado.get(1).getNomeUser())
        );
    }

    @Test
    void buscarUser() {
        //criando o objeto users
        User user1 = new User();
        user1.setNomeUser("Edilson");
        User user2 = new User();
        user2.setNomeUser("Nayara");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //configuração de componente em Mock
        when(userRepository.findAll()).thenReturn(users);

        //a execução do metodo a ser testado
        var resultado = userService.listarUsers();

        //validação do teste
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Edilson", resultado.get(0).getNomeUser()),
                () -> assertEquals("Nayara", resultado.get(1).getNomeUser())
        );
    }

    @Test
    void atualizarUser() {
        // Dados do usuário existente e do usuário atualizado
        User userExistente = new User();
        userExistente.setNomeUser("Edilson");
        userExistente.setEmail("edilson@gmail.com");

        User userAtualizado = new User();
        userAtualizado.setNomeUser("Edilson Maria");
        userAtualizado.setEmail("edilsonmaria@gmail.com");

        // Configuração do mock
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(userExistente));
        when(userRepository.save(any(User.class))).thenReturn(userExistente);

        // Execução do método
        var resultado = userService.atualizarUser(1L, userAtualizado);

        // Validação do teste
        assertNotNull(resultado);
        assertEquals("Edilson Maria", resultado.getNomeUser());
        assertEquals("edilsonmaria@gmail.com", resultado.getEmail());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(userExistente);
    }

    @Test
    void deletarUser() {
        // Dados do usuário a ser deletado
        User user = new User();
        user.setNomeUser("Edilson");

        // Configuração do mock
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Execução do método
        userService.deletarUser(1L);

        // Validação do teste
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }
}