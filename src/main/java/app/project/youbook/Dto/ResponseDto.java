package app.project.youbook.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ResponseDto {
    private String status;
    private String message;
    private Object data;

    public ResponseDto(String status,String message){
        this.message = message;
        this.status = status;
    }
}
