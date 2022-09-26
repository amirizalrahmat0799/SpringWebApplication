package com.custom.webapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotEmpty(message = "Code can't be empty")
	@Column(name = "code",
			updatable = false)
    private String code;
	
	@NotEmpty(message = "Name can't be empty")
	@Column(name = "name")
    private String name;
	
	@NotEmpty(message = "Category can't be empty")
	@Column(name = "category")
    private String category;
	
	@Column(name = "brand")
    private String brand;
	
	@Column(name = "type")
    private String type;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "created_at",
			updatable = false)
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date lastUpdated;
    
	// I don't make use of this useful prebuilt function, i'm sorry.
    public static Product build(
            Integer id,
            String code,
            String name,
            String category,
            String brand,
            String type,
            String description)
    {
        Product item = new Product();
        item.id = id;
        item.code = code;
        item.name = name;
        item.category = category;
        item.brand = brand;
        item.type = type;
        item.description = description;
        return item;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int idx = -1;
        if (id != null) {
            sb.append(++idx > 0 ? ", " : "").append("id=").append(id);
        }
        if (code != null) {
            sb.append(++idx > 0 ? ", " : "").append("code=").append(code);
        }
        if (name != null) {
            sb.append(++idx > 0 ? ", " : "").append("name=").append(name);
        }
        if (category != null) {
            sb.append(++idx > 0 ? ", " : "").append("category=").append(category);
        }
        if (brand != null) {
            sb.append(++idx > 0 ? ", " : "").append("brand=").append(brand);
        }
        if (type != null) {
            sb.append(++idx > 0 ? ", " : "").append("type=").append(type);
        }
        if (description != null) {
            sb.append(++idx > 0 ? ", " : "").append("description=").append(description);
        }
        return sb.toString();
    }

}
