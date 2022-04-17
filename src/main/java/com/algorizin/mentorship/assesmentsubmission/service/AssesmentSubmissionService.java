package com.algorizin.mentorship.assesmentsubmission.service;

import com.algorizin.mentorship.assesment.entity.AssesmentEntity;
import com.algorizin.mentorship.assesmentsubmission.dto.AssesmentSubmissionListDTO;
import com.algorizin.mentorship.assesmentsubmission.entity.AssesmentSubmissionEntity;
import com.algorizin.mentorship.assesmentsubmission.repository.AssesmentSubmissionCustomRepository;
import com.algorizin.mentorship.assesmentsubmission.repository.AssesmentSubmissionRepository;
import com.algorizin.mentorship.authorization.user.entity.LoginEntity;
import com.algorizin.mentorship.authorization.user.repository.LoginRepository;
import com.algorizin.mentorship.util.Constants;
import com.algorizin.mentorship.util.ExceptionHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AssesmentSubmissionService {
    @Autowired
    private AssesmentSubmissionRepository assesmentSubmissionRepository;

    @Autowired
    private AssesmentSubmissionCustomRepository assesmentSubmissionCustomRepository;

    @Autowired
    private LoginRepository loginRepository;
    //    public ResponseEntity<Map<String, Object>> getPendingApplication(String userId, int limit, int offset, String searchText) throws ExceptionHandlerUtil {
//        List<ApplicationListResponseDTO> allList = applicationCustomRepository.getPendingApplicationList(userId, limit, offset, searchText);
//
//        int count = applicationCustomRepository.getPendingAllListCount(userId);
//        if (allList.size() == 0) {
//            log.error("Error occurred during retrieving all list: all list not found");
//            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Data not found");
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", allList);
//        response.put("totalRecords", count);
//        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//    }
    public ResponseEntity<Map<String, Object>> getAllSubmission(String userId) throws ExceptionHandlerUtil {
        LoginEntity loginEntity = loginRepository.findByUserId(userId);
        List<AssesmentSubmissionListDTO> submissionList = assesmentSubmissionCustomRepository.getAssesmentSubmissionList(userId, loginEntity.getRole());

        if (submissionList == null || submissionList.isEmpty())
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Assesment submission not found");
        Map<String, Object> response = new HashMap<>();
        response.put("data", submissionList);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    public List<AssesmentSubmissionEntity> getAllAssesment() throws ExceptionHandlerUtil {
        List<AssesmentSubmissionEntity> submissionList = assesmentSubmissionRepository.findAll();

        if (submissionList == null || submissionList.isEmpty())
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "Assesment not found");
        return submissionList;
    }
}
