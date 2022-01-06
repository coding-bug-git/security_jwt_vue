package cn.bug.common.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description
 *
 * @author by bug
 * @Date 2021/11/22
 */
@ApiModel("agv 无计划入库 接口参数")
@Data
public class AgvCallIOStockNoPlanControllerVO {
    @ApiModelProperty("qms二维码主键")
    private String qmsTkey;
    @ApiModelProperty("接口调用者")
    private String username;
    @ApiModelProperty("接口调用者Tkey")
    private String userTkey;
}
