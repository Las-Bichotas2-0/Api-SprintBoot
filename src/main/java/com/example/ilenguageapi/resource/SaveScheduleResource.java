package com.example.ilenguageapi.resource;


import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
public class SaveScheduleResource {

    @NotBlank
    @Size(min = 3, max = 20, message="NameCourse must have between 3 and 20 characters")
    private String name;

    @NotNull
    @Positive(message ="Hours must have a positive value")
    private int hoursDuration;

    public int getHoursDuration() {
        return hoursDuration;
    }
    public String getName() {
        return name;
    }
    public SaveScheduleResource setHoursDuration(int hoursDuration) {
        this.hoursDuration = hoursDuration;
        return this;
    }

    public SaveScheduleResource setName(String name) {
        this.name = name;
        return this;
    }
}
