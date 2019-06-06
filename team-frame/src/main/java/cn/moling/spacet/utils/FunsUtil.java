package cn.moling.spacet.utils;
/**
 * 常用的用于计算的函数
 * 全部是static类型
 */
public class FunsUtil /*20051216*/
{
 /**
   * 当 arg_checkValue==null 时返回 arg_ValueWhenNull,
   * 当 arg_checkValue!=null 时返回 arg_ValueWhenNotNull
   * @param arg_checkValue
   * @param arg_ValueWhenNull
   */
 public static String decode_null(String arg_checkValue, String arg_ValueWhenNull,String arg_ValueWhenNotNull)
 {
   if (arg_checkValue == null) 
      return arg_ValueWhenNull;
   else
      return arg_ValueWhenNotNull;
 }
 /**
   * 当 arg_checkValue==null 时返回 arg_ValueWhenNull 否则 返回 arg_checkValue
   * @param arg_checkValue
   * @param arg_ValueWhenNull
   * @return 
   */
 public static String decode_null(String arg_checkValue, String arg_ValueWhenNull)
 {
   if (arg_checkValue == null) 
      return arg_ValueWhenNull;
   else
      return arg_checkValue;
 }
 /**
   * 当 arg_checkValue==null 或为""  时返回 arg_ValueWhenBlankOrNull 否则返回 arg_ValueWhenNotNullOrNotBlank
   * @param arg_checkValue
   * @param arg_ValueWhenBlankOrNull
   * @param arg_ValueWhenNotNullOrNotBlank
   */
 public static String decode_blankOrNull(String arg_checkValue, String arg_ValueWhenBlankOrNull,String arg_ValueWhenNotNullOrNotBlank)
 {
   if (arg_checkValue == null || arg_checkValue.trim().length()==0) 
      return arg_ValueWhenBlankOrNull;
   else
      return arg_ValueWhenNotNullOrNotBlank;
 }
 /**
   * 当 arg_checkValue==null 或为""  时返回 arg_ValueWhenBlankOrNull 否则返回 arg_checkValue
   * @param arg_checkValue
   * @param arg_ValueWhenBlankOrNull
   */
 public static String decode_blankOrNull(String arg_checkValue, String arg_ValueWhenBlankOrNull)
 {
   if (arg_checkValue == null || arg_checkValue.trim().length()==0) 
      return arg_ValueWhenBlankOrNull;
   else
      return arg_checkValue;
 }
 
 /**
   * 二选一，首先取非null,非'', 如都非空，则取第1个 
   * 用途示例： choiceOne(Name, nickName) ：真实姓名与呢称只需取其1
   * @param arg1
   * @param arg2
   * @return 
   */
 public static String choiceOne(String arg1, String arg2)
 {
    if (arg1==null) return arg2;
    if (arg2==null) return arg1;
    if (arg1.trim().equals("")) return arg2;
    if (arg2.trim().equals("")) return arg1;
    return arg1.trim();
 }
 /**
   * 当  arg_checkValue=“1”时，返回 arg_ValueWhenValue_1 否则返回 arg_ValueWhenValue_0
   * @param arg_checkValue
   * @param arg_ValueWhenValue_1
   * @param arg_ValueWhenValue_else
   * @return 
   */
 public static String decode_1(String arg_checkValue, String arg_ValueWhenValue_1,String arg_ValueWhenValue_else)
 {
   if (arg_checkValue!=null && arg_checkValue.trim().equals("1")) 
      return arg_ValueWhenValue_1;
   else
      return arg_ValueWhenValue_else;
 }
 public static String decode_0(String arg_checkValue, String arg_ValueWhenValue_0,String arg_ValueWhenValue_else)
 {
   if (arg_checkValue!=null && arg_checkValue.trim().equals("0")) 
      return arg_ValueWhenValue_0;
   else
      return arg_ValueWhenValue_else;
 }
 public static String decode_0(int arg_checkValue, String arg_ValueWhenValue_0,String arg_ValueWhenValue_else)
 {
   if (arg_checkValue==0) 
      return arg_ValueWhenValue_0;
   else
      return arg_ValueWhenValue_else;
 }
  public static String decode_true(boolean arg_checkValue, String arg_ValueWhenValue_true,String arg_ValueWhenValue_else)
 {
   if (arg_checkValue) 
      return arg_ValueWhenValue_true;
   else
      return arg_ValueWhenValue_else;
 }

