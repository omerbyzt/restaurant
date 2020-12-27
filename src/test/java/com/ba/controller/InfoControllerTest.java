package com.ba.controller;

import com.ba.dto.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {

    @InjectMocks
    private InfoController infoController;

    private List<Info> infoList = new ArrayList<>();
    private Info info = new Info();

    @Before
    public void setUp() throws Exception {
        info.setKey("testKey");
        info.setValue("testValue");
        infoList.add(info);
    }

    @Test
    public void shouldGetInfo() {
        List<Info> infoList = infoController.getInfo();
        assertNotNull(infoList);
    }
}