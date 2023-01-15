package gr.hua.ds.club_registry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ClubRegistryApplication {

    public static void main( String[] args ) {
        SpringApplication.run(ClubRegistryApplication.class , args);
    }


}
