package com.algorizin.mentorship.assesmentsubmission.controller;

import com.algorizin.mentorship.assesmentsubmission.service.AssesmentSubmissionService;
import com.algorizin.mentorship.util.ExceptionHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/v1/assesmentsubmission")
@Slf4j
public class AssesmentSubmissionController {
    @Autowired
    private AssesmentSubmissionService assesmentSubmissionService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAssesmentSubmissionList(
            Authentication authentication
    ) {
        try {
            log.info("Request received for Assesment Submission list vy: {}", authentication.getName());
            ResponseEntity<Map<String, Object>> response = assesmentSubmissionService.getAllSubmission(authentication.getName());
            log.info("Response sent for Assesment Submission list : {}", response);
            return response;
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }
}
