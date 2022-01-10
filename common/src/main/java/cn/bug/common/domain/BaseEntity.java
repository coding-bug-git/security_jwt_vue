package cn.bug.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author coding-bug
 * @description
 * @createDate 2022-01-04 10:23
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private  Integer statu;

}
