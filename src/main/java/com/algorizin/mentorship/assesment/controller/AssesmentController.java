package com.algorizin.mentorship.assesment.controller;

import com.algorizin.mentorship.assesment.dto.AssesmentRequestDTO;
import com.algorizin.mentorship.assesment.dto.AssesmentResponseDTO;
import com.algorizin.mentorship.assesment.entity.AssesmentEntity;
import com.algorizin.mentorship.assesment.service.AssesmentService;
import com.algorizin.mentorship.util.ExceptionHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/v1/assesment")
@Slf4j
public class AssesmentController {
    @Autowired
    private AssesmentService assesmentService;

    @GetMapping("/list")
    public ResponseEntity<List<AssesmentEntity>> getAssesmentList() {
        try {
            log.info("Request received for office list");
            List<AssesmentEntity> response = assesmentService.getAllAssesment();
            log.info("Response send for Assesment list: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }

    @GetMapping("/{OID}")
    public ResponseEntity<AssesmentEntity> getOfficebyOid(
//            @AuthenticationPrincipal Jwt principal,
            @PathVariable("OID") @NotEmpty String oid
    ) {
        try {
            log.info("Request received for assesment  by oid");
            AssesmentEntity response = assesmentService.getAssesmentByOid(oid);
            log.info("Response send for assesment by oid: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AssesmentResponseDTO> createAssesment(
            @Valid @RequestBody AssesmentRequestDTO requestDTO
    ) {
        try {
            log.info("Request received for assesment save:");
            AssesmentResponseDTO response = assesmentService.saveAssesment(requestDTO);
            log.info("Response send for assesment save: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }

    @PutMapping("/{OID}")
    public ResponseEntity<AssesmentResponseDTO> updateAssesment(
            @Valid @RequestBody AssesmentRequestDTO requestDTO,
            @PathVariable("OID") @NotEmpty String oid) {
        try {
            log.info("Request received for Assesment by oid");
            AssesmentResponseDTO response = assesmentService.updateAssesment(requestDTO, oid);
            log.info("Response send for Assesment: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ExceptionHandlerUtil ex) {
            log.error(ex.getMessage(), ex);
            throw new ResponseStatusException(ex.getCode(), ex.getMessage(), ex);
        }
    }
}
