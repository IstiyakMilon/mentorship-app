package com.algorizin.mentorship.assesment.service;

import com.algorizin.mentorship.assesment.dto.AssesmentRequestDTO;
import com.algorizin.mentorship.assesment.dto.AssesmentResponseDTO;
import com.algorizin.mentorship.assesment.entity.AssesmentEntity;
import com.algorizin.mentorship.assesment.repository.AssesmentRepository;
import com.algorizin.mentorship.util.ExceptionHandlerUtil;
import com.algorizin.mentorship.util.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AssesmentService {
    @Autowired
    private AssesmentRepository assesmentRepository;

    public List<AssesmentEntity> getAllAssesment() throws ExceptionHandlerUtil {
        List<AssesmentEntity> assesmentList = assesmentRepository.findAll();

        if (assesmentList == null || assesmentList.isEmpty())
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Assesment not found");
        return assesmentList;
    }

    public AssesmentEntity getAssesmentByOid(String oid) throws ExceptionHandlerUtil {
        AssesmentEntity assesmentEntity = assesmentRepository.findByOid(oid);

        if (assesmentEntity == null)
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Assesment not found");
        return assesmentEntity;
    }

    public AssesmentResponseDTO saveAssesment(AssesmentRequestDTO requestDTO) throws ExceptionHandlerUtil {
        AssesmentEntity assesmentEntity = new AssesmentEntity();
        BeanUtils.copyProperties(requestDTO, assesmentEntity);
        assesmentEntity.setCreatedOn(new Timestamp(new Date().getTime()));

        assesmentEntity = assesmentRepository.save(assesmentEntity);


        if (assesmentEntity.getOid() == null)
            throw new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, "Assesment not saved");

        return AssesmentResponseDTO.builder()
                .oid(assesmentEntity.getOid())
                .message("Assesment saved successfully")
                .build();
    }

    public AssesmentResponseDTO updateAssesment(AssesmentRequestDTO requestDTO, String oid) throws ExceptionHandlerUtil {

        AssesmentEntity assesmentEntity = assesmentRepository.findByOid(oid);
        BeanUtils.copyProperties(requestDTO, assesmentEntity, "oid");
//        assesmentEntity.setUpdatedBy(updatedBy);
        assesmentEntity.setUpdatedOn(new Timestamp(new Date().getTime()));
        assesmentEntity = assesmentRepository.save(assesmentEntity);

        return AssesmentResponseDTO.builder()
                .oid(assesmentEntity.getOid())
                .message("Assesment updated successfully")
                .build();
    }

    public ResponseEntity<Map<String, String>> deleteAssesment(String oid) throws ExceptionHandlerUtil {
        assesmentRepository.deleteById(oid);
        log.info("Removed Assesment for oid: {}", oid);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", Messages.ASSESMENT_REMOVED);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);

    }
}
