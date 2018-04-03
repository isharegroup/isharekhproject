package com.isharekh.domain.models.user_security;

import com.isharekh.domain.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity{
	@Column(name="role")
	private String role;
}
