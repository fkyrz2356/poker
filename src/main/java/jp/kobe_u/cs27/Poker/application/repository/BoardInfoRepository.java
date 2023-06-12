package jp.kobe_u.cs27.Poker.application.repository;

import org.springframework.data.repository.CrudRepository;
import jp.kobe_u.cs27.Poker.application.bean.BoardInfo;
import java.util.List;

public interface BoardInfoRepository extends CrudRepository<BoardInfo, Long> {
    List<BoardInfo> findByDateAndTypeOrderByBoardNumberAsc(String date, String type);
    List<BoardInfo> findByDate(String date);
    List<BoardInfo> findByDateLessThan(String date);
}