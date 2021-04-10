package emotiWar.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class EmotiWarUserServiceImpl implements UserDetailsService {

    private final EmotiService emotiService;

    public EmotiWarUserServiceImpl(EmotiService emotiService) {
        this.emotiService = emotiService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    @Scheduled(fixedDelay = 600000, initialDelay = 600000)
    public void scheduleGetCoins() {
        emotiService.giveCoins();
    }

    @Scheduled(fixedDelay = 600000, initialDelay = 600000)
    public void scheduleWarCounter() {
        emotiService.giveBattles();
    }
}
