package com.jadteam.jadapi.studentimage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * StudentImage
 */
@Entity
@Table(name = "images")
public class StudentImage {
    
	@Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata", length = 100000)
    private byte[] imageData;

    public StudentImage() {
    }

    public StudentImage(String name, String type, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

}
