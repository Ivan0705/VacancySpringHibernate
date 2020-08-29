package academits.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class DeleteRandomContactScheduler {

    private final static Logger logger =
            LoggerFactory.getLogger(DeleteRandomContactScheduler.class.getName());

    //  @Autowired
    //  private VacancyDao vacancyDao;

  /*  @Scheduled(fixedRate = 10000, initialDelay = 10000)
    public void sendEmailWithContactList() {
        Integer randomId = contactDao.getRandomId();

        if (randomId != null) {
            contactDao.remove(randomId);
            logger.info("Deleted contact with id {}", randomId);
        }
    }*/
}
