/**
 * @author Cannicide2753
 */

import javax.swing.JOptionPane;
 
public class JavaCalc {
 
 public interface CalculusInterface {
  public double function(double x);
 }
  
 private CalculusInterface calculusInterface;
 public JavaCalc(CalculusInterface calculusInterface){
  this.calculusInterface = calculusInterface;
 }
  
 private double integrate(double from, double to) {
  final int intevalNum = 1000000;
  double inteval = (to - from) / intevalNum;
  double result = 0d;
  for (int i = 0; i < intevalNum; i++) {
   try {
    result += (calculusInterface.function(from + i*inteval) + 
      calculusInterface.function(from + (i + 1)*inteval)) * inteval / 2;
     
   } catch (Exception e) {
    // TODO: handle exception
    JOptionPane.showMessageDialog(null, "The function is not integrable between " + from + " and " + to);
    System.exit(0);
   }
  }
 
  return result;
 }
  
 private double differentiate(double x){
  final double aSmallValue = 0.0000000001;
  try {
   double d1 = (calculusInterface.function(x + aSmallValue) - 
     calculusInterface.function(x))/(aSmallValue);
   double d2 = (calculusInterface.function(x - aSmallValue) - 
     calculusInterface.function(x))/( - aSmallValue);
   double ratio = d1/d2;
   if (ratio > 1.01 || ratio < 0.99){
    JOptionPane.showMessageDialog(null, "The function is not differntiable at " + x);
    System.exit(0);
   }
   return (d1 + d2) / 2; 
  } catch (Exception e) {
   // TODO: handle exception
   JOptionPane.showMessageDialog(null, "The function is not differntiable at " + x);
   System.exit(0);
   return 0;
  }
 }
 
 public static void main(String[] args) {
  JavaCalc calculus = new JavaCalc(new CalculusInterface() {
   @Override
   public double function(double x) {
    //the function to be integrated or differentiated; configure it on your own
    return Math.pow(Math.E, x);
   }
  });
   
  double integrationValue = calculus.integrate(0, 1);
  //System.out.println("Integration value is : " + integrationValue);
  double differentiationValue = calculus.differentiate(1);
  //System.out.println("Differentiation value is : " + differentiationValue);
  System.out.println("Use WolframAlpha.com instead!");
 }
 
}
