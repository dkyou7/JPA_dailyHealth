package com.spo.repository;

import com.spo.domain.Board;
import com.spo.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
