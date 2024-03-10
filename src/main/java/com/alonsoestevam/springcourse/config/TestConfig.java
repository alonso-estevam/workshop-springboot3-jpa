package com.alonsoestevam.springcourse.config;

import com.alonsoestevam.springcourse.entities.User;
import com.alonsoestevam.springcourse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // classe de configuração para popular o banco de dados
@Profile("test") // para rodar a configuração apenas quando estiver no perfil de teste
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // tudo dentro desse método vai ser executado quando a aplicação for iniciada
                        // id null porque o id será gerado pelo banco de dados
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }

}
