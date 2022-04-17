package com.algorizin.mentorship.assesmentsubmission.repository;

import com.algorizin.mentorship.assesmentsubmission.dto.AssesmentSubmissionListDTO;
import com.algorizin.mentorship.util.Constants;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AssesmentSubmissionCustomRepository {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<AssesmentSubmissionListDTO> getAssesmentSubmissionList(String userId, String role) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String sql = "";
        sql = " Select a.oid as assesmentOid, a.title as assesmentTitle, a.description as description,";
        sql += " (select name from UserProfileEntity where loginOid = a.mentorId) as mentorName, a.mentorId as mentorId, ";
        sql += " a.deadline as deadline, asm.studentId as studentId, ";
        sql += " (select name from UserProfileEntity where loginOid  = asm.studentId) as studentName, ";
        sql += " asub.oid as submissionOid, asub.filePath as filePath, asub.submissionDate as submissionDate ";
        sql += " from AssesmentEntity a, AssignmentEntity asm, AssesmentSubmissionEntity asub ";
        sql += " where 1=1";
        sql += " and a.oid = asm.assesmentId";
        sql += " and asm.oid = asub.assignmentId";
        if(role.equalsIgnoreCase(Constants.STUDENT_USER)){
            sql += " and asm.studentId  = '" + userId;
        }
        if(role.equalsIgnoreCase(Constants.MENTOR_USER)){
            sql += " and a.mentorId  = '" + userId;
        }

//        if (!Strings.isNullOrEmpty(searchText)
//                && !searchText.equalsIgnoreCase("undefined")) {
//            sql += " AND (LOWER(up.name) like '%" + searchText.trim().toLowerCase()
//                    + "%' OR" + " LOWER(up.designation) like '%" + searchText.trim().toLowerCase()
//                    + "%' OR" + " LOWER(uh.orderSection) like '%" + searchText.trim().toLowerCase()
//                    + "%')";
//        }

        Query query = entityManager.createQuery(sql);
//        query.setFirstResult(offset * limit);
//        query.setMaxResults(limit);

        List<AssesmentSubmissionListDTO> list = query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new AliasToBeanResultTransformer(AssesmentSubmissionListDTO.class)).list();

        entityManager.close();
        return list;
    }
}
