package com.outofoffice.notificationsservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name="notifications_type")
@Data
public class NotificationsType {
	
	@Id
    @GeneratedValue(generator = "Notifications_typeIdGenerator",strategy = GenerationType.AUTO)
    private Long id;
	
	@Size(min=1, max=1, message = "Invalid code input")
	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="name")
	private String name;
	
	public NotificationsType() {};
	
	public NotificationsType(String code,String displayName,String name) {
      this.code = code;
      this.displayName = displayName;
      this.name = name;
	}
	

}
