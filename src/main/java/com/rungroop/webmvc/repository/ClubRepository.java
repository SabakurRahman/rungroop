package com.rungroop.webmvc.repository;

import com.rungroop.webmvc.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    @Query("SELECT c from Club c where c.title LIKE concat('%',:query,'%') ")
    List<Club> searchClubByTitle(String query);
}
