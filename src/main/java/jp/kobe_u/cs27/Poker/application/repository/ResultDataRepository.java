package jp.kobe_u.cs27.Poker.application.repository;

import jp.kobe_u.cs27.Poker.application.bean.ResultData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// ResultDataRepository.java
@Repository
public interface ResultDataRepository extends CrudRepository<ResultData, Long> {
    List<ResultData> findByUserIDAndDate(String userID, String date);
    List<ResultData> findByDateOrderBySolvedDescRestTimeDesc(String date);
}


