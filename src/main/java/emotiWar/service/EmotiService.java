package emotiWar.service;

import emotiWar.model.entity.EmotiEntity;

public interface EmotiService {
    void emotiSave(EmotiEntity emotiEntity);
    EmotiEntity getEmoti();

    void giveCoins();

    void collectCoins();
}
