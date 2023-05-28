package jp.kobe_u.cs27.Poker.application.repository;

import org.springframework.data.repository.CrudRepository;
import jp.kobe_u.cs27.Poker.application.bean.SiteAccess;
import java.util.Date;

public interface SiteAccessRepository extends CrudRepository<SiteAccess, Long> {
    SiteAccess findByDate(Date date);
}
