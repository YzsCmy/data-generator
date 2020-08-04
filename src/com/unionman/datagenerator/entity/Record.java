package com.unionman.datagenerator.entity;

public class Record{
    /** 主键 */
    private Integer id;
    /** 机型 */
    private String type;
    /** sn前缀 */
    private String snPrefix;
    /** mac前缀 */
    private String macPrefix;
    /** sn开始流水号 */
    private String snStart;
    /** macsn开始流水号 */
    private String macStart;
    /** 生成时间 */
    private Long createTime;
    /** 生成数量 */
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSnPrefix() {
        return snPrefix;
    }

    public void setSnPrefix(String snPrefix) {
        this.snPrefix = snPrefix;
    }

    public String getMacPrefix() {
        return macPrefix;
    }

    public void setMacPrefix(String macPrefix) {
        this.macPrefix = macPrefix;
    }

    public String getSnStart() {
        return snStart;
    }

    public void setSnStart(String snStart) {
        this.snStart = snStart;
    }

    public String getMacStart() {
        return macStart;
    }

    public void setMacStart(String macStart) {
        this.macStart = macStart;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}