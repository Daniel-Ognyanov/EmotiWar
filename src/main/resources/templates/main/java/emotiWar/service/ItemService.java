package emotiWar.service;

public interface ItemService { 
    void initItems();

    boolean buyWeaponByID(Long id);
    boolean buyShieldByID(Long id);
    boolean buyHeadByID(Long id);
    boolean buyBootsByID(Long id);
    boolean buyTrinketsByID(Long id);
}
