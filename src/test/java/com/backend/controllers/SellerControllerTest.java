package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.appuser.seller.Seller;
import com.backend.product.Product;
import com.backend.product.ProductCategory;
import com.backend.repository.AppUserRepository;
import com.backend.repository.CategoryRepository;
import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SellerControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private AppUserService appUserService;


    @InjectMocks
    private SellerController appUserController;

    @Test
    public void testParseExcelFile() throws IOException {
        var resource = new ClassPathResource("Test.xlsx");

        // Create a mock AppUser with a seller ID
        var appUser = new AppUser();
        appUser.setId(123L);

        // Create a mock Seller for the AppUser
        var seller = new Seller();
        seller.setId(appUser.getId());
        appUser.setSeller(seller);
        when(appUserService.getUserById(appUser.getId())).thenReturn(appUser);
        ProductCategory category1 = new ProductCategory(
                10L,
                "Category1",
                "Category1 desc",
                null
        );
        ProductCategory category2 = new ProductCategory(
                11L,
                "Category2",
                "Category2 desc",
                null
        );
        // Create a mock ProductService to return categories

        when(productService.getCategoriesByTitle(anyList())).thenReturn(
                List.of(category1, category2), List.of(category2)
        );

        // Create a mock MultipartFile
        var is = resource.getInputStream();
        var file = new MockMultipartFile("file", "Test.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                is);

        // Call the method being tested
        appUserController.parseExcelFile(file, appUser);

        // Verify that the ProductService.create() method was called with the expected products
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productService, times(2)).create(argumentCaptor.capture());
        List<Product> captured = argumentCaptor.getAllValues();

        // Verify the product data
        assertEquals("Product1", captured.get(0).getTitle());
        assertEquals("Description1", captured.get(0).getDescription());
        assertEquals(9.99f, captured.get(0).getPrice());
        assertEquals(10L, captured.get(0).getAmount());
        assertEquals("Image1", captured.get(0).getImageLink());
        assertEquals(2, captured.get(0).getCategories().size());
        assertEquals("Category1", captured.get(0).getCategories().get(0).getCategoryName());
        assertEquals("Category2", captured.get(0).getCategories().get(1).getCategoryName());
        assertEquals(seller, captured.get(0).getSeller());
        assertEquals(new Date().toString(), captured.get(0).getUpload().toString());

        assertEquals("Product2", captured.get(1).getTitle());
        assertEquals("Description2", captured.get(1).getDescription());
        assertEquals(19.99f, captured.get(1).getPrice());
        assertEquals(20L, captured.get(1).getAmount());
        assertEquals("Image2", captured.get(1).getImageLink());
        assertEquals(1, captured.get(1).getCategories().size());
        assertEquals("Category2", captured.get(1).getCategories().get(0).getCategoryName());
        assertEquals(seller, captured.get(1).getSeller());
        assertEquals(new Date().toString(), captured.get(1).getUpload().toString());
    }
}