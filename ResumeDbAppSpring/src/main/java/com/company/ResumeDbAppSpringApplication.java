package com.company;

import com.company.dao.inter.UserRepository;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppSpringApplication {
//    @Autowired//userdainter den hansi class impl edibse onu tapib getirir
//    @Qualifier("userdao1")//burda claslar arasinda konflikt yaranmasin deye ad veririk,
//    private UserDaoInter userDao;//bu interfaceden gedir sohbet


    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppSpringApplication.class, args);
    }
@Autowired
private UserRepository userRepo;
    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
            List<User>list= userRepo.getAll(null,null,null);
                System.out.println(list);



            }
        };
        return clr;
    }

}
