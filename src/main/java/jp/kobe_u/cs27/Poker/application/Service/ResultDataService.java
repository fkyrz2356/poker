package jp.kobe_u.cs27.Poker.application.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.kobe_u.cs27.Poker.application.bean.ResultData;
import jp.kobe_u.cs27.Poker.application.repository.ResultDataRepository;


@Service
public class ResultDataService {

    private final ResultDataRepository repository;

    public ResultDataService(ResultDataRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveData(String userID, int solved, String date, double restTime) {
        ResultData resultData = new ResultData();
        resultData.setUserID(userID);
        resultData.setSolved(solved);
        resultData.setDate(date);
        resultData.setRestTime(restTime);
        repository.save(resultData);
    }
}
