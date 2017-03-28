package dto;

/**
 * Created by guojing on 2017/3/27.
 */
public class StatusDto {
    /**
     * normal dto message.
     */
    private String message;

    /**
     * status dto object.
     * @param statusMessage message
     */
    public StatusDto(final String statusMessage) {
        this.message = statusMessage;
    }

    /**
     * get dto message.
     * @return message
     */
    public final String getMessage() {
        return message;
    }
}
