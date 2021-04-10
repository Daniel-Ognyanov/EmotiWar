package emotiWar.service.impl;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.model.entity.UserEntity;
import emotiWar.repository.EmotiRepository;
import emotiWar.repository.UserRepository;
import emotiWar.service.EmotiService;
import emotiWar.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EmotiServiceImpl implements EmotiService {
    private final EmotiRepository emotiRepository;
    private final UserRepository userRepository;


    public EmotiServiceImpl(EmotiRepository emotiRepository, UserRepository userRepository) {
        this.emotiRepository = emotiRepository;
        this.userRepository = userRepository;

    }

    @Override
    public void emotiSave(EmotiEntity emotiEntity) {
        emotiRepository.save(emotiEntity);
    }

    @Override
    public EmotiEntity getEmoti() {
        UserEntity currUser = userRepository.findByUsername(getCurrentUsername()).orElse(null);

        return currUser.getEmoti();
    }

    @Override
    public void giveCoins() {
        emotiRepository.findAll().forEach(e -> {
            if (e.getCoinsToCollect() <= 5) {
                e.setCoinsToCollect(e.getCoinsToCollect() + 1);
                emotiRepository.save(e);
            }
        });
    }

    @Override
    public void collectCoins() {
        EmotiEntity emoti = this.getEmoti();
        emoti.setCoins(emoti.getCoins() + emoti.getCoinsToCollect());
        emoti.setCoinsToCollect(0);
        emotiRepository.save(emoti);
    }

    @Override
    public void giveBattles() {
        emotiRepository.findAll().forEach(e -> {
            e.setBattles(5);
            emotiRepository.save(e);
        });
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
