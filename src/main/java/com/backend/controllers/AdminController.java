package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.repository.AppUserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AppUserRepository appUserRepository;

    /*@GetMapping ("/users")
    public ResponseEntity<List<AppUser>> get10UsersById(){
        Pageable page = PageRequest.of(10, 10);
        return ResponseEntity
                .ok(appUserRepository.findFirst10OrderById(PageRequest.of(10, 10)));
    }*/
}
