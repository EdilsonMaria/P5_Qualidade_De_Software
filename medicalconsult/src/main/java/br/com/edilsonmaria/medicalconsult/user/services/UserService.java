package br.com.edilsonmaria.medicalconsult.user.services;

import br.com.edilsonmaria.medicalconsult.user.domain.User;
import br.com.edilsonmaria.medicalconsult.user.repository.UserRepository;
import com.sun.jdi.ObjectCollectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User cadastrarUser(User user) {
        return userRepository.save(user);
    }

    public List<User> listarUsers() {
        return userRepository.findAll();
    }

    public User buscarUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                () -> new ObjectCollectedException("Usuario não encontrado:"));
    }

    public User atualizarUser(Long id, User userAtualizado) {
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new ObjectCollectedException("Usuário não encontrado"));

        // Atualizar os campos do usuário existente
        userExistente.setNomeUser(userAtualizado.getNomeUser());
        userExistente.setEmail(userAtualizado.getEmail());
        userExistente.setCpf(userAtualizado.getCpf());
        userExistente.setTelefone(userAtualizado.getTelefone());
        userExistente.setDataNascimento(userAtualizado.getDataNascimento());
        userExistente.setPermissao(userAtualizado.getPermissao());

        return userRepository.save(userExistente);
    }

    public void deletarUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectCollectedException("Usuário não encontrado"));
        userRepository.delete(user);
    }
}
