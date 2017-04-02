package model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by guojing on 2017/4/2.
 */
@Document(collection = "jedi")
public class UserProps {
    @Id
    private Long id;

    private String content;

    public UserProps() {
        super();
    }

    public UserProps(String content) {
        this.content = content;
    }

    public final Long getId() { return id; }

    public final void setId(final Long id) { this.id = id; }

    public final String getContent() { return content; }

    public final void setContent(final String content) { this.content = content; }

    @Override
    public String toString() {
        return String.format(
            "Todo[id=%s, content='%s']",
            id, content);
    }
}
