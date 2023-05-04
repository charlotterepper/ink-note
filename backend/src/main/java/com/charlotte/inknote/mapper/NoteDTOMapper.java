package com.charlotte.inknote.mapper;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.UserDTO;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteDTOMapper {
    private final UserDTOMapper userDTOMapper;

    public NoteDTOMapper(UserDTOMapper userDTOMapper) {
        this.userDTOMapper = userDTOMapper;
    }

    public Note toNote(NoteDTO noteDTO) {
        User user = userDTOMapper.toUser(noteDTO.getUserDTO());
        return new Note(noteDTO.getId(), noteDTO.getTitle(), noteDTO.getDescription(), user);
    }

    public NoteDTO toNoteDTO(Note note) {
        UserDTO userDTO = userDTOMapper.toUserDTO(note.getUser());
        return new NoteDTO(note.getId(), note.getTitle(), note.getDescription(), userDTO);
    }

    public List<NoteDTO> toNoteDTOList(List<Note> notes) {
        return notes.stream()
                .map(this::toNoteDTO)
                .collect(Collectors.toList());
    }
}
