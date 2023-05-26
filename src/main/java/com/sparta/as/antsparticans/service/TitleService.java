package com.sparta.as.antsparticans.service;

import com.sparta.as.antsparticans.model.dtos.TitleDTO;
import com.sparta.as.antsparticans.model.dtos.TitleDTOId;
import com.sparta.as.antsparticans.model.repositories.TitleDTORepository;
import com.sparta.as.antsparticans.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TitleService {

    public final TitleDTORepository titleDTORepository;

    @Autowired
    public TitleService(TitleDTORepository titleDTORepository) {
        this.titleDTORepository = titleDTORepository;
    }

    public List<TitleDTO> getAllTitlesDtosByTitleWorkingDuringAGivenRange(String title, LocalDate startDate, LocalDate endDate) {
        List<TitleDTO> listOfTitleDtosFilteredByTitle = titleDTORepository.getAllTitleDTOsByTitle(title);

        List<TitleDTO> listOfTitleDtosFilteredByDateRange = listOfTitleDtosFilteredByTitle.stream().filter(titleDTO -> Utils.isDateRangeWithin(startDate, endDate, titleDTO.getId().getFromDate(), titleDTO.getToDate())).toList();

        return listOfTitleDtosFilteredByDateRange;
    }
}
