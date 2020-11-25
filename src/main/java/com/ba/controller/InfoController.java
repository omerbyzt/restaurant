package com.ba.controller;

import com.ba.entity.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InfoController {

    @Value("${server}")
    private String serverName;

    @Value("${spring.h2.console.enabled}")
    private String consoleEnabled;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.java.show-sql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${logging.level.org.hibernate.type}")
    private String hybernateType;

    @GetMapping("infolist")
    public List<Info> getInfo(){
        List<Info> infoList = new ArrayList<>();

        Info info1 = new Info("spring.h2.console.enabled",consoleEnabled);
        infoList.add(info1);

        Info info2 = new Info("spring.jpa.hibernate.ddl-auto",ddlAuto);
        infoList.add(info2);

        Info info3 = new Info("spring.datasource.url",dataSourceUrl);
        infoList.add(info3);

        Info info4 = new Info("spring.java.show-sql",showSql);
        infoList.add(info4);

        Info info5 = new Info("spring.jpa.properties.hibernate.format_sql",formatSql);
        infoList.add(info5);

        Info info6 = new Info("logging.level.org.hibernate.type",hybernateType);
        infoList.add(info6);

        return infoList;
    }

}
