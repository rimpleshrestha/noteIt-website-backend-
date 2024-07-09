package com.example.noteitweb.controller;

import com.example.noteitweb.pojo.FavouriteNotePojo;
import com.example.noteitweb.pojo.UserPojo;
import com.example.noteitweb.service.FavouriteNoteService;
import com.example.noteitweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FavouriteNote ")
@RequiredArgsConstructor


public class FavouriteNoteController {
    private final FavouriteNoteService noteService;
    private FavouriteNoteService favouriteNoteService;

    @PostMapping("/save")
    public void save(@RequestBody FavouriteNotePojo favouriteNotePojo){
        FavouriteNotePojo favouriteNotePojo1 = null;
        this.favouriteNoteService.saveData(favouriteNotePojo);
    }
}
