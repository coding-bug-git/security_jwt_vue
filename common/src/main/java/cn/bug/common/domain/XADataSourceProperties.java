package cn.bug.common.domain;

import lombok.Data;

/**
 * Description
 *
 * @author by bug
 * @Date 2021/6/22
 */
@Data
public class XADataSourceProperties {
    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private Integer initialSize;

    private Integer maxActive;

    private Integer minIdle;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;

    private String filters;

}