 /**
   * 当  arg_checkValue=1时，返回 arg_ValueWhenValue_1 否则返回 arg_ValueWhenValue_0
   * @param arg_checkValue
   * @param arg_ValueWhenValue_1
   * @param arg_ValueWhenValue_else
   * @return 
   */
 public static String decode_1(int arg_checkValue, String arg_ValueWhenValue_1,String arg_ValueWhenValue_else)
 {
   if (arg_checkValue==1) 
      return arg_ValueWhenValue_1;
   else
      return arg_ValueWhenValue_else;
 }
 /**
   * 当 arg_value1.equal(arg_value2)时返回 arg_equal_return 否则返回 arg_not_equal_return
   * @param arg_value1
   * @param arg_value2
   * @param arg_equal_return
   * @param arg_not_equal_return
   * @return 
   */
 public static String if2ValueEqualReturn(String arg_value1, String arg_value2,String arg_equal_return, String arg_not_equal_return)
 {
   if (arg_value1==null || arg_value2==null) return arg_equal_return;
   if (arg_value1==null) arg_value1=""; else arg_value1=arg_value1.trim();
   if (arg_value2==null) arg_value2=""; else arg_value2=arg_value2.trim();
   
   if (arg_value1.equals(arg_value2)) 
      return arg_equal_return;
   else
      return arg_not_equal_return;
 }
 public static String case123_9(String arg_checkValue,String arg_case_1_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else return "";
 }
 public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else return "";
 }
 public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return,String arg_case_5_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else if (arg_checkValue.equals("5")) return arg_case_5_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return,String arg_case_5_return,
                                   String arg_case_6_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else if (arg_checkValue.equals("5")) return arg_case_5_return;
   else if (arg_checkValue.equals("6")) return arg_case_6_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return,String arg_case_5_return,
                                   String arg_case_6_return,String arg_case_7_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else if (arg_checkValue.equals("5")) return arg_case_5_return;
   else if (arg_checkValue.equals("6")) return arg_case_6_return;
   else if (arg_checkValue.equals("7")) return arg_case_7_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return,String arg_case_5_return,
                                   String arg_case_6_return,String arg_case_7_return,String arg_case_8_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else if (arg_checkValue.equals("5")) return arg_case_5_return;
   else if (arg_checkValue.equals("6")) return arg_case_6_return;
   else if (arg_checkValue.equals("7")) return arg_case_7_return;
   else if (arg_checkValue.equals("8")) return arg_case_8_return;
   else return "";
 }
  public static String case123_9(String arg_checkValue,String arg_case_1_return,String arg_case_2_return,String arg_case_3_return,String arg_case_4_return,String arg_case_5_return,
                                                         String arg_case_6_return,String arg_case_7_return,String arg_case_8_return,String arg_case_9_return)
 {
   if (arg_checkValue==null) return "";
   arg_checkValue=arg_checkValue.trim();
   if (arg_checkValue.equals("1")) return arg_case_1_return;
   else if (arg_checkValue.equals("2")) return arg_case_2_return;
   else if (arg_checkValue.equals("3")) return arg_case_3_return;
   else if (arg_checkValue.equals("4")) return arg_case_4_return;
   else if (arg_checkValue.equals("5")) return arg_case_5_return;
   else if (arg_checkValue.equals("6")) return arg_case_6_return;
   else if (arg_checkValue.equals("7")) return arg_case_7_return;
   else if (arg_checkValue.equals("8")) return arg_case_8_return;
   else if (arg_checkValue.equals("9")) return arg_case_9_return;
   else return "";
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param check8          条件8
   * @param value8          返回值8
   * @param check9          条件9
   * @param value9          返回值9
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String check5,String value5,
                                String check6,String value6,
                                String check7,String value7,
                                String check8,String value8,
                                String check9,String value9,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   if (check5==null)  check5=""; if (arg_checkValue.trim().equals(check5.trim())) return value5;
   if (check6==null)  check6=""; if (arg_checkValue.trim().equals(check6.trim())) return value6;
   if (check7==null)  check7=""; if (arg_checkValue.trim().equals(check7.trim())) return value7;
   if (check8==null)  check8=""; if (arg_checkValue.trim().equals(check8.trim())) return value8;
   if (check9==null)  check9=""; if (arg_checkValue.trim().equals(check9.trim())) return value9;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param check8          条件8
   * @param value8          返回值8
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String check5,String value5,
                                String check6,String value6,
                                String check7,String value7,
                                String check8,String value8,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   if (check5==null)  check5=""; if (arg_checkValue.trim().equals(check5.trim())) return value5;
   if (check6==null)  check6=""; if (arg_checkValue.trim().equals(check6.trim())) return value6;
   if (check7==null)  check7=""; if (arg_checkValue.trim().equals(check7.trim())) return value7;
   if (check8==null)  check8=""; if (arg_checkValue.trim().equals(check8.trim())) return value8;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String check5,String value5,
                                String check6,String value6,
                                String check7,String value7,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   if (check5==null)  check5=""; if (arg_checkValue.trim().equals(check5.trim())) return value5;
   if (check6==null)  check6=""; if (arg_checkValue.trim().equals(check6.trim())) return value6;
   if (check7==null)  check7=""; if (arg_checkValue.trim().equals(check7.trim())) return value7;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String check5,String value5,
                                String check6,String value6,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   if (check5==null)  check5=""; if (arg_checkValue.trim().equals(check5.trim())) return value5;
   if (check6==null)  check6=""; if (arg_checkValue.trim().equals(check6.trim())) return value6;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String check5,String value5,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   if (check5==null)  check5=""; if (arg_checkValue.trim().equals(check5.trim())) return value5;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String check4,String value4,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   if (check4==null)  check4=""; if (arg_checkValue.trim().equals(check4.trim())) return value4;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String check3,String value3,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   if (check3==null)  check3=""; if (arg_checkValue.trim().equals(check3.trim())) return value3;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String check2,String value2,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   if (check2==null)  check2=""; if (arg_checkValue.trim().equals(check2.trim())) return value2;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(String arg_checkValue,
                                String check1,String value1,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   if (arg_checkValue==null) arg_checkValue="";
   if (check1==null)  check1=""; if (arg_checkValue.trim().equals(check1.trim())) return value1;
   return elseValue;
 }
 //================================以下为数字型比较字段的decode====================
   /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param check8          条件8
   * @param value8          返回值8
   * @param check9          条件9
   * @param value9          返回值9
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                int check5,String value5,
                                int check6,String value6,
                                int check7,String value7,
                                int check8,String value8,
                                int check9,String value9,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   if (arg_checkValue==check5) return value5;
   if (arg_checkValue==check6) return value6;
   if (arg_checkValue==check7) return value7;
   if (arg_checkValue==check8) return value8;
   if (arg_checkValue==check9) return value9;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param check8          条件8
   * @param value8          返回值8
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                int check5,String value5,
                                int check6,String value6,
                                int check7,String value7,
                                int check8,String value8,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   if (arg_checkValue==check5) return value5;
   if (arg_checkValue==check6) return value6;
   if (arg_checkValue==check7) return value7;
   if (arg_checkValue==check8) return value8;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param check7          条件7
   * @param value7          返回值7
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                int check5,String value5,
                                int check6,String value6,
                                int check7,String value7,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   if (arg_checkValue==check5) return value5;
   if (arg_checkValue==check6) return value6;
   if (arg_checkValue==check7) return value7;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param check6          条件6
   * @param value6          返回值6
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                int check5,String value5,
                                int check6,String value6,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   if (arg_checkValue==check5) return value5;
   if (arg_checkValue==check6) return value6;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param check5          条件5
   * @param value5          返回值5
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                int check5,String value5,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   if (arg_checkValue==check5) return value5;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param check4          条件4
   * @param value4          返回值4
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                int check4,String value4,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   if (arg_checkValue==check4) return value4;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param check3          条件3
   * @param value3          返回值3
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                int check3,String value3,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   if (arg_checkValue==check3) return value3;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param check2          条件2
   * @param value2          返回值2
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                int check2,String value2,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   if (arg_checkValue==check2) return value2;
   return elseValue;
 }
  /**
   * 
   * @param arg_checkValue  被检查的参数
   * @param check1          条件1
   * @param value1          返回值1
   * @param elseValue       上述参数条件均不符合时返回 elseValue
   * @return 
   */
  public static String decode(int arg_checkValue,
                                int check1,String value1,
                                String elseValue)             //上述参数条件均不符合时返回 elseValue
 {
   
   if (arg_checkValue==check1) return value1;
   return elseValue;
 }

/**
   * 判断字符串是否为空格 或 null
   * @param arg
   * @return 
   */
public static boolean isBlankOrNull(String arg)
{
  if (arg==null) return true;
  if (arg.trim().equals("")) return true;
  return false;
}
/**
   * 判断 去空格后 是否 = 1 
   * @param arg
   * @return 
   */
public static boolean equals_1(String arg)
{
  if (arg==null) return false;
  if (arg.trim().equals("1")) return true;
  return false;
}

}