package cn.moling.spacet.utils;
import java.util.Random;

/**
 * int 类型的 工具类
 */
public class IntUtil 
{
  public IntUtil()
  {
  }
 
 /**
   * 生成随机数 数组（可能有重复） int[]  (未排序) 
   * @param random_base   随机数产生的 基数(例 100， 则产生 0-99 ，不会产生100）
   * @param random_count  随机数个数
   */
 public static int[] getRandomList_include_0(int random_base, int random_count)
 {
     if (random_count<=0 || random_base<=0 ) return (new int[0]);
     if (random_base<random_count) random_count = random_base;
     int[] v_ret = new int[random_count];
     Random r=new Random();   //*** r.nextInt(100) 会产生 0-99 的数，但不会产生 100这个数
     
     for(int i=0;i<random_count;i++)
     {
        v_ret[i]=r.nextInt(random_base);
     }
     //Arrays.sort(v_ret);   不做排序
     return v_ret;
   }
 
 /**
   * 生成不重复的 随机数  int[]  
   * @param random_base
   * @param random_count
   * @return 
   */
 public static int[] getUniqueRandomList_include_0(int random_base, int random_count)
 {
     if (random_count<=0 || random_base<=0 )  return (new int[0]);
     if (random_base<random_count) random_count = random_base;
     
     int[] v_ret     = new int[random_count];
     int[] baseList  = new int[random_base];
     Random r=new Random();                   //*** r.nextInt(100) 会产生 0-99 的数，但不会产生 100这个数
     
     //生成 基数 0,1,...
     for(int i=0;i<random_base;i++) baseList[i]=i;
     
     //生成随机数 
     for(int i=0;i<random_count;i++)
     {
        int temp = r.nextInt(random_base-i);     //随机位置
//        System.out.print("i=" + i );
//        System.out.print("random_base-i=" + (random_base -i) );
//        System.out.print("    temp=" + temp);
        v_ret[i]= baseList[temp];                 // 将随机位置中的 值 搬到 结果中 
        baseList[temp]=baseList[random_base-i-1]; // 用最后一个值代替 随机位置中的值
     }
     //Arrays.sort(v_ret);   不做排序
     return v_ret;
   }

 public static int toInt(byte bytes[])
 {
   int value = 0;
   for (int i = -1; ++i < bytes.length;)
   {
     value <<= 8;
     int b = bytes[i] & 0xff;
     value |= b;
   }
   return value;
 }
 
	public static String toHex(int value, int length) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer(length);
		int shift = length - 1 << 2;
		for (int i = -1; ++i < length;) {
			buffer.append(hexDigits[value >> shift & 0xf]);
			value <<= 4;
		}

		return buffer.toString();
	}
	
	public static int toInt(String hexStr, int len){
		int value = 0;
		int hexToDec;
		String thisString;

		thisString = new String(hexStr.toUpperCase());
		if (thisString.length() <= len) {
			for (int i = 0, sLen = thisString.length(); i < sLen; i++) {
				char c = thisString.charAt(i);
				if ((c >= '0' && c <= '9' ) || (c >= 'A' && c <= 'F')) {
					hexToDec = (int)c - ((c >= '0' && c <= '9') ? 48 : 55);
					value += hexToDec * (int)(Math.pow(16, (double)(sLen - 1 - i)));
				} else {
					value = -1;
					break;
				}
			}
		} else {
			value = -1;
		}
		
		return value;
	}
	
	public static String readHexQuery(String input) {
	    StringBuffer queryBuffer = new StringBuffer();
	    int stringLength;
	    int hiByte, loByte;
	    String thisString;

	    thisString = new String(input.toUpperCase());
	    stringLength = thisString.length();

	    if ((stringLength % 2) != 0) return "-1";

	    int i = 0;
	    while (i < stringLength )
	    {
	        if ((thisString.charAt(i) < '0') || ((thisString.charAt(i) > '9') && (thisString.charAt(i) < 'A')) || (thisString.charAt(i) > 'F'))
	        return "-1";
	        i++;
	    }
	    for (i = 0; i < stringLength; i+= 2) 
	    {
	        if ((thisString.charAt(i) >= '0') && (thisString.charAt(i) <= '9'))
	        	hiByte = (int)(thisString.charAt(i) - 48) * 16;
	        else
	            hiByte = (int)(thisString.charAt(i) - 55) * 16;
	        if ((thisString.charAt(i+1) >= '0') && (thisString.charAt(i+1) <= '9'))
	            loByte = (int)thisString.charAt(i+1) - 48;
	        else
	            loByte = (int)thisString.charAt(i+1) - 55;
	        queryBuffer.append((char)(hiByte + loByte));
	    }
	    return queryBuffer.toString();
	}/*end of readHexQuery fn*/

  /**
   * 
   * @param args
   */
  public static void main(String[] args)
  {
      int[] v = getUniqueRandomList_include_0(30,5);
      //Arrays.sort(v);
      for(int i=0;i<v.length;i++) System.out.println(v[i]);

  }
}