package extensions;

/**
 * A base extension interface. Extension must implement it, then,
 * the scenario must load the extension to override the base behavior.
 */
public interface IExtensionDeGestionnaire {
    /**
     * The loading instructions of the extension.
     */
    void Load();
}
