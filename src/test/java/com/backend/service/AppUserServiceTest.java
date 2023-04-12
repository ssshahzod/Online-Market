package com.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
import com.backend.appuser.seller.Seller;
import com.backend.bucket.Bucket;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserCredentialsDAO;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import com.backend.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ComponentScan("com.backend")
class AppUserServiceTest {

    @MockBean
    AppUserRepository appUserRepository;
    @MockBean
    SellerRepository sellerRepository;
    @MockBean
    BucketRepository bucketRepository;

    @MockBean
    AppUserCredentialsDAO appUserCredentialsDAO;

    @Autowired
    AppUserService appUserService;

    private AppUser appUser2;
    private Seller seller1;

    @BeforeEach
    public void setup(){
        appUser2 = new AppUser(
                11L,
                "Sel",
                "LEs",
                "sellerTest@mail.ru",
                "123paswd321",
                null,
                AppUserRole.SELLER.name(),
                new Bucket(),
                null
        );
        seller1 = new Seller(
                null,
                null,
                "image",
                "Test seller"
        );

        appUser2.setSeller(seller1);
        seller1.setAppUser(appUser2);
    }

    @Test
    void givenEmptyUsersList_whenGetByEmailOrId_throwException() {

        when(appUserRepository.getAppUserByEmail("test@mail.ru")).thenReturn(
                null
        );

        Exception notFoundByMail = assertThrows(UsernameNotFoundException.class, () ->{
            appUserService.get("test@mail.ru");
        });

        String expectedMsg = "There is no such user";
        assertTrue(notFoundByMail.getMessage().contains(expectedMsg));
    }

    @Test
    void givenUsers_whenGetByEmailOrId_returnUsers(){
        AppUserDTO dto2 = new AppUserDTO(appUser2);
        AppUser appUser1 = new AppUser(
                10L,
                "Alex",
                "V",
                "test@mail.ru",
                "123paswd321",
                null,
                AppUserRole.USER.name(),
                new Bucket(),
                null
        );

        when(appUserRepository.getAppUserByEmail("sellerTest@mail.ru")).thenReturn(appUser2);
        when(appUserCredentialsDAO.getById(11L)).thenReturn(dto2);
        when(appUserRepository.getReferenceById(10L)).thenReturn(appUser1);

        AppUser testUser = appUserService.getUserById(10L);
        AppUserDTO testUserDTO = appUserService.get("sellerTest@mail.ru");

        assertEquals(appUser1, testUser);
        assertEquals(dto2, testUserDTO);
    }

    @Test
    void givenAppUser_whenCreateSeller_makeCorrectSeller(){
        AppUser toSeller = new AppUser(
                11L,
                "Sel",
                "LEs",
                "sellerTest@mail.ru",
                "123paswd321",
                null,
                AppUserRole.SELLER.name(),
                new Bucket(),
                null
        );
        when(appUserRepository.getReferenceById(11L)).thenReturn(toSeller);
        when(appUserRepository.save(appUser2)).thenReturn(null);

        appUserService.makeSeller(toSeller.getId(), seller1);

        assertEquals(appUser2, toSeller);
        verify(appUserRepository, times(1)).getReferenceById(any());
        verify(appUserRepository, times(1)).save(any());
    }
}