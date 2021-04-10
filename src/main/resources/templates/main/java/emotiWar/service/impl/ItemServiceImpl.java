package emotiWar.service.impl;

import emotiWar.model.entity.ItemEntity;
import emotiWar.model.entity.UserEntity;
import emotiWar.repository.EmotiRepository;
import emotiWar.repository.ItemRepository;
import emotiWar.repository.UserRepository;
import emotiWar.service.ItemService;
import emotiWar.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final EmotiRepository emotiRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;

    public ItemServiceImpl(EmotiRepository emotiRepository, UserRepository userRepository, ItemRepository itemRepository, UserService userService) {
        this.emotiRepository = emotiRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    @Override
    public void initItems() {
        if (itemRepository.findAll().isEmpty()) {

            itemRepository.save(new ItemEntity("Copper Weapon", 5, 2, 5));
            itemRepository.save(new ItemEntity("Copper Shield", 2, 5, 4));
            itemRepository.save(new ItemEntity("Copper Head", 3, 3, 3));
            itemRepository.save(new ItemEntity("Copper Boots", 2, 2, 2));
            itemRepository.save(new ItemEntity("Copper Trinkets", 1, 1, 1));


            itemRepository.save(new ItemEntity("Silver Weapon", 15, 8, 25));
            itemRepository.save(new ItemEntity("Silver Shield", 8, 15, 22));
            itemRepository.save(new ItemEntity("Silver Head", 13, 13, 18));
            itemRepository.save(new ItemEntity("Silver Boots", 12, 12, 17));
            itemRepository.save(new ItemEntity("Silver Trinkets", 11, 11, 16));

            itemRepository.save(new ItemEntity("Gold Weapon", 30, 15, 65));
            itemRepository.save(new ItemEntity("Gold Shield", 15, 30, 60));
            itemRepository.save(new ItemEntity("Gold Head", 23, 23, 45));
            itemRepository.save(new ItemEntity("Gold Boots", 22, 22, 44));
            itemRepository.save(new ItemEntity("Gold Trinkets", 21, 21, 43));

            itemRepository.save(new ItemEntity("Wood Weapon", 0, 0, 0));
            itemRepository.save(new ItemEntity("Wood Shield", 0, 0, 0));
            itemRepository.save(new ItemEntity("Wood Head", 0, 0, 0));
            itemRepository.save(new ItemEntity("Wood Boots", 0, 0, 0));
            itemRepository.save(new ItemEntity("Wood Trinkets", 0, 0, 0));
        }
    }

    @Override
    public boolean buyWeaponByID(Long id) {
        ItemEntity newItem = itemRepository.findById(id).orElse(null);
        UserEntity currUser = userService.getCurrentUser();
        if (currUser.getEmoti().getCoins() >= newItem.getPrice()) {
            currUser.getEmoti().setWeapon(newItem);
            currUser.getEmoti().updateStrength();
            currUser.getEmoti().updateArmor();
            currUser.getEmoti().setCoins(currUser.getEmoti().getCoins() - newItem.getPrice());
            emotiRepository.save(currUser.getEmoti());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean buyShieldByID(Long id) {
        ItemEntity newItem = itemRepository.findById(id).orElse(null);
        UserEntity currUser = userService.getCurrentUser();
        if (currUser.getEmoti().getCoins() >= newItem.getPrice()) {
            currUser.getEmoti().setShield(newItem);
            currUser.getEmoti().updateStrength();
            currUser.getEmoti().updateArmor();
            currUser.getEmoti().setCoins(currUser.getEmoti().getCoins() - newItem.getPrice());
            emotiRepository.save(currUser.getEmoti());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean buyHeadByID(Long id) {
        ItemEntity newItem = itemRepository.findById(id).orElse(null);
        UserEntity currUser = userService.getCurrentUser();
        if (currUser.getEmoti().getCoins() >= newItem.getPrice()) {
            currUser.getEmoti().setHead(newItem);
            currUser.getEmoti().updateStrength();
            currUser.getEmoti().updateArmor();
            currUser.getEmoti().setCoins(currUser.getEmoti().getCoins() - newItem.getPrice());
            emotiRepository.save(currUser.getEmoti());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean buyBootsByID(Long id) {
        ItemEntity newItem = itemRepository.findById(id).orElse(null);
        UserEntity currUser = userService.getCurrentUser();
        if (currUser.getEmoti().getCoins() >= newItem.getPrice()) {
            currUser.getEmoti().setBoots(newItem);
            currUser.getEmoti().updateStrength();
            currUser.getEmoti().updateArmor();
            currUser.getEmoti().setCoins(currUser.getEmoti().getCoins() - newItem.getPrice());
            emotiRepository.save(currUser.getEmoti());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean buyTrinketsByID(Long id) {
        ItemEntity newItem = itemRepository.findById(id).orElse(null);
        UserEntity currUser = userService.getCurrentUser();
        if (currUser.getEmoti().getCoins() >= newItem.getPrice()) {
            currUser.getEmoti().setTrinkets(newItem);
            currUser.getEmoti().updateStrength();
            currUser.getEmoti().updateArmor();
            currUser.getEmoti().setCoins(currUser.getEmoti().getCoins() - newItem.getPrice());
            emotiRepository.save(currUser.getEmoti());
            return true;
        } else {
            return false;
        }
    }

}
