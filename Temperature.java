// name: Pavle Vujicic
// id: 101159343
// course: COMP 1406A
// semester: Semester 1
//
// assignment: Assignement 2
// comments:
//x °C ≘ (x + 273.15) K
//x °C ≘ (x × 9/5+ 32) °F
// To celcius:
//x K ≘ (x − 273.15) °C
//x °F ≘ (x − 32) × 5/9 °C

public class Temperature{

  private double value;
  private String scale=Scale.CELSIUS;



/** Initializes a temperature object with given value in Celsius
 *  <par>
 *  If the initial temperature is less than -273.15C then the temperature
 *  object will be initialized with -273.15C.
 *
 * @param value is the initial temperature in Celsius.
 */
  public Temperature(double value){
    this.value=value;
  }


/** Initializes a temperature object with given value using the specified scale
 * <par>
 * If the temperature is lower than absolute zero, in the given scale,
 * then set the temperature to absolute zero (in that scale).
 * <par>
 * If the scale is not one of the three specified in the Scale class, 
 * then create the object with value 0.0 and scale Scale.NONE
 * <par>
 * Example: new Temperature(12.3, Scale.KELVIN)
 *
 * @param value is the initial temperature
 * @param scale is the scale of initial temperature and must a constant
 *        defined in the Scale enum type.
 */
  public Temperature(double value, String scale){
    setTemp(value,scale);
  }





/** getter for the scale. 
 *  The output will always be one of the static attributes 
 *  from the Scale class.
 *
 * @return the current scale of this object. 
 */
  public String getScale(){
    return scale;
  }

 /** getter for the temperature value (in the current scale)
  *
  * @return the temperature of the object using the current scale
  */
  public double getValue(){
    //x °C ≘ (x + 273.15) K
    //x °C ≘ (x × 9/5+ 32) °F

    if(this.scale.equals(Scale.KELVIN)){
      return value+273.15;
    }else if(this.scale.equals(Scale.FAHRENHEIT)){
      return value *9/5+32;
    }else if(this.scale.equals(Scale.CELSIUS)){
      return value;
    }
    return 0.0;
  }


  /** setter for scale
  * <par>
  * If the scale is not one of the three specified in the Scale class, 
  * then set the to scale Scale.NONE and leave the value unchanged. 
  *
  * @param scale is the new scale of the temperature and must be
  *        a constant from the Scale class.
  */
  public void setScale(String scale){
    if(scale.equals(Scale.KELVIN)){
      this.scale=scale;
    }else if(scale.equals(Scale.FAHRENHEIT)){
      this.scale=scale;
    }else if(scale.equals(Scale.CELSIUS)){
      this.scale=scale;
    }else{
      this.scale=Scale.NONE;
    }
  }

  /** setter for value. It is assumed that this value is in the 
   * object's current scale. 
   * <p>
   * For example, if the current scale
   * is Scale.KELVIN, then obj.setValue(12.4) sets current 
   * temperature to be 12.4K.
   * <p>
   * As usual, if the value is less than absolute zero, the 
   * object's temperature is set to absolute zero in the
   * current scale. 
   *
   * @param value is the new value of the temperature and must be
   */
  public void setValue(double value){
    this.value=value;
  }





  /** setter for temperature
  * <par>
  * If the temperature is lower than absolute zero, in the given scale,
  * then sets the temperature to absolute zero (in that scale).
  * <par>
  * If the scale is not one of the three specified in the Scale class, 
  * then set the object's value to be 0.0 and scale to be Scale.NONE
  *
  * @param value is the new temperature
  * @param scale must be one of the class attributes from the Scale class
  */
  public void setTemp(double value, String scale){
    this.setScale(scale);
    if(this.scale.equals(Scale.KELVIN)){
      // x-273.15
      if(value< 273.15){
        this.value= 0.0;
        this.scale= Scale.NONE;
      }else{
        this.value= value-273.15;
      }

    }else if(this.scale.equals(Scale.FAHRENHEIT)){
      //(x-32) x 5/9
      if(value< 32.00){
        this.value=0.00;
        this.scale=Scale.NONE;
      }else{
        this.value=(value-32.00)*5/9;
      }

    }else if(this.scale.equals(Scale.CELSIUS)){
      if(this.value< 0.0){
        this.value=0.0;
        this.scale=Scale.NONE;
      }else{
        this.value=value;
      }
    }else{
      this.value=0.0;
      this.scale=Scale.NONE;
    }
  }










/* ------------------------------------------------- */
/* ------------------------------------------------- */
/* do not change anything below this                 */
/* ------------------------------------------------- */
/* ------------------------------------------------- */



  /** String representation of a temperature object    */
  @Override
  public String toString(){
    return "" + this.getValue() + this.getScale().charAt(0);
  }

}