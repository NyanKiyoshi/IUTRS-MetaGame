package models;

/**
 * Defines a base universe context, with its hard-coded parameters.
 */
public abstract class BaseUnivers {
    /**
     * Dummy constructor.
     */
    public BaseUnivers() {}

    /**
     * Retrieves the default hitting force of the chefs.
     * @return The default hitting force of the chefs.
     */
    public abstract int getForceParDefaultChefs();

    /**
     * Checks if the chefs can hit people.
     * @return {@code True} if chefs can hit people.
     */
    public abstract boolean isChefsPeuventFrapper();
}
