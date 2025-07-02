package com.nil.transportx.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SubDistrict")
public class SubDistrict {
	
	@Column(name = "subdist_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subDistId;
	
	@Column(name = "subdist_name", length = 100,nullable = false)
	@NotBlank(message = "SubDistrict Name is required...")
	private String subDistName;
	
	@Column(name = "pincode", length = 6, nullable = false)
	@NotBlank(message = "pincode is required...")
	private String pincode;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dist_id", nullable = false)
	private District district;
}
