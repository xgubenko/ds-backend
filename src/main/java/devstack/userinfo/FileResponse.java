package devstack.userinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private String id;
    private String name;
    private Long size;
    private String url;
    private String contentType;
}
