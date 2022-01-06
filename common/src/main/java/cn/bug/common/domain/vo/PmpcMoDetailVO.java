package cn.bug.common.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Description
 *
 * @author by bug
 * @Date 2021/9/23
 */
@Data
public class PmpcMoDetailVO {
    private String tkey;
    private String packNo;
    private Long packQty;
    private Long printQty;
    private Long sysInspectStatus;
    private Long finInspectStatus;
    private Long stockStatus;
    private String inspectTkey;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date mdfTime;
    
    private String mo;
    private String orderNo;
    private Long moQty;
    private String matName;
    private String matSpec;
    private String matCode;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date manuDate;
    private Long productionQty;
    private Long instockQty;
    private Long moStatus;
    
}
