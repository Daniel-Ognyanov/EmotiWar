package emotiWar.service.impl;

import emotiWar.model.entity.EmotiEntity;
import emotiWar.model.entity.UserEntity;
import emotiWar.repository.EmotiRepository;
import emotiWar.repository.UserRepository;
import emotiWar.service.FightService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class FightServiceImpl implements FightService {
    private final UserRepository userRepository;
    private final EmotiRepository emotiRepository;

    public FightServiceImpl(UserRepository userRepository, EmotiRepository emotiRepository) {
        this.userRepository = userRepository;
        this.emotiRepository = emotiRepository;
    }

    @Override
    public void attack() {
        UserEntity attacker = userRepository.findByUsername(getCurrentUsername()).orElse(null);

        Long number = (long) getRandomNumber(1, userRepository.findAll().size());
        while (number == attacker.getId()) {
            number = (long) getRandomNumber(1, userRepository.findAll().size());
        }

        UserEntity defender = userRepository.findById(number).orElse(null);

        int attackerStr = attacker.getEmoti().getStrength();
        int defenderArm = defender.getEmoti().getArmor();
        int defenderStr = defender.getEmoti().getStrength();

        if (attackerStr > defenderArm) {
            EmotiEntity attackerEmoti = attacker.getEmoti();
            attackerEmoti.setBaseStrength(attackerEmoti.getBaseStrength() + 1);
            attackerEmoti.updateStrength();
            emotiRepository.save(attackerEmoti);
            winner(attacker, defender);
        } else if (attackerStr == defenderArm) {
            if (attackerStr >= defenderStr) {
                EmotiEntity attackerEmoti = attacker.getEmoti();
                attackerEmoti.setBaseStrength(attackerEmoti.getBaseStrength() + 1);
                attackerEmoti.updateStrength();
                emotiRepository.save(attackerEmoti);
                winner(attacker, defender);
            } else {
                EmotiEntity defenderEmoti = defender.getEmoti();
                defenderEmoti.setBaseArmor(defenderEmoti.getBaseArmor() + 1);
                defenderEmoti.updateArmor();
                emotiRepository.save(defenderEmoti);
                winner(defender, attacker);
            }
        } else {
            EmotiEntity defenderEmoti = defender.getEmoti();
            defenderEmoti.setBaseArmor(defenderEmoti.getBaseArmor() + 1);
            defenderEmoti.updateArmor();
            emotiRepository.save(defenderEmoti);
            winner(defender, attacker);
        }
        EmotiEntity atck = attacker.getEmoti();
        emotiRepository.save(atck);
    }



    private void winner(UserEntity attacker, UserEntity defender) {
        EmotiEntity attackerEmoti = attacker.getEmoti();
        EmotiEntity defenderEmoti = defender.getEmoti();
        attackerEmoti.setWins(attackerEmoti.getWins() + 1);
        attackerEmoti.setCoins(attackerEmoti.getCoins() + 6);
        if (defenderEmoti.getCoins() >= 6){
            defenderEmoti.setCoins(defenderEmoti.getCoins() - 6);
        }else {
            defenderEmoti.setCoins(0);
        }
        defenderEmoti.setLoses(defenderEmoti.getLoses() + 1);
        attackerEmoti.setBattles(attackerEmoti.getBattles() - 1);
        emotiRepository.save(attackerEmoti);
        emotiRepository.save(defenderEmoti);
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

}
