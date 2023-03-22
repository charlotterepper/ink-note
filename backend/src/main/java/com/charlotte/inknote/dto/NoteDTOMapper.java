package com.charlotte.inknote.dto;

import com.charlotte.inknote.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteDTOMapper {

    public Note toNote(NoteDTO noteDTO) {
        return new Note(noteDTO.getTitle(), noteDTO.getDescription());
    }

    public NoteDTO toNoteDTO(Note note) {
        return new NoteDTO(note.getTitle(), note.getDescription());
    }

    public List<NoteDTO> toNoteDTOList(List<Note> notes) {
        return notes.stream()
                .map(this::toNoteDTO)
                .collect(Collectors.toList());
    }
}
