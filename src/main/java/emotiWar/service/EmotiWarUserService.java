package emotiWar.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class EmotiWarUserService implements UserDetailsService {

    private final EmotiService emotiService;

    public EmotiWarUserService(EmotiService emotiService) {
        this.emotiService = emotiService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }


    @Scheduled(fixedDelay = 10000, initialDelay = 1000)
    public void scheduleGetCoins() {
        emotiService.giveCoins();
    }
}
