package dto;

/**
 * Created by guojing on 2017/3/18.
 */
public class UserDto {
    private final long id;
    private final String name;

    public UserDto(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
