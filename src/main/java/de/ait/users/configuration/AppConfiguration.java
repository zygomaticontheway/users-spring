package de.ait.users.configuration;

import de.ait.users.repository.IUserRepository;
import de.ait.users.repository.UserRepositoryImp;
import de.ait.users.repository.UserRepositoryJDBCImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    private ConfigurableApplicationContext context;

    @Bean
    public IUserRepository getRepository() {
        if (repositoryType.equalsIgnoreCase("list")) {
            return context.getBean(UserRepositoryImp.class);
        } else {
            return context.getBean(UserRepositoryJDBCImpl.class);
        }
    }

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
