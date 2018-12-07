package models.MedievalPack;

import models.BaseUnivers;

/**
 * Defines a medieval universe's context and behavior.
 */
public class UniversMedieval extends BaseUnivers {
    /**
     * The universe's friendly name.
     */
    public static final String NOM_UNIVERS = "MEDIEVAL";

    @Override
    public int getForceParDefaultChefs() {
        return 0;
    }

    @Override
    public boolean isChefsPeuventFrapper() {
        return false;
    }
}
