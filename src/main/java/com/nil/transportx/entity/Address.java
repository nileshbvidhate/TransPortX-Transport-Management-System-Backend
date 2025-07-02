package com.nil.transportx.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
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
@Table(name = "Address")
public class Address {
	
	@Column(name = "address_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@Column(name = "street", length = 255, nullable = false)
	@NotBlank(message = "Street is required...")
	private String street;
	
	@Column(name = "city", length = 50, nullable = false)
	@NotBlank(message = "City is required...")
	private String city;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subdist_id", nullable = false, foreignKey = @ForeignKey(name = "subdist_id") )
	private SubDistrict subDistrict;
}
