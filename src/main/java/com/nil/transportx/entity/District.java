package com.nil.transportx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "District")
public class District {
	
	@Column(name = "dist_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer distId;
	
	@Column(name = "dist_name", length = 100, nullable = false)
	@NotBlank(message = "District Name is required...")
	private String distName;
	
	@Column(name = "rto_code", length = 5, nullable = false)
	@NotBlank(message = "RTO Code is required...")
	@Size(max = 4, message = "RTO Code should have only four characters...")
	private String rtoCode;

}
