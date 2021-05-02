package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "grpcEvents")
@Data
public class GRPCModel {
	@Id
    @GeneratedValue(generator = "GRPCIdGenerator", strategy = GenerationType.AUTO)
	private long ID;
	
	@Column(name="url")
	private String url;
	
	@Column(name="status")
	private int status;
	
	@Column(name="action")
	private String action;
	
	@Column(name="serviceName")
	private String serviceName;
	
	@Column(name="localDate")
	private Date localDate;
	
	public GRPCModel () {}

	public GRPCModel(String url, int status, String action, String serviceName, Date localDate) {
		super();
		this.url = url;
		this.status = status;
		this.action = action;
		this.serviceName = serviceName;
		this.localDate = localDate;
	}
	
	
}
