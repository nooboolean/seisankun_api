package com.dededesignworkshop.seisankun_api.domain.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Travel implements Serializable {

    private Integer id;

    @NotBlank(message = "nameは必須です")
    @Size(max = 20, message = "nameは20文字以内で入力してください")
    private String name;

    private String hashId;

    @NotNull(message = "travelStartは必須です")
    private String travelStart;

    @NotNull(message = "travelEndは必須です")
    private String travelEnd;

    private Integer createdBy;

    private String createdAt;

    private Integer updatedBy;

    private String updatedAt;

    private Integer deletedBy;

    private String deletedAt;

    private Integer deleteFlag;

}
