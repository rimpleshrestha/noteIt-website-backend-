package com.example.noteitweb.service.impl;

import com.example.noteitweb.entity.FavouriteNote;
import com.example.noteitweb.pojo.FavouriteNotePojo;
import com.example.noteitweb.repository.FavouriteNoteRepository;
import com.example.noteitweb.service.FavouriteNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteNoteServiceImpl implements FavouriteNoteService {

    private final FavouriteNoteRepository favouriteNoteRepository;
    @Override
    public void saveData(FavouriteNotePojo favouriteNotePojoPojo) {
        FavouriteNote favNote = new FavouriteNote();

        favNote.setId(favouriteNotePojoPojo.getId());
        favNote.setTitle(favouriteNotePojoPojo.getTitle());
        favNote.setContent(favouriteNotePojoPojo.getContent());

        favouriteNoteRepository.save(favNote);

    }
}
