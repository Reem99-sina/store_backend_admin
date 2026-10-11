package store_backend_admin.exception;

public class CampaignNotFoundException extends RuntimeException {

    public CampaignNotFoundException(String message) {
        super(message);
    }
}