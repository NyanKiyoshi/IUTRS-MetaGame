package models.WesternPack;

import models.BaseUnivers;

/**
 * Defines a western universe's context and behavior.
 */
public class UniversWestern extends BaseUnivers {
    /**
     * The universe's friendly name.
     */
    public static final String NOM_UNIVERS = "WESTERN";

    @Override
    public int getForceParDefaultChefs() {
        return 0;
    }

    @Override
    public boolean isChefsPeuventFrapper() {
        return false;
    }
}
