package cn.bug.common.domain.vo;

import lombok.Data;

/**
 * Description
 *
 * @author by bug
 * @Date 2021/10/18
 */
@Data
public class LabelInfo {
    private String tkey;
    private String inspectTkey;
    private Long packQty;
    private String matCode;
    private String matName;
    private String mo;
    private String orderNo;
    
    /**
     * 加工设备编码
     */
    private String manuMachineid;
    /**
     *  Erp 入库单编码
     */
    private String docType;
    private String stockType;
    
    
}
