package com.ba.controller;

import com.ba.dto.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InfoController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.h2.console.enabled}")
    private String consoleEnabled;

//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddlAuto;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${logging.level.org.hibernate.type}")
    private String hybernateType;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${app.message}")
    private String message;

    @Value("${spring.profiles.active}")
    private String profileName;

    @GetMapping("infolist")
    public List<Info> getInfo(){
        List<Info> infoList = new ArrayList<>();

        Info infoMessage = new Info("Database Name",message);
        infoList.add(infoMessage);

        Info infoServerPort = new Info("server.port",serverPort);
        infoList.add(infoServerPort);

        Info infoProfileName = new Info("spring.profiles.active",profileName);
        infoList.add(infoProfileName);

        Info infoDataSourceUrl = new Info("spring.datasource.url",dataSourceUrl);
        infoList.add(infoDataSourceUrl);

//        Info infoDdlAuto = new Info("spring.jpa.hibernate.ddl-auto",ddlAuto);
//        infoList.add(infoDdlAuto);

        Info infoUserName = new Info("spring.datasource.username",username);
        infoList.add(infoUserName);

        Info infoPassword = new Info("spring.datasource.password",password);
        infoList.add(infoPassword);

        Info infoDriverClassName = new Info("spring.datasource.driverClassName",driverClassName);
        infoList.add(infoDriverClassName);

        Info infoShowSql = new Info("spring.java.show-sql",showSql);
        infoList.add(infoShowSql);

        Info infoFormatSql = new Info("spring.jpa.properties.hibernate.format_sql",formatSql);
        infoList.add(infoFormatSql);

        Info infoHybernateType = new Info("logging.level.org.hibernate.type",hybernateType);
        infoList.add(infoHybernateType);

        Info infoConsoleEnabled = new Info("spring.h2.console.enabled",consoleEnabled);
        infoList.add(infoConsoleEnabled);

        return infoList;
    }

}
