package com.backend.registration;

import com.backend.appuser.AppUserRole;
import com.backend.dao.AppUserDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class TestAppUserDAO {

    @Autowired
    private AppUserDAO appUserDAO;
    private AppUserDTO appUserDTO = new AppUserDTO("Asd", "Dsa", "asd@mail.com",
            "asd", AppUserRole.USER, false);


}
