package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.appuser.seller.Seller;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.SellerDTO.SellerDTO;
import com.backend.product.Product;
import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/sellers")
@Controller
public class SellerController {

    final private AppUserService appUserService;
    final private ProductService productService;
    final private Logger logger = LoggerFactory.getLogger(SellerController.class);
    @Autowired
    public SellerController(AppUserService appUserService,
                            ProductService productService){
        this.appUserService = appUserService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getFullProfile(@PathVariable String id,
                                 @RequestParam(required = false) String state,
                                 Model model){
        Long userId = Long.decode(id);
        try {
            AppUser appUser = appUserService.getUserById(userId);
            SellerDTO seller = new SellerDTO(appUser.getSeller());
            seller.setProducts(productService.getProductsBySeller(appUser.getSeller()));
            model.addAttribute("userInfo", new AppUserDTO(appUser));
            model.addAttribute("sellerInfo", new SellerDTO(appUser.getSeller()));
            model.addAttribute("showNewProd", !(state == null));
        }
        catch(EntityNotFoundException e){
            logger.info("Couldn't find user for id: " + userId);
            return "CustomError";
        }
        return "sellers/profile";

    }

    @GetMapping("/{id}/new")
    public String uploadForm(@PathVariable String id,
                             Model model){
        Long userId = Long.decode(id);
        try {
            AppUserDTO appUserDTO = appUserService.getUserDTOById(userId);
        }
        catch(EntityNotFoundException e){
            return "CustomError";
        }
        model.addAttribute("id", id);
        return "sellers/NewProduct";
    }

    @PostMapping("/{id}/create")
    public String createProduct(@ModelAttribute("newProduct") Product product,
                              @PathVariable String id){
        Long sellerId = Long.decode(id);
        Date creationTime = new Date();
        product.setUpload(creationTime);
        product.setSeller(appUserService.getUserById(sellerId).getSeller());
        productService.create(product);
        return "sellers/profile";
    }

    @GetMapping("/excelForm")
    public String getExcelForm(){
        return "sellers/ExcelForm";
    }

    @PostMapping("/upload/excel")
    public String parseExcelFile(@RequestParam("file") MultipartFile file,
                                 @AuthenticationPrincipal AppUser appUser){
        Long sellerId = appUser.getId();
        List<Product> productList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
                XSSFRow row = sheet.getRow(i);
                productList.add(new Product(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        (float) row.getCell(2).getNumericCellValue(),
                        (long) row.getCell(3).getNumericCellValue(),
                        row.getCell(4).getStringCellValue(),
                        productService.getCategoriesByTitle(List.of(row.getCell(5)
                                .getStringCellValue()
                                .split(",")))
                ));

            }

        } catch(IOException e){
            logger.debug("Couldn't open excel file to parse products "
                    + "time: {}, user: {}", Date.from(Instant.now()), appUser.getId());
            return "CustomError";
        }
        Seller seller = appUserService.getUserById(sellerId).getSeller();
        productList.forEach(product -> {
            product.setSeller(seller);
            product.setUpload(new Date());
            productService.create(product);
        });

        return "sellers/profile";
    }
}
