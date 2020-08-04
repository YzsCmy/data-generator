package com.unionman.datagenerator.entity;

public class Detail{
    /** 主键 */
    private Integer id ;
    /** 机型 */
    private String type ;
    /** sn */
    private String sn ;
    /** mac */
    private String mac ;
    /** sn前缀 */
    private String snPrefix ;
    /** mac前缀 */
    private String macPrefix ;
    /** sn流水号 */
    private String snSerial ;
    /** mac流水号 */
    private String macSerial ;
    /** 生成时间 */
    private Long createTime ;
    /** 主表id */
    private Integer recordId ;

    /** 主键 */
    public Integer getId(){
        return this.id;
    }
    /** 主键 */
    public void setId(Integer id){
        this.id = id;
    }
    /** 机型 */
    public String getType(){
        return this.type;
    }
    /** 机型 */
    public void setType(String type){
        this.type = type;
    }
    /** sn */
    public String getSn(){
        return this.sn;
    }
    /** sn */
    public void setSn(String sn){
        this.sn = sn;
    }
    /** mac */
    public String getMac(){
        return this.mac;
    }
    /** mac */
    public void setMac(String mac){
        this.mac = mac;
    }
    /** sn前缀 */
    public String getSnPrefix(){
        return this.snPrefix;
    }
    /** sn前缀 */
    public void setSnPrefix(String snPrefix){
        this.snPrefix = snPrefix;
    }
    /** mac前缀 */
    public String getMacPrefix(){
        return this.macPrefix;
    }
    /** mac前缀 */
    public void setMacPrefix(String macPrefix){
        this.macPrefix = macPrefix;
    }
    /** sn流水号 */
    public String getSnSerial(){
        return this.snSerial;
    }
    /** sn流水号 */
    public void setSnSerial(String snSerial){
        this.snSerial = snSerial;
    }
    /** mac流水号 */
    public String getMacSerial(){
        return this.macSerial;
    }
    /** mac流水号 */
    public void setMacSerial(String macSerial){
        this.macSerial = macSerial;
    }
    /** 生成时间 */
    public Long getCreateTime(){
        return this.createTime;
    }
    /** 生成时间 */
    public void setCreateTime(Long createTime){
        this.createTime = createTime;
    }
    /** 主表id */
    public Integer getRecordId(){
        return this.recordId;
    }
    /** 主表id */
    public void setRecordId(Integer recordId){
        this.recordId = recordId;
    }
}