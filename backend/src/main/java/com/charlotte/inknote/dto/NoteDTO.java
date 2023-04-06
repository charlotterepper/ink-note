package com.charlotte.inknote.dto;

public class NoteDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO userDTO;

    public NoteDTO(Long id, String title, String description, UserDTO userDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userDTO = userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userDTO=" + userDTO +
                '}';
    }
}
