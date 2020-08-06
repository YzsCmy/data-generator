package com.unionman.datagenerator.entity;

public class Record{
    /** 主键 */
    private Integer id;
    /** 机型 */
    private String type;
    /** sn前缀 */
    private String sn_prefix;
    /** mac前缀 */
    private String mac_prefix;
    /** sn开始流水号 */
    private String sn_start;
    /** macsn开始流水号 */
    private String mac_start;
    /** 生成时间 */
    private Long create_time;
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

	public String getSn_start() {
		return sn_start;
	}

	public void setSn_start(String sn_start) {
		this.sn_start = sn_start;
	}

	public String getMac_start() {
		return mac_start;
	}

	public void setMac_start(String mac_start) {
		this.mac_start = mac_start;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}