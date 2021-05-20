package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;

public interface LanguageOfInterestService {
    Page<LanguageOfInterest> getAllTopics(Pageable pageable);
    LanguageOfInterest getLanguaeById(Long languageId);
    LanguageOfInterest createLanguage(LanguageOfInterest Language);
    LanguageOfInterest updateLanguage(Long languegeId, LanguageOfInterest languageDetails);
    ResponseEntity<?> deleteLanguage(Long languageId);
}
