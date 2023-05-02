package retoBackendOrenes.RetoBackend.enums;

public enum ResponseCodes {
    SUCCESS ("1", "200", "Success"),
    ERROR_NOT_FOUND ("2", "404", "Not found error"),
    GENERIC_ERROR ("3", "500", "Generic error"),
    FORBIDDEN ("4", "403", "Forbidden");

    private final String id;
    private final String responseCode;
    private final String description;

    private ResponseCodes (String id, String responseCode, String description) {
        this.id = id;
        this.responseCode = responseCode;
        this.description = description;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getDescription() {
        return this.description;
    }
}
