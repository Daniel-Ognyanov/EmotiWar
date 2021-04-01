package emotiWar;
import emotiWar.service.ItemService;
import emotiWar.service.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final ItemService itemService;
    private final UserRoleService userRoleService;


    public DBInit(ItemService itemService, UserRoleService userRoleService) {
        this.itemService = itemService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {

        userRoleService.initRoles();
        itemService.initItems();
    }
}
