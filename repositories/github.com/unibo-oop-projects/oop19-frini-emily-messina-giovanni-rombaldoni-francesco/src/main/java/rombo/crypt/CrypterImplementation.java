package rombo.crypt;

public class CrypterImplementation implements Crypter {

	/*builder*/
	public CrypterImplementation() {};
	
	/*Creation of a first container of characters for code and encode the string.*/
	private static final char[] map1 = new char[64];
	   static {
	      int i=0;
	      for (char c='A'; c<='Z'; c++) map1[i++] = c;
	      for (char c='a'; c<='z'; c++) map1[i++] = c;
	      for (char c='0'; c<='9'; c++) map1[i++] = c;
	      map1[i++] = '+'; map1[i++] = '/'; }

	   /*Creation of a second container of characters for code and encode the string.*/
	private static final byte[] map2 = new byte[128];
	   static {
	      for (int i=0; i<map2.length; i++) map2[i] = -1;
	      for (int i=0; i<64; i++) map2[map1[i]] = (byte)i; }
	   
	   /*Method for encode a String.*/
	   
	   /*Encodes a byte array into Base64 format.
	    No blanks or line breaks are inserted in the output.
	    
	    @Param in    An array containing the data bytes to be encoded.
	    @Param iOff  Offset of the first byte in <code>in</code> to be processed.
	    @Param iLen  Number of bytes to process in <code>in</code>, starting at <code>iOff</code>.
	    Return      A character array containing the Base64 encoded data.
	    
	    This method permit of encode a byte array(I used an byte array because is more simple to do the conversion).*/
	   private static char[] encode (byte[] in, int iOff, int iLen) {
	      int oDataLen = (iLen*4+2)/3;       // output length without padding.
	      int oLen = ((iLen+2)/3)*4;         // output length including padding.
	      char[] out = new char[oLen];
	      int ip = iOff;
	      int iEnd = iOff + iLen;
	      int op = 0;
	      while (ip < iEnd) {
	         int i0 = in[ip++] & 0xff;
	         int i1 = ip < iEnd ? in[ip++] & 0xff : 0;
	         int i2 = ip < iEnd ? in[ip++] & 0xff : 0;
	         int o0 = i0 >>> 2;
	         int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
	         int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
	         int o3 = i2 & 0x3F;
	         out[op++] = map1[o0];
	         out[op++] = map1[o1];
	         out[op] = op < oDataLen ? map1[o2] : '='; op++;
	         out[op] = op < oDataLen ? map1[o3] : '='; op++; }
	      return out; }
	   
	   /*Encodes a byte array into Base64 format.
	   No blanks or line breaks are inserted in the output.
	    
	   @Param in  An array containing the data bytes to be encoded.
	   @Return    A character array containing the Base64 encoded data.
	   
	   This method permit to convert a string into a byte array.*/
	   private static char[] ToArrayEn (byte[] in) {
	      return encode(in, 0, in.length); }
	   
	   /*Encodes a string into Base64 format.
	    No blanks or line breaks are inserted.
	    
	    @Param s  A String to be encoded.
	    @Return   A String containing the Base64 encoded data.*/
	   public  String Crypt(String s) {
	      return new String(ToArrayEn(s.getBytes())); }
	   
	   /*Dedicated part of class for decode a entry string and after exit a decode string.*/
	   
	   /* Decodes a byte array from Base64 format.
	    No blanks or line breaks are allowed within the Base64 encoded input data.
	    
	   @Param in    A character array containing the Base64 encoded data.
	   @Param iOff  Offset of the first character in <code>in</code> to be processed.
	   @Param iLen  Number of characters to process in <code>in</code>, starting at <code>iOff</code>.
	   @Return      An array containing the decoded data bytes.
	   
	   Throws      IllegalArgumentException If the input is not valid Base64 encoded data.(in this specific case this is unnecessary)*/
	   private static byte[] decode (char[] in, int iOff, int iLen) {
	      if (iLen%4 != 0) throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
	      while (iLen > 0 && in[iOff+iLen-1] == '=') iLen--;
	      int oLen = (iLen*3) / 4;
	      byte[] out = new byte[oLen];
	      int ip = iOff;
	      int iEnd = iOff + iLen;
	      int op = 0;
	      while (ip < iEnd) {
	         int i0 = in[ip++];
	         int i1 = in[ip++];
	         int i2 = ip < iEnd ? in[ip++] : 'A';
	         int i3 = ip < iEnd ? in[ip++] : 'A';
	         if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
	            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
	         int b0 = map2[i0];
	         int b1 = map2[i1];
	         int b2 = map2[i2];
	         int b3 = map2[i3];
	         if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
	            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
	         int o0 = ( b0       <<2) | (b1>>>4);
	         int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
	         int o2 = ((b2 &   3)<<6) |  b3;
	         out[op++] = (byte)o0;
	         if (op<oLen) out[op++] = (byte)o1;
	         if (op<oLen) out[op++] = (byte)o2; }
	      return out; }
	   
	   /*Decodes a byte array from Base64 format.
	   No blanks or line breaks are allowed within the Base64 encoded input data.
	   
	   @Param in  A character array containing the Base64 encoded data.
	   @Return    An array containing the decoded data bytes.
	   
	   Throws    IllegalArgumentException If the input is not valid Base64 encoded data.*/
	   private static byte[] ArrayPropeties (char[] in) {
	      return decode(in, 0, in.length); }
	   
	   
	   /*Decodes a byte array from Base64 format.
	   No blanks or line breaks are allowed within the Base64 encoded input data.
	   
	   @Param s  A Base64 String to be decoded.
	   @Return   An array containing the decoded data bytes.
	   
	   Throws   IllegalArgumentException If the input is not valid Base64 encoded data.*/
	   private static byte[] ToArrayDe (String s) {
	      return ArrayPropeties(s.toCharArray()); }
	   
	   /*Decodes a string from Base64 format.
	   No blanks or line breaks are allowed within the Base64 encoded input data.
	   
	   @Param s  A Base64 String to be decoded.
	   @Return   A String containing the decoded data.
	   
	   Throws   IllegalArgumentException If the input is not valid Base64 encoded data.*/
	   public String Decrypt(String s) {
	      return new String(ToArrayDe(s)); }
	   

}
