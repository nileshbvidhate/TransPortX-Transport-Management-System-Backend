package com.nil.transportx.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transport_Office")
public class TransportOffice {
	
	@Column(name = "office_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer officeId;
	
	@Column(name = "office_name", length = 200, nullable = false)
	@NotBlank(message = "Office Name is required...")
	private String officeName;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "address_id"))
	private Address address;
	
	@Column(name = "contact", length = 15, nullable = false, unique = true)
	@NotBlank(message = "Contact is required...")
	@Size(max = 14, message = "Contact number should not exceed 14 characters")
	private String contact;
	
	@Column(name = "email", length = 50, nullable = false, unique = true)
	@NotBlank(message = "Email is required...")
	@Email(message = "Email should be valid...")
	private String email;
	
	@CreationTimestamp
	@Column(name = "registered_at", updatable = false)
	private LocalDateTime registeredAt;
	
}
