package com.charlotte.inknote.dto;

import com.charlotte.inknote.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteDTOMapper {

    public Note toNote(NoteDTO noteDTO) {
        return new Note(noteDTO.getTitle(), noteDTO.getDescription());
    }
}
