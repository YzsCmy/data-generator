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
    private String sn_prefix ;
    /** mac前缀 */
    private String mac_prefix ;
    /** sn流水号 */
    private String sn_serial ;
    /** mac流水号 */
    private String mac_serial ;
    /** 生成时间 */
    private Long create_time ;
    /** 主表id */
    private Integer record_id ;

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
	public String getSn_prefix() {
		return sn_prefix;
	}
	public void setSn_prefix(String sn_prefix) {
		this.sn_prefix = sn_prefix;
	}
	public String getMac_prefix() {
		return mac_prefix;
	}
	public void setMac_prefix(String mac_prefix) {
		this.mac_prefix = mac_prefix;
	}
	public String getSn_serial() {
		return sn_serial;
	}
	public void setSn_serial(String sn_serial) {
		this.sn_serial = sn_serial;
	}
	public String getMac_serial() {
		return mac_serial;
	}
	public void setMac_serial(String mac_serial) {
		this.mac_serial = mac_serial;
	}
	public Long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
    
}