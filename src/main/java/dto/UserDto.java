package dto;

/**
 * Created by guojing on 2017/3/18.
 */
public class UserDto {
    /**
     * user dto id.
     */
    private final long id;

    /**
     * user dto name.
     */
    private final String name;

    /**
     * user dto constructor.
     * @param userId user id
     * @param userName user name
     */
    public UserDto(final long userId, final String userName) {
        this.id = userId;
        this.name = userName;
    }

    /**
     * user id getter.
     * @return user id
     */
    public final long getId() {
        return id;
    }

    /**
     * user name getter.
     * @return user name
     */
    public final String getName() {
        return name;
    }
}
