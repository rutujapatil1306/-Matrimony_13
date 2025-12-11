package com.spring.jwt.SearchFiltter;

import com.spring.jwt.entity.CompleteProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchProfileRepository extends JpaRepository<CompleteProfile, Integer> {

    @Query("""
        SELECT cp
        FROM CompleteProfile cp
        LEFT JOIN cp.userProfile up
        LEFT JOIN cp.educationAndProfession ep
        LEFT JOIN cp.contactDetails cd
        WHERE (:gender IS NULL OR up.gender = :gender)
        AND (:maritalStatus IS NULL OR up.maritalStatus = :maritalStatus)
        AND (:ageFrom IS NULL OR up.age >= :ageFrom)
        AND (:ageTo IS NULL OR up.age <= :ageTo)
        AND (:heightFrom IS NULL OR up.height >= :heightFrom)
        AND (:heightTo IS NULL OR up.height <= :heightTo)
        AND (:occupation IS NULL OR ep.occupation = :occupation)
        AND (:education IS NULL OR LOWER(ep.education) LIKE LOWER(CONCAT('%', :education, '%')))
        AND (:city IS NULL OR LOWER(cd.city) LIKE LOWER(CONCAT('%', :city, '%')))
        AND (:district IS NULL OR LOWER(up.district) LIKE LOWER(CONCAT('%', :district, '%')))
        """)
    Page<CompleteProfile> searchProfiles(
            String gender,
            String maritalStatus,
            Integer ageFrom,
            Integer ageTo,
            Integer heightFrom,
            Integer heightTo,
            String occupation,
            String education,
            String city,
            String district,
            Pageable pageable
    );
}
