package domain;

/**
 * Created by guojing on 2017/3/18.
 */
public class UserDomain {
    /**
     * user id.
     */
    private Long id;

    /**
     * user name.
     */
    private String name;

    /**
     * user id getter.
     * @return user id
     */
    public final Long getId() {
        return id;
    }

    /**
     * user name getter.
     * @return user name
     */
    public final String getName() {
        return name;
    }

    /**
     * user name setter.
     * @param userName user name
     */
    public final void setName(final String userName) {
        this.name = userName;
    }

}
