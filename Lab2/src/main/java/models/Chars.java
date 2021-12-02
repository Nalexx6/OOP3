
package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for Chars complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Chars">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brilliance" type="{}BrillianceType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="revolutions" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="filtered" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="nutritionValue" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="containerVolume" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="containerMaterial" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Chars", propOrder = {
    "brilliance"
})
public class Chars {

    protected double brilliance;
    @XmlAttribute(name = "revolutions")
    protected Integer revolutions;
    @XmlAttribute(name = "filtered", required = true)
    protected boolean filtered;
    @XmlAttribute(name = "nutritionValue", required = true)
    protected int nutritionValue;
    @XmlAttribute(name = "containerVolume", required = true)
    protected int containerVolume;
    @XmlAttribute(name = "containerMaterial", required = true)
    protected String containerMaterial;

    /**
     * Gets the value of the brilliance property.
     * 
     */
    public double getBrilliance() {
        return brilliance;
    }

    /**
     * Sets the value of the brilliance property.
     * 
     */
    public void setBrilliance(double value) {
        this.brilliance = value;
    }

    /**
     * Gets the value of the revolutions property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRevolutions() {
        return revolutions;
    }

    /**
     * Sets the value of the revolutions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRevolutions(Integer value) {
        this.revolutions = value;
    }

    /**
     * Gets the value of the filtered property.
     * 
     */
    public boolean isFiltered() {
        return filtered;
    }

    /**
     * Sets the value of the filtered property.
     * 
     */
    public void setFiltered(boolean value) {
        this.filtered = value;
    }

    /**
     * Gets the value of the nutritionValue property.
     * 
     */
    public int getNutritionValue() {
        return nutritionValue;
    }

    /**
     * Sets the value of the nutritionValue property.
     * 
     */
    public void setNutritionValue(int value) {
        this.nutritionValue = value;
    }

    /**
     * Gets the value of the containerVolume property.
     * 
     */
    public int getContainerVolume() {
        return containerVolume;
    }

    /**
     * Sets the value of the containerVolume property.
     * 
     */
    public void setContainerVolume(int value) {
        this.containerVolume = value;
    }

    /**
     * Gets the value of the containerMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerMaterial() {
        return containerMaterial;
    }

    /**
     * Sets the value of the containerMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerMaterial(String value) {
        this.containerMaterial = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chars chars = (Chars) o;
        return Double.compare(chars.brilliance, brilliance) == 0 && filtered == chars.filtered && nutritionValue == chars.nutritionValue && containerVolume == chars.containerVolume && Objects.equals(revolutions, chars.revolutions) && Objects.equals(containerMaterial, chars.containerMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brilliance, revolutions, filtered, nutritionValue, containerVolume, containerMaterial);
    }
}
